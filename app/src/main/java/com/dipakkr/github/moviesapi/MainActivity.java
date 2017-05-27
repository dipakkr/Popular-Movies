package com.dipakkr.github.moviesapi;

import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY = "53873c6fc26c2abac786d7822d2e1a93";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(API_KEY == null){
            Toast.makeText(this, "Internal Error occured", Toast.LENGTH_SHORT).show();
            return;
        }

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_manage : return  true;

            case R.id.nav_camera : return true;
        }
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{
         super.onBackPressed();
        }
    }
}
