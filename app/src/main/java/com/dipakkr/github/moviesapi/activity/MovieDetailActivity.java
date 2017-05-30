package com.dipakkr.github.moviesapi.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.dipakkr.github.moviesapi.R;

/**
 * Created by root on 5/28/17.
 */

public class MovieDetailActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);
        Toolbar toolbar = new Toolbar(this);
        setSupportActionBar(toolbar);
    }
}
