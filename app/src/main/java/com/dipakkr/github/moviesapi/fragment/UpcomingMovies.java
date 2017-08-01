package com.dipakkr.github.moviesapi.fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
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

public class UpcomingMovies extends android.support.v4.app.Fragment {

    private static final String API_KEY = "53873c6fc26c2abac786d7822d2e1a93";

    private static String TAG = PopularMovies.class.getSimpleName();

    private Context context;

    List<Movie> pop_movies;
    ProgressBar progressBar ;

    RecyclerView recyclerView;

    ApIinterface apIinterface = Apiclient.getClient().create(ApIinterface.class);


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frag_upcoming_movies,container,false);

        progressBar = (ProgressBar)view.findViewById(R.id.pbar);
        progressBar.setVisibility(View.VISIBLE);

        recyclerView = (RecyclerView)view.findViewById(R.id.recyler_view);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(),3));
        recyclerView.setNestedScrollingEnabled(false);


        Call<MovieResponse> movieResponseCall = apIinterface.getUpcomingMovies(API_KEY);

        movieResponseCall.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                progressBar.setVisibility(View.GONE);
                pop_movies = response.body().getMovies();
                Log.d(TAG, "Movie Response Fetched : " + pop_movies.size());
                recyclerView.setAdapter(new MovieAdapter(pop_movies,getActivity(),R.layout.list_movie_item));
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(getActivity(), "Failed to get Data", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        recyclerView.addOnItemTouchListener(new RecyclerViewClickListener(getActivity(), recyclerView, new RecyclerViewClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                String id = pop_movies.get(position).getId();
                String movie_name = pop_movies.get(position).getTitle();

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
