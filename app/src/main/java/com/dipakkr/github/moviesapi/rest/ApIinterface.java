package com.dipakkr.github.moviesapi.rest;

import com.dipakkr.github.moviesapi.model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by deepak on 5/23/17.
 */

public interface ApIinterface {

    //Define the API endpoints in AP interface
    @GET("movie/top_rated")
    Call<MovieResponse>getTopRated(@Query("api_key") String apikey);

    @GET("movie/popular")
    Call<MovieResponse>getPopularMovies(@Query("api_key") String apikey);

    @GET("movie/now_playing")
    Call<MovieResponse>getNowPlayingMovies(@Query("api_key") String apikey);

    @GET("movie/upcoming")
    Call<MovieResponse>getUpcomingMovies(@Query("api_key") String apikey);

    @GET("movie/{id}")
    Call<MovieResponse>getMovie(@Query("id") String id, @Query("api_key") String apikey);

}
