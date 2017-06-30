package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 6/30/17.
 */

public class PopularCelebrity {

    @SerializedName("results")
    private List<Celebrity> celebrities = new ArrayList<>();

    public List<Celebrity> getCelebrities() {
        return celebrities;
    }

}
