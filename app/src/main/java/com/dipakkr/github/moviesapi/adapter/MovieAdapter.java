package com.dipakkr.github.moviesapi.adapter;

import android.app.Activity;
import android.content.Context;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.Movie;

import java.util.List;

import static java.security.AccessController.getContext;

/**
 * Created by deepak on 5/23/17.
 */

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHoder> {

    private List<Movie> movieList;
    private Context mContext;
    private int rowLayout;

    public static class MovieViewHoder extends RecyclerView.ViewHolder{
        CardView cardView ;
        TextView movieTitle;
        TextView movieOverview;
        ImageView movieIcon;
        TextView movieDate;

        public MovieViewHoder(View v){
            super(v);
            cardView = (CardView)v.findViewById(R.id.movie_card);
            movieTitle = (TextView)v.findViewById(R.id.movie_title);
            movieOverview = (TextView)v.findViewById(R.id.movie_desc);
            movieIcon = (ImageView)v.findViewById(R.id.movie_icon);
            movieDate = (TextView)v.findViewById(R.id.movie_release_date);
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
    public void onBindViewHolder(MovieViewHoder holder, int position) {

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

        holder.movieTitle.setText(movieList.get(position).getTitle());
        holder.movieDate.setText(movieList.get(position).getReleaseDate());
    }

    @Override
    public int getItemCount() {
        return movieList.size();
    }
}
