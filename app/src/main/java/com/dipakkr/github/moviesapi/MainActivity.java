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
import android.support.v7.widget.Toolbar;
import android.text.style.URLSpan;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import android.widget.TextView;
import android.widget.Toast;

import com.dipakkr.github.moviesapi.activity.TvShowActivity;
import com.dipakkr.github.moviesapi.adapter.SimplePagerAdapter;
import com.facebook.AccessToken;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.Profile;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONObject;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static String TAG = MainActivity.class.getSimpleName();

    private static int count = 2;

    Toolbar toolbar;
    DrawerLayout drawerLayout;

    TextView mEmail;

    private GoogleApiClient apiClient;
    AccessToken accessToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        accessToken = AccessToken.getCurrentAccessToken();

        mEmail = (TextView)findViewById(R.id.acc_email);

       /* Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        String name = bundle.getString("name");
        String surname = bundle.getString("surname");
        String url = bundle.getString("imageUrl");

        Log.d(TAG,"NAme " + name);
        Log.d(TAG,"surname" + surname);
        Log.d(TAG,"URL " + url);*/

        DrawerLayout drawerLayout = (DrawerLayout)findViewById(R.id.drawer);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.setDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = (NavigationView)findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ViewPager viewPager = (ViewPager)findViewById(R.id.viewpager);
        TabLayout tabLayout = (TabLayout)findViewById(R.id.tabs);
        SimplePagerAdapter adapter = new SimplePagerAdapter(getApplicationContext(),getSupportFragmentManager());
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        viewPager.setAdapter(adapter);

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
         }

        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
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
}
