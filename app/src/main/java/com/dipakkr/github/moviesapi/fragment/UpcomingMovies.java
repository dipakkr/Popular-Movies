package com.dipakkr.github.moviesapi.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dipakkr.github.moviesapi.R;

/**
 * Created by root on 6/8/17.
 */

public class UpcomingMovies extends android.support.v4.app.Fragment {

    ProgressBar progressBar;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_upcoming_movies,container,false);
        progressBar = (ProgressBar)view.findViewById(R.id.pbar);
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }
}
