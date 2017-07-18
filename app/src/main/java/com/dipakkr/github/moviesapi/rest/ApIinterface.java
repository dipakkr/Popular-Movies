package com.dipakkr.github.moviesapi.rest;

import com.dipakkr.github.moviesapi.model.Celebrity;
import com.dipakkr.github.moviesapi.model.CelebrityDetail;
import com.dipakkr.github.moviesapi.model.MovieDescription;
import com.dipakkr.github.moviesapi.model.MovieResponse;
import com.dipakkr.github.moviesapi.model.MovieReviewResponse;
import com.dipakkr.github.moviesapi.model.MovieVideo;
import com.dipakkr.github.moviesapi.model.PopularCelebrity;

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

    @GET("movie/{movie_id}/videos")
    Call<MovieVideo>getMovieVideo(@Path("movie_id") String id ,@Query("api_key") String apikey);

    @GET("movie/{movie_id}/reviews")
    Call<MovieReviewResponse>getMoviesReview(@Path("movie_id") String id ,@Query("api_key") String apikey);

    @GET("person/{person_id}")
    Call<CelebrityDetail>getCelebrityDetail(@Path("person_id") String id ,@Query("api_key") String apikey);

    @GET("tv/on_the_air")
    Call<MovieResponse>getTvOnAir(@Query("api_key") String apikey);

    @GET("tv/top_rated")
    Call<MovieResponse>getTvTopRated(@Query("api_key") String apikey);

    @GET("tv/airing_today")
    Call<MovieResponse>getTvAiringToday(@Query("api_key") String apikey);

    @GET("tv/popular")
    Call<MovieResponse>getTvPopular(@Query("api_key") String apikey);

}
