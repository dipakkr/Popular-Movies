package com.dipakkr.github.moviesapi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.adapter.MovieAdapter;
import com.dipakkr.github.moviesapi.model.Movie;
import com.dipakkr.github.moviesapi.model.MovieResponse;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;
import com.dipakkr.github.moviesapi.utils.RecyclerViewClickListener;

import java.net.URL;
import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchMovieActivity extends AppCompatActivity {

    List<Movie> movies;
    RecyclerView recyclerView;
    ProgressBar progressBar;
    int coint = 1;
    ImageView notfound;
    TextView txt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_movie);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent =getIntent();
        String query = intent.getStringExtra("search");

        notfound = (ImageView)findViewById(R.id.img);
        txt = (TextView)findViewById(R.id.txt_des);
        progressBar = (ProgressBar)findViewById(R.id.pb);
        recyclerView = (RecyclerView)findViewById(R.id.rv_moview_search);
        recyclerView.setLayoutManager(new GridLayoutManager(SearchMovieActivity.this,3));

        ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);
        Call<MovieResponse> call = apIinterface.getSearchedMovie(getResources().getString(R.string.api_key),query);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressBar.setVisibility(View.INVISIBLE);
                int ct = 0;
                ct = response.body().getMovies().size();

                if(ct != 0){
                    movies = response.body().getMovies();
                    recyclerView.setAdapter(new MovieAdapter(movies,SearchMovieActivity.this,R.layout.list_movie_item));
                }else {
                    notfound.setVisibility(View.VISIBLE);
                    txt.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                notfound.setVisibility(View.VISIBLE);
                txt.setVisibility(View.VISIBLE);
            }
        });


        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(this, recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = movies.get(position).getId();

                String movie_name = movies.get(position).getTitle();
                Intent detailIntent = new Intent(SearchMovieActivity.this,MovieDetailActivity.class);
                detailIntent.putExtra("movie_id",id);
                detailIntent.putExtra("movie_name",movie_name);
                detailIntent.putExtra("pos",position);
                startActivity(detailIntent);
            }


            @Override
            public void onItemLongClick(View view, int position) {

            }

        }));
    }
}
