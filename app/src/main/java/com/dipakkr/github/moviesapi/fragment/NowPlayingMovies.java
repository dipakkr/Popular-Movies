package com.dipakkr.github.moviesapi.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.dipakkr.github.moviesapi.R;

/**
 * Created by root on 6/8/17.
 */

public class NowPlayingMovies extends Fragment {

    ProgressBar progressBar ;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_nowplaying,container,false);

        progressBar = (ProgressBar)view.findViewById(R.id.pbar);
        progressBar.setVisibility(View.VISIBLE);
        return view;
    }
}
