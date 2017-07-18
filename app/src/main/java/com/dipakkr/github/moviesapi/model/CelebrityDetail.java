package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by deepak on 7/7/17.
 */

public class CelebrityDetail {
    @SerializedName("biography")
    private String bio;

    @SerializedName("birthday")
    private String birthday;

    @SerializedName("homepgae")
    private String homepageUrl;

    @SerializedName("deathday")
    private String death;

    @SerializedName("place_of_birth")
    private String place;

    @SerializedName("name")
    private String name;

    @SerializedName("profile_path")
    private String image;

    @SerializedName("popularity")
    private String popularity;

    public CelebrityDetail(String bio, String birthday, String homepageUrl, String death, String place, String name, String image, String popularity) {
        this.bio = bio;
        this.birthday = birthday;
        this.homepageUrl = homepageUrl;
        this.death = death;
        this.place = place;
        this.name = name;
        this.image = image;
        this.popularity = popularity;
    }

    public String getBio() {
        return bio;
    }

    public String getBirthday() {
        return birthday;
    }

    public String getHomepageUrl() {
        return homepageUrl;
    }

    public String getDeath() {
        return death;
    }

    public String getPlace() {
        return place;
    }

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }

    public String getPopularity() {
        return popularity;
    }
}
