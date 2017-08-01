package com.dipakkr.github.moviesapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.Movie;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak on 5/23/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHoder> {

    List<Movie> movieList;
    private Context mContext;
    private int rowLayout;
    private boolean isSelected;

    List<Movie> favlist = new ArrayList<>();

    public static class MovieViewHoder extends RecyclerView.ViewHolder{
        CardView cardView ;
        TextView movieTitle;
        TextView movieOverview;
        ImageView movieIcon;
        Button mFavourite;

        public MovieViewHoder( View v){

            super(v);
            cardView = (CardView)v.findViewById(R.id.movie_card);
            movieTitle = (TextView)v.findViewById(R.id.movie_title);
            movieOverview = (TextView)v.findViewById(R.id.movie_desc);
            movieIcon = (ImageView)v.findViewById(R.id.movie_icon);
            mFavourite = (Button)v.findViewById(R.id.bt_favourite);

        }
    }

    public MovieAdapter(List<Movie> movieList, Context mContext, int rowLayout) {
        this.movieList = movieList;
        this.mContext = mContext;
        this.rowLayout = rowLayout;
    }

    @Override
    public MovieViewHoder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(rowLayout,parent,false);
        return new MovieViewHoder(view);

    }

    @Override
    public void onBindViewHolder(final MovieViewHoder holder, int position) {

        String BASE_URL = "https://image.tmdb.org/t/p/w500";
        String img = movieList.get(position).getPosterPath();
        String IMG_URL = BASE_URL + img ;

        Glide.with(mContext).load(IMG_URL)
                .thumbnail(1f)
                .crossFade()
                //Center Crop fits the image to image view
                .centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(holder.movieIcon);


        if(movieList.get(position).getTitle() != null){
            holder.movieTitle.setText(movieList.get(position).getTitle());
        }

        if(movieList.get(position).getName() != null){
            holder.movieTitle.setText(movieList.get(position).getName());
        }

       /* holder.movieDate.setText(movieList.get(position).getReleaseDate());*/

       // if button is not selected, set selected and move to favourite.
        // if button is selected, set unselected and move to unfavourite.

       holder.mFavourite.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {

               if(isSelected){
                   setSelected();
                   setToFavourite();
               }else{
                   setUnselected();
                   setUnFavourite();
               }
           }
       });
    }

    private void setSelected(){

    }

    private void setUnselected(){

    }

    private void setToFavourite(){
        
    }

    private void setUnFavourite(){


    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }

}
