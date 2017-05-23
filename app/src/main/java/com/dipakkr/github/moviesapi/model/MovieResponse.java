package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak on 5/23/17.
 */

public class MovieResponse {
    @SerializedName("pages")
    private int pages;

    @SerializedName("results")
    private List<Movie> movies = new ArrayList<>();

    @SerializedName("total_results")
    private int totalResults;

    @SerializedName("total_pages")
    private int totalPages;
}
