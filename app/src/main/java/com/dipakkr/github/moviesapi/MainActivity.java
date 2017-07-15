package com.dipakkr.github.moviesapi;

import android.annotation.TargetApi;
import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.dipakkr.github.moviesapi.activity.PopularPersonActivity;
import com.dipakkr.github.moviesapi.activity.SearchMovieActivity;
import com.dipakkr.github.moviesapi.activity.TvShowActivity;
import com.dipakkr.github.moviesapi.activity.UserProfileActivity;
import com.dipakkr.github.moviesapi.adapter.SimplePagerAdapter;
import com.dipakkr.github.moviesapi.fragment.HighRatedMovies;
import com.dipakkr.github.moviesapi.utils.HelpUtils;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.squareup.picasso.Picasso;

import org.json.JSONObject;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private static int count = 2;
    DrawerLayout drawerLayout;
    SearchView searchView;
    Toolbar toolbar;

    //Text variables
    TextView mEmail;
    String user_name;
    Uri image_uri;
    String query;

    private GoogleApiClient apiClient;
    private Profile profile;
    ImageView profile_image;
    AccessToken accessToken;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        checkLoginstatus();

        MobileAds.initialize(this,getResources().getString(R.string.banner_ad_unit_id));

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        View header = navigationView.getHeaderView(0);
        mEmail = (TextView)header.findViewById(R.id.acc_email);
        profile_image = (ImageView)header.findViewById(R.id.acc_image);
        RelativeLayout headerContainer = (RelativeLayout)header.findViewById(R.id.profile_detail_container);
        headerContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (accessToken != null) {
                    Intent profile = new Intent(MainActivity.this, UserProfileActivity.class);
                    profile.putExtra("name",user_name);
                    startActivity(profile);
                } else {
                    Toast.makeText(MainActivity.this, "You are not logged in.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(accessToken == null){
            mEmail.setTextSize(13);
            mEmail.setText("You are not Logged In.");
        }else{
            user_name = profile.getName();
            image_uri = profile.getProfilePictureUri(220,220);
            mEmail.setText(user_name);
            Picasso.with(getApplicationContext()).load(image_uri).transform(new CropCircleTransformation()).into(profile_image);
        }

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        SimplePagerAdapter adapter = new SimplePagerAdapter(getApplicationContext(),getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(adapter);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }

    private void checkLoginstatus(){
        accessToken = AccessToken.getCurrentAccessToken();
        profile = Profile.getCurrentProfile();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();
        drawerLayout = (DrawerLayout)findViewById(R.id.drawer);

        switch (id){
            case R.id.nav_tvshow :
                Intent TVShow = new Intent(this, TvShowActivity.class);
                startActivity(TVShow);
                return true;

            case R.id.nav_movies :
                drawerLayout.closeDrawer(GravityCompat.START);
                return true;

            case R.id.nav_fav :
                return true;

            case R.id.nav_person :
                Intent person = new Intent(this, PopularPersonActivity.class);
                startActivity(person);
                return true;

            case R.id.nav_send :
                drawerLayout.closeDrawer(GravityCompat.START);
                Intent send = new Intent();
                send.setType(Intent.ACTION_SEND);
                send.setType("text/plain");
                startActivity(Intent.createChooser(send,"Share App, Spread Words"));
                return true ;

            case  R.id.nav_setting :
                return true;

            case R.id.nav_about :
                HelpUtils.showAboutus(this);
         }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @TargetApi(Build.VERSION_CODES.HONEYCOMB)
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        final MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);

        SearchManager manager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView)menu.findItem(R.id.movie_search).getActionView();
        searchView.setSearchableInfo(manager.getSearchableInfo(getComponentName()));
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Intent intent = new Intent(MainActivity.this, SearchMovieActivity.class);
                intent.putExtra("search",query);
                startActivity(intent);
                return true;
            }
            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

       int id = item.getItemId();

        if(id == R.id.movie_search){
            searchView.getQuery();
        }

        return super.onOptionsItemSelected(item);
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
              moveTaskToBack(true);
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

}
