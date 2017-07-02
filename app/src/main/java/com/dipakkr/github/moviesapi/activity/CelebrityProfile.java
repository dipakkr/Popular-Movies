package com.dipakkr.github.moviesapi.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.dipakkr.github.moviesapi.R;

public class CelebrityProfile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_profile);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
