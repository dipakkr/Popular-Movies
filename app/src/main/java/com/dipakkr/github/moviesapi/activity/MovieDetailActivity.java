package com.dipakkr.github.moviesapi.activity;


import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.MovieDescription;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.squareup.picasso.Picasso;


import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.view.View.GONE;

/**
 * Created by deepak on 5/28/17.
 */

public class MovieDetailActivity extends AppCompatActivity implements AppBarLayout.OnOffsetChangedListener {

    private static String TAG = MovieDetailActivity.class.getSimpleName();
    private static final String API_KEY = "53873c6fc26c2abac786d7822d2e1a93";
    private static String BASE_URL = "https://image.tmdb.org/t/p/w500";


    @BindView(R.id.collapse_toolbar)
    CollapsingToolbarLayout collapsingToolbarLayout;
    @BindView(R.id.app_bar)

    TextView mMovieDesc;
    TextView mReleaseDate;
    ImageView mPoster;
    ProgressBar progressBar;
    TextView mLang;
    TextView mRunTime;
    TextView mVotes;
    TextView mRating;
    Button review;

    ImageView image_play;

    ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);
    String id;
    String movie_name;

    private AdView mAdView;

    int count = 1;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        MobileAds.initialize(this,getResources().getString(R.string.banner_ad_unit_id));
        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);


        Intent intent = this.getIntent();

        if(intent != null){
            Bundle bundle = intent.getExtras();
            id = bundle.getString("movie_id");
            movie_name = bundle.getString("movie_name");
            final int pos = bundle.getInt("pos");
        }

        Log.d(TAG, "PASSED_ID : " + id);
        Log.d(TAG, "PASSED POS : " + movie_name);

        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_detail);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        toolbar.setTitle(movie_name);

        mMovieDesc = (TextView) findViewById(R.id.movie_desc);
        mReleaseDate = (TextView) findViewById(R.id.release_date);
        mPoster = (ImageView) findViewById(R.id.movie_poster);
        progressBar = (ProgressBar) findViewById(R.id.progress_bar);
        mLang = (TextView) findViewById(R.id.movie_lang);
        mRunTime = (TextView) findViewById(R.id.movie_runtime);
        mVotes = (TextView) findViewById(R.id.movie_vote);
        mRating = (TextView) findViewById(R.id.txt_review);
        review = (Button)findViewById(R.id.bt_review);

        image_play = (ImageView)findViewById(R.id.bt_videos);
        progressBar.setVisibility(View.VISIBLE);

        final Call<MovieDescription> movieDescriptionCall = apIinterface.getMovieDes(id, API_KEY);
        movieDescriptionCall.enqueue(new Callback<MovieDescription>() {
            @Override
            public void onResponse(Call<MovieDescription> call, Response<MovieDescription> response) {
                Log.v(TAG, apIinterface.getMovieDes(id, API_KEY).request().url().toString());
                String desc = response.body().getMovie_overview();
                String date = response.body().getMovie_release_date();
                String image_path = response.body().getMovie_poster_path();
                String lang = response.body().getMovie_lang();
                String runtime = response.body().getMovie_runtime();
                String votes = response.body().getMovie_votes();
                double rating = response.body().getMovie_avg_vote();
                int id = response.body().getMovie_id();


                Log.v("MOVIE ID========>>>>",String.valueOf(id));

                String mRate = String.valueOf(rating);

                String IMG_URL = BASE_URL + image_path;
                progressBar.setVisibility(GONE);
                Picasso.with(getApplicationContext()).load(IMG_URL).into(mPoster);
                mMovieDesc.setText(desc);
                mReleaseDate.setText("Release Date "+date);
                mLang.setText(lang);
                mRunTime.setText(runtime);
                mVotes.setText(votes);
                mRating.setText(mRate);
            }

            @Override
            public void onFailure(Call<MovieDescription> call, Throwable t) {
                Toast.makeText(MovieDetailActivity.this, "Error in Network Connectivity", Toast.LENGTH_SHORT).show();
            }
        });

        image_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent videos = new Intent(MovieDetailActivity.this,VideosActivity.class);
                videos.putExtra("movie_id",id);
                startActivity(videos);
            }
        });

        review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent reviewIntent = new Intent(MovieDetailActivity.this,MovieReview.class);
                reviewIntent.putExtra("id",id);
                startActivity(reviewIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_movie_detail, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == android.R.id.home) {
            finish();
        }

        if(id == R.id.fav){

            if(count == 1){
                count ++;
                item.setIcon(R.drawable.ic_favorite_red_24dp);
                Toast.makeText(this, "Added to Favourite", Toast.LENGTH_SHORT).show();
            }
            else {
                count --;
                item.setIcon(R.drawable.heart_brown);
                Toast.makeText(this, "Removed from Favourite", Toast.LENGTH_SHORT).show();
            }


        }

        if(id == R.id.share_movie){
            //
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
        int scroll = -1;
        boolean isshowing = false;

        if (scroll == -1) {
            scroll = appBarLayout.getTotalScrollRange();
            Toast.makeText(this, "Collapsed", Toast.LENGTH_SHORT).show();

        }
        if (scroll + verticalOffset == 0) {
            collapsingToolbarLayout.setTitle(movie_name);
            Toast.makeText(this, "Collapsed", Toast.LENGTH_SHORT).show();
        } else if (isshowing) {
            collapsingToolbarLayout.setTitle(" ");
            Toast.makeText(this, "Collapsed", Toast.LENGTH_SHORT).show();
            isshowing = false;
        }
    }

}
