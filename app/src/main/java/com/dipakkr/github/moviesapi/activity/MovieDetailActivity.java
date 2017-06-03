package com.dipakkr.github.moviesapi.activity;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.MovieResponse;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by deepak on 5/28/17.
 */

public class MovieDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private static String TAG = MovieDetailActivity.class.getSimpleName();
    private static final String URL_MOVIE = "http://api.themoviedb.org/3/movie/";
    private static final String API_KEY = "53873c6fc26c2abac786d7822d2e1a93";

    TextView overview;
    TextView date;
    TextView title;

    @BindView(R.id.collapse_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;

    @BindView(R.id.app_bar) AppBarLayout appBarLayout;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        Toolbar toolbar = new Toolbar(this);
        setSupportActionBar(toolbar);

        if(getSupportActionBar() !=null) getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String id  = bundle.getString(Intent.EXTRA_TEXT);

        final int pos = bundle.getInt("pos");
        Log.d(TAG,"PASSED_ID : " + id);
        Log.d(TAG,"PASSED POS : " + pos);

        ApIinterface iinterface = Apiclient.getClient().create(ApIinterface.class);

        Call<MovieResponse> movieResponseCall = iinterface.getMovie(id,API_KEY);
        Log.d(TAG,URL_MOVIE);
        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {


            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home){
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int scroll = -1 ;
         boolean isshowing = false;

        if(scroll == -1 ){
            scroll = appBarLayout.getTotalScrollRange();
        }if(scroll + verticalOffset == 0 ) {
            collapsingToolbarLayout.setTitle(getString(R.string.app_name));
        }else if(isshowing){
            collapsingToolbarLayout.setTitle(" ");
            isshowing = false;
        }
    }
    private boolean isnetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }
}
