package com.dipakkr.github.moviesapi.rest;

import com.dipakkr.github.moviesapi.model.MovieResponse;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by deepak on 5/23/17.
 */

public interface ApIinterface {

    //Define the API endpoints in AP interface
    @GET("movie/top_rated")
    Call<MovieResponse>getTopRated(@Query("api_key") String apikey);

    @GET("movie/{id")
    Call<MovieResponse>getMovie(@Query("api_key") String apikey);


    
}
