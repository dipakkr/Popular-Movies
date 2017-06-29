package com.dipakkr.github.moviesapi.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.model.Movie;


/**
 * Created by deepak on 6/29/17.
 */

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ImageHolder> {

    String string = "deepak";
    private Context mContext;
    private int columnLayout;

    public static class ImageHolder extends RecyclerView.ViewHolder {
        CardView cardView;


        public ImageHolder(View v) {
            super(v);
            cardView = (CardView) v.findViewById(R.id.movie_card);

        }
    }

    public PersonAdapter(String string, Context mContext, int columnLayout) {
        this.string = string;
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

    }

    @Override
    public int getItemCount() {
        return 20;
    }

    //For setting divider height in grids


}
