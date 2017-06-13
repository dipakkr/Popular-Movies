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
import com.dipakkr.github.moviesapi.model.Movie;
import com.dipakkr.github.moviesapi.model.MovieDescription;
import com.dipakkr.github.moviesapi.model.MovieResponse;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.Query;

/**
 * Created by deepak on 5/28/17.
 */


public class MovieDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener{

    private static String TAG = MovieDetailActivity.class.getSimpleName();
    private static final String API_KEY = "53873c6fc26c2abac786d7822d2e1a93";


    @BindView(R.id.collapse_toolbar) CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar) AppBarLayout appBarLayout;

    TextView mMovieDesc;
    TextView mReleaseDate;

    MovieDescription movieDescription;
    ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);
    String id;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        final Toolbar toolbar = new Toolbar(this);
        if(toolbar != null){
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        id  = bundle.getString("movie_id");
        final String movie_name = bundle.getString("movie_name");
        final int pos = bundle.getInt("pos");
        Log.d(TAG,"PASSED_ID : " + id);
        Log.d(TAG,"PASSED POS : " + movie_name);

        TextView mTitle = (TextView)findViewById(R.id.md_name);
        mTitle.setText(movie_name);

        mMovieDesc = (TextView)findViewById(R.id.movie_desc);
        mReleaseDate = (TextView)findViewById(R.id.release_date);

        final Call<MovieDescription> movieDescriptionCall = apIinterface.getMovieDes(id,API_KEY);
        movieDescriptionCall.enqueue(new Callback<MovieDescription>() {
            @Override
            public void onResponse(Call<MovieDescription> call, Response<MovieDescription> response) {
                Log.v(TAG,apIinterface.getMovieDes(id,API_KEY).request().url().toString());
                String desc = response.body().getMovie_overview();
                String date = response.body().getMovie_release_date();

                mMovieDesc.setText(desc);
                mReleaseDate.setText(date);
            }

            @Override
            public void onFailure(Call<MovieDescription> call, Throwable t) {

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
