package com.dipakkr.github.moviesapi.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.nfc.Tag;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.MovieVideo;
import com.dipakkr.github.moviesapi.model.MovieVideoList;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;

import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Url;

public class VideosActivity extends AppCompatActivity {

    List<MovieVideoList> movieVideoLists;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videos);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("movie_id");
        Log.v("PASSED ID ====??>>>",id);

         listView = (ListView)findViewById(R.id.list_trailers);
        final VideoAdapter adapter = new VideoAdapter(this,R.layout.list_item_videos,movieVideoLists);

        ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);
        Call<MovieVideo> call = apIinterface.getMovieVideo(id,getResources().getString(R.string.api_key));
        call.enqueue(new Callback<MovieVideo>() {
            @Override
            public void onResponse(Call<MovieVideo> call, Response<MovieVideo> response) {
                movieVideoLists = response.body().getMovieVideoLists();
                listView.setAdapter(new VideoAdapter(VideosActivity.this,R.layout.list_item_videos,movieVideoLists));
            }

            @Override
            public void onFailure(Call<MovieVideo> call, Throwable t) {

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int posti = parent.getPositionForView(view);
                String key = movieVideoLists.get(posti).getVideo_key();
                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.youtube.com/watch?v="+key)));
            }
        });

    }
    private class VideoAdapter extends ArrayAdapter<MovieVideoList>{

        private Context mContext;
        List<MovieVideoList> movieVideoLists;

        public VideoAdapter(@NonNull Context context, @LayoutRes int resource,List<MovieVideoList> movieVideoLists) {
            super(context, resource,movieVideoLists);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_item_videos,parent,false);
            }
            MovieVideoList movieVideoList = getItem(position);
            int pos = position + 1;
            TextView title = (TextView)convertView.findViewById(R.id.video_title);
            title.setText("Trailer " +  pos);
            TextView type = (TextView)convertView.findViewById(R.id.video_type);
            type.setText(movieVideoList.getVideo_type());

            return convertView;
        }

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if(id == android.R.id.home){
            finish();
        }

        return super.onOptionsItemSelected(item);
    }
}
