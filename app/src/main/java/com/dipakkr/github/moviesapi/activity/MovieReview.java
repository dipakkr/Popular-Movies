package com.dipakkr.github.moviesapi.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.MovieReviewResponse;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;

import java.util.List;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieReview extends AppCompatActivity {

    List<MovieReviewResponse.ReviewItem> reviewItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_review);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String id = intent.getStringExtra("id");

        ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);
        Call<MovieReviewResponse> responseCall = apIinterface.getMoviesReview(id,getResources().getString(R.string.api_key));
        responseCall.enqueue(new Callback<MovieReviewResponse>() {
            @Override
            public void onResponse(Call<MovieReviewResponse> call, Response<MovieReviewResponse> response) {
                reviewItems = response.body().getMovieReviewList();
            }

            @Override
            public void onFailure(Call<MovieReviewResponse> call, Throwable t) {

            }
        });
    }
}
