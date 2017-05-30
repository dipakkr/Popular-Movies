package com.dipakkr.github.moviesapi.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import com.dipakkr.github.moviesapi.R;

/**
 * Created by deepak on 5/28/17.
 */

public class MovieDetailActivity extends AppCompatActivity {

    private static String TAG = MovieDetailActivity.class.getSimpleName();
    private static final String URL_MOVIE = "http://api.themoviedb.org/3/movie/";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Intent intent = this.getIntent();
        String id  = intent.getStringExtra(Intent.EXTRA_TEXT);
        Log.d(TAG,"PASSED_ID" + id);
        Toolbar toolbar = new Toolbar(this);
        setSupportActionBar(toolbar);
        setTitle(R.string.app_name);

    }
}
