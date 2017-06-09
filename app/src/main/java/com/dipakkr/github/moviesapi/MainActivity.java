package com.dipakkr.github.moviesapi;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.dipakkr.github.moviesapi.activity.MovieDetailActivity;
import com.dipakkr.github.moviesapi.adapter.MovieAdapter;
import com.dipakkr.github.moviesapi.adapter.SimplePagerAdapter;
import com.dipakkr.github.moviesapi.model.Movie;
import com.dipakkr.github.moviesapi.model.MovieResponse;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;
import com.dipakkr.github.moviesapi.utils.RecyclerViewClickListener;

import java.util.List;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private static int count = 2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //Setup View pager
        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        SimplePagerAdapter adapter = new SimplePagerAdapter(getApplicationContext(),getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(adapter);


        /*final RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        //Using Animator library
        recyclerView.setItemAnimator(new SlideInUpAnimator());

        //Calling for getting TOP rated movies

        Call<MovieResponse> responseCall = apiService.getTopRated(API_KEY);
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                movies = response.body().getMovies();
                recyclerView.setAdapter(new MovieAdapter(movies, getApplicationContext(), R.layout.list_movie_item));
                Log.d(TAG, "Total movies Received " + movies.size());

            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                if(!isNetworkConnected()) {

                }
                Toast.makeText(MainActivity.this, "Error in Calling Api ", Toast.LENGTH_SHORT).show();
            }
        });

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(this, recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Get the id of movies and pass them to detail activity

                String id = movies.get(position).getId();
                Log.d(TAG,"ID = " + id);
                Toast.makeText(MainActivity.this, "Item " + position+ "clicked" , Toast.LENGTH_SHORT).show();
                Intent detailIntent = new Intent(MainActivity.this,MovieDetailActivity.class);
                detailIntent.putExtra(Intent.EXTRA_TEXT,id);
                detailIntent.putExtra("pos",position);
                startActivity(detailIntent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));*/
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();

        switch (id){
            case R.id.nav_tvshow :
                return true;

            case R.id.nav_movies :
                return true;

            case R.id.nav_menu3 :
                return true;

            case R.id.nav_menu4 :
                return true;

            case R.id.nav_send :
                return true ;

            case  R.id.nav_setting :
                return true;

         }

         DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
         drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onBackPressed() {
        count--;
        Log.d(TAG,"COUNT == "+count);
        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }
        else{

            if(count == 0){
                count = 0 ;
                finish();
                Log.d(TAG,"COUNT == 0");
            }else{
                Toast.makeText(this, "Press again to exit", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        count = 2;
    }

    private boolean isNetworkConnected(){
        ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() !=null;
    }
}
