package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by deepak on 6/13/17.
 */

public class MovieDescription {
    @SerializedName("original_language")
    private String movie_lang;

    @SerializedName("original_title")
    private String movie_title;

    @SerializedName("overview")
    private String movie_overview;

    @SerializedName("release_date")
    private String movie_release_date;

    @SerializedName("runtime")
    private int movie_runtime;

    @SerializedName("tagline")
    private String movie_tagline;

    @SerializedName("vote_average")
    private String moveie_avg_vote;

    public MovieDescription(String movie_lang, String movie_title, String movie_overview,
                            String movie_release_date, int movie_runtime, String movie_tagline,
                            String moveie_avg_vote) {
        this.movie_lang = movie_lang;
        this.movie_title = movie_title;
        this.movie_overview = movie_overview;
        this.movie_release_date = movie_release_date;
        this.movie_runtime = movie_runtime;
        this.movie_tagline = movie_tagline;
        this.moveie_avg_vote = moveie_avg_vote;
    }

    public String getMovie_lang() {
        return movie_lang;
    }

    public void setMovie_lang(String movie_lang) {
        this.movie_lang = movie_lang;
    }

    public String getMovie_title() {
        return movie_title;
    }

    public void setMovie_title(String movie_title) {
        this.movie_title = movie_title;
    }

    public String getMovie_overview() {
        return movie_overview;
    }

    public void setMovie_overview(String movie_overview) {
        this.movie_overview = movie_overview;
    }

    public String getMovie_release_date() {
        return movie_release_date;
    }

    public void setMovie_release_date(String movie_release_date) {
        this.movie_release_date = movie_release_date;
    }

    public int getMovie_runtime() {
        return movie_runtime;
    }

    public void setMovie_runtime(int movie_runtime) {
        this.movie_runtime = movie_runtime;
    }

    public String getMovie_tagline() {
        return movie_tagline;
    }

    public void setMovie_tagline(String movie_tagline) {
        this.movie_tagline = movie_tagline;
    }

    public String getMoveie_avg_vote() {
        return moveie_avg_vote;
    }

    public void setMoveie_avg_vote(String moveie_avg_vote) {
        this.moveie_avg_vote = moveie_avg_vote;
    }
}
