package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 7/18/17.
 */

public class TvResponse {

    @SerializedName("results")
    private List<TvShow> tv = new ArrayList<>();



}
