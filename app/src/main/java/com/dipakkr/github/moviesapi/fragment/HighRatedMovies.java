package com.dipakkr.github.moviesapi.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.dipakkr.github.moviesapi.R;
import com.dipakkr.github.moviesapi.activity.MovieDetailActivity;
import com.dipakkr.github.moviesapi.adapter.MovieAdapter;
import com.dipakkr.github.moviesapi.model.Movie;
import com.dipakkr.github.moviesapi.model.MovieResponse;
import com.dipakkr.github.moviesapi.rest.ApIinterface;
import com.dipakkr.github.moviesapi.rest.Apiclient;
import com.dipakkr.github.moviesapi.utils.RecyclerViewClickListener;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by deepak on 6/8/17.
 */

public class HighRatedMovies extends Fragment {

    private static final String API_KEY = "53873c6fc26c2abac786d7822d2e1a93";

    private static String TAG = HighRatedMovies.class.getSimpleName();

    private Context context;

    List<Movie> movies;

    ApIinterface apiService = Apiclient.getClient().create(ApIinterface.class);

    ProgressBar bar;
    RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_highrated,container,false);


        if(API_KEY == null){
            Toast.makeText(context, "Go get a Api key first", Toast.LENGTH_SHORT).show();
        }

        bar = (ProgressBar)view.findViewById(R.id.pbar);
        bar.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setNestedScrollingEnabled(false);


        //Using Animator library

       /* recyclerView.setItemAnimator(new SlideInUpAnimator()); */

        //Calling for getting TOP rated movies
        Call<MovieResponse> responseCall = apiService.getTopRated(API_KEY);
        responseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {

                String url = apiService.getTopRated(API_KEY).request().url().toString();
                Log.v(TAG,url);

                bar.setVisibility(View.GONE);
                movies = response.body().getMovies();
                recyclerView.setAdapter(new MovieAdapter(movies,getActivity(),R.layout.list_movie_item));
                Log.d(TAG,"TOTAL MOVIES  : " + movies.size());
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {

            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getContext(), recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                //Get the id of movies and pass them to detail activity

                String id = movies.get(position).getId();
                String movie_name = movies.get(position).getTitle();

                Intent detailIntent = new Intent(getActivity(),MovieDetailActivity.class);
                detailIntent.putExtra("movie_id",id);
                detailIntent.putExtra("movie_name",movie_name);
                detailIntent.putExtra("pos",position);
                startActivity(detailIntent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        }));
    }
}
