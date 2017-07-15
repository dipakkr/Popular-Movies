package com.dipakkr.github.moviesapi.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.dipakkr.github.moviesapi.MainActivity;
import com.dipakkr.github.moviesapi.R;
import com.google.android.gms.auth.api.Auth;

/**
 * Created by deepak on 6/24/17.
 */

public class SplashActivity extends AppCompatActivity {

    Animation zoomIn, slide_down;
    TextView txt;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        txt = (TextView) findViewById(R.id.txt);
        zoomIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.zoom_in);
        txt.setVisibility(View.VISIBLE);
        txt.setAnimation(zoomIn);

        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                    startActivity(new Intent(SplashActivity.this, Authentication.class));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
