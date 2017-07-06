package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/6/17.
 */

public class MovieReviewResponse {
    @SerializedName("results")
    private List<ReviewItem> movieVideoLists = new ArrayList<>();

    public List<ReviewItem> getMovieReviewList() {
        return movieVideoLists;
    }

}

