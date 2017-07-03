package com.dipakkr.github.moviesapi.rest;

import com.dipakkr.github.moviesapi.model.MovieDescription;
import com.dipakkr.github.moviesapi.model.MovieResponse;
import com.dipakkr.github.moviesapi.model.PopularCelebrity;
import com.facebook.CallbackManager;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
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
    Call<MovieDescription>getMovieDes(@Path("id") String id, @Query("api_key") String apikey);

    @GET("person/popular")
    Call<PopularCelebrity>getPopCelebrity(@Query("api_key") String apikey);

    @GET("search/movie")
    Call<MovieResponse>getSearchedMovie(@Query("api_key") String apikey, @Query("query") String query);

}
