package com.dipakkr.github.moviesapi.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.Celebrity;

import java.util.List;


/**
 * Created by deepak on 6/29/17.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ImageHolder> {

    List<Celebrity> celebrities;
    private Context mContext;
    private int columnLayout;

    public static class ImageHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView person_pic;

        public ImageHolder(View v) {
            super(v);
            name = (TextView)v.findViewById(R.id.person_name);
            person_pic = (ImageView)v.findViewById(R.id.person_pic);
        }
    }

    public PersonAdapter(List<Celebrity> celebrities, Context mContext, int columnLayout) {
        this.celebrities = celebrities;
        this.mContext = mContext;
        this.columnLayout = columnLayout;
    }

    @Override
    public ImageHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(columnLayout, parent, false);
        return new ImageHolder(view);
    }

    @Override
    public void onBindViewHolder(ImageHolder holder, int position) {


        String BASE_URL = "https://image.tmdb.org/t/p/w500";

        if(celebrities != null){
            String img = celebrities.get(position).getmImagePath();
            String IMG_URL = BASE_URL + img ;
            Log.v("IMage " + position, IMG_URL);

            Glide.with(mContext).load(IMG_URL)
                    .thumbnail(1f)
                    .crossFade()
                    //Center Crop fits the image to image view
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .into(holder.person_pic);

            holder.name.setText(celebrities.get(position).getName());
        }

    }

    @Override
    public int getItemCount() {
        return celebrities.size();
    }

}
