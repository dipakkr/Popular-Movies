package com.dipakkr.github.moviesapi.fragment;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.adapter.MovieAdapter;
import com.dipakkr.github.moviesapi.model.Movie;
import com.dipakkr.github.moviesapi.model.MovieResponse;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by root on 7/18/17.
 */

public class AiringToday extends Fragment {
    ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);;
    private Context context;
    List<Movie> movies;
    RecyclerView recyclerView;
    @Nullable

    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_airtoday,container,false);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setNestedScrollingEnabled(false);

        //Using Animator library

       /* recyclerView.setItemAnimator(new SlideInUpAnimator()); */

        //Calling for getting TOP rated movies
        Call<MovieResponse> responseCall = apIinterface.getTvOnAir(getString(R.string.api_key));
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                movies = response.body().getMovies();
                recyclerView.setAdapter(new MovieAdapter(movies,getActivity(),R.layout.list_movie_item));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        return view;
    }

}
