package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by root on 6/30/17.
 */



public class Celebrity {

    @SerializedName("profile_path")
    private String mImagePath;

    @SerializedName("name")
    private String name;

   @SerializedName("id")
    private String id;

    /*@SerializedName("popularity")
    private int rating;

    @SerializedName("known_for")
    private List<Movie> movies = new ArrayList<>();*/

    public Celebrity(String mImagePath, String name) {
        this.mImagePath = mImagePath;
        this.name = name;

    }

    public String getId() {
        return id;
    }

    public String getmImagePath() {
        return mImagePath;
    }

    public void setmImagePath(String mImagePath) {
        this.mImagePath = mImagePath;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}


