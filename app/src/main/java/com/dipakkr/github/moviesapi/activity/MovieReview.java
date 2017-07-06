package com.dipakkr.github.moviesapi.activity;

import android.content.Context;
import android.content.Intent;
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
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.MovieReviewResponse;
import com.dipakkr.github.moviesapi.model.MovieVideoList;
import com.dipakkr.github.moviesapi.model.ReviewItem;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;

import java.util.Arrays;
import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieReview extends AppCompatActivity {

    List<ReviewItem> reviewItems;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_review);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");
        final TextView error_desc = (TextView)findViewById(R.id.txt_des);
        final ImageView error_img = (ImageView)findViewById(R.id.img);


        listView = (ListView)findViewById(R.id.list_review);
        final MovieReviewAdapter adapter = new MovieReviewAdapter(this,R.layout.list_movie_review,reviewItems);

        ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);
        Call<MovieReviewResponse> responseCall = apIinterface.getMoviesReview(id,getResources().getString(R.string.api_key));
        HttpUrl url = responseCall.request().url();
        Log.v("TAGGURL",url.toString());

        responseCall.enqueue(new Callback<MovieReviewResponse>() {
            @Override
            public void onResponse(Call<MovieReviewResponse> call, Response<MovieReviewResponse> response) {
                int ct = 0;
                ct = response.body().getMovieReviewList().size();
                if(ct != 0){
                    reviewItems = response.body().getMovieReviewList();
                    listView.setAdapter(new MovieReviewAdapter(MovieReview.this,R.layout.list_movie_review,reviewItems));
                }else{
                    error_desc.setVisibility(View.VISIBLE);
                    error_img.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<MovieReviewResponse> call, Throwable t) {

            }
        });
    }

    private class MovieReviewAdapter extends ArrayAdapter<ReviewItem>{

        private Context mContext;
        List<ReviewItem> reviewItems;

        public MovieReviewAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<ReviewItem> objects) {
            super(context, resource, objects);
            mContext = context;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

            if(convertView == null){
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.list_movie_review,parent,false);
            }

            ReviewItem reviewItem = getItem(position);

            TextView name = (TextView)convertView.findViewById(R.id.review_author_name);
            name.setText(reviewItem.getAuthor());
            TextView comment = (TextView)convertView.findViewById(R.id.review_content);
            comment.setText(reviewItem.getReviewContent());

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
