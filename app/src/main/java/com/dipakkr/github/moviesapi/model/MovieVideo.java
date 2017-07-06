package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/3/17.
 */

public class MovieVideo {

    @SerializedName("results")
    private List<MovieVideoList> movieVideoLists = new ArrayList<>();

    public List<MovieVideoList> getMovieVideoLists() {
        return movieVideoLists;
    }

}
