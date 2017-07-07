package com.dipakkr.github.moviesapi.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.dipakkr.github.moviesapi.MainActivity;
import com.dipakkr.github.moviesapi.R;
import com.facebook.AccessToken;
import com.facebook.Profile;
import com.facebook.login.LoginManager;
import com.squareup.picasso.Picasso;

import java.net.URI;

import jp.wasabeef.picasso.transformations.CropCircleTransformation;

public class UserProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        final Profile profile = Profile.getCurrentProfile();
        Uri imagelink = profile.getProfilePictureUri(350,350);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String name = intent.getStringExtra("name");
        ImageView imageView = (ImageView)findViewById(R.id.image_profile);
        TextView mName = (TextView)findViewById(R.id.profile_name);
        Button button = (Button)findViewById(R.id.logout);

        mName.setText(name);
        Picasso.with(getApplicationContext()).load(imagelink).transform(new CropCircleTransformation()).into(imageView);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginManager.getInstance().logOut();
                startActivity(new Intent(UserProfileActivity.this,MainActivity.class));
            }
        });
    }
}
