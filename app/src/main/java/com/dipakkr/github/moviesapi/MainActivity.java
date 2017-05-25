package com.dipakkr.github.moviesapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.dipakkr.github.moviesapi.adapter.MovieAdapter;
import com.dipakkr.github.moviesapi.model.Movie;
import com.dipakkr.github.moviesapi.model.MovieResponse;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private static String TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY = "53873c6fc26c2abac786d7822d2e1a93";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(API_KEY == null){
            Toast.makeText(this, "Internal Error occured", Toast.LENGTH_SHORT).show();
            return;
        }

        final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        ApIinterface apiservice = Apiclient.getClient().create(ApIinterface.class);
        Call<MovieResponse> responseCall = apiservice.getTopRated(API_KEY);
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                List<Movie> movies = response.body().getMovies();
                recyclerView.setAdapter(new MovieAdapter(movies,getApplicationContext(),R.layout.list_movie_item));
                Log.d(TAG,"Total movies Recieved " + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, "Error in Calling Api ", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
