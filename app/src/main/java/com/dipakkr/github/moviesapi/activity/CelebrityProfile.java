package com.dipakkr.github.moviesapi.activity;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.Celebrity;
import com.dipakkr.github.moviesapi.model.CelebrityDetail;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.gson.Gson;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CelebrityProfile extends AppCompatActivity {

    String id;
    String name;
    int count = 0;
    CelebrityDetail celebrityDetail;

    private AdView mAdView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_celebrity_profile);

        FontOverride.setDefaultFont(this, "MONOSPACE", "my_font.ttf");

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        if(intent != null){
             id = intent.getStringExtra("person_id");
             name = intent.getStringExtra("person_name");
        }

        getSupportActionBar().setTitle(name);

        mAdView = (AdView) findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);
        Call<CelebrityDetail> call = apIinterface.getCelebrityDetail(id,getString(R.string.api_key));
        call.enqueue(new Callback<CelebrityDetail>() {
            @Override
            public void onResponse(Call<CelebrityDetail> call, Response<CelebrityDetail> response) {
                celebrityDetail = response.body();
                updateUI();
            }

            @Override
            public void onFailure(Call<CelebrityDetail> call, Throwable t) {

            }
        });
    }
    private void updateUI(){

        ImageView imageView = (ImageView)findViewById(R.id.image_person);
        TextView txt_name = (TextView)findViewById(R.id.txt_name);
        TextView txt_birth = (TextView)findViewById(R.id.dob);
        TextView place = (TextView)findViewById(R.id.place);
        TextView txt_desc = (TextView)findViewById(R.id.person_bio);

        String BASE_URL = "https://image.tmdb.org/t/p/w500";
        String img = celebrityDetail.getImage();
        String IMG_URL = BASE_URL + img ;

        Glide.with(CelebrityProfile.this).load(IMG_URL)
                .thumbnail(1f)
                .crossFade()
                //Center Crop fits the image to image view
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imageView);

        txt_name.setText(celebrityDetail.getName());
        place.setText(celebrityDetail.getPlace());
        txt_desc.setText(celebrityDetail.getBio());

        String birth = celebrityDetail.getBirthday();

        String death = celebrityDetail.getDeath();

        if(death == null){
            String dates = birth + " - " + "Present";
            txt_birth.setText(dates);
        }else {
            String dates = birth + " - " + death;
            txt_birth.setText(dates);
        }

    }
    public static class FontOverride {

        public static void setDefaultFont(Context context,
                                          String staticTypefaceFieldName, String fontAssetName) {
            final Typeface regular = Typeface.createFromAsset(context.getAssets(),
                    fontAssetName);
            replaceFont(staticTypefaceFieldName, regular);
        }

        protected static void replaceFont(String staticTypefaceFieldName,
                                          final Typeface newTypeface) {
            try {
                final Field staticField = Typeface.class
                        .getDeclaredField(staticTypefaceFieldName);
                staticField.setAccessible(true);

                staticField.set(null, newTypeface);
            } catch (NoSuchFieldException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }


    }
}
