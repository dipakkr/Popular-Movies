package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by deepak on 5/23/17.
 */

public class Movie {

    @SerializedName("poster_path")
    private String posterPath;

    @SerializedName("original_name")
    private String name;

    @SerializedName("adult")
    private boolean adult ;

    @SerializedName("overview")
    private String overview;

    @SerializedName("release_date")
    private String releaseDate;

    @SerializedName("genre_ids")
    private List<Integer> genreId = new ArrayList<>();

    @SerializedName("id")
    private String Id;

    @SerializedName("original_title")
    private String title;

    @SerializedName("original_language")
    private String orginalLang;

    @SerializedName("backdrop_path")
    private String backdropPath;

    @SerializedName("popularity")
    private Double popularity;

    @SerializedName("vote_count")
    private Integer voteCount;

    @SerializedName("video")
    private Boolean video;

    @SerializedName("vote_average")
    private Double voteAverage;

    public Movie(String posterPath, boolean adult, String overview, String releaseDate, List<Integer> genreId,
                         String id, String title, String orginalLang, String backdropPath, Double popularity,
                         Integer voteCount, Boolean video, Double voteAverage) {

        this.posterPath = posterPath;
        this.adult = adult;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.genreId = genreId;
        Id = id;
        this.title = title;
        this.orginalLang = orginalLang;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.video = video;
        this.voteAverage = voteAverage;
    }


    public Movie(String posterPath, String name, String overview, String id, String orginalLang, String backdropPath, Double popularity, Integer voteCount, Double voteAverage) {
        this.posterPath = posterPath;
        this.name = name;
        this.overview = overview;
        Id = id;
        this.orginalLang = orginalLang;
        this.backdropPath = backdropPath;
        this.popularity = popularity;
        this.voteCount = voteCount;
        this.voteAverage = voteAverage;
    }

    public String getName() {
        return name;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public void setAdult(boolean adult) {
        this.adult = adult;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public void setGenreId(List<Integer> genreId) {
        this.genreId = genreId;
    }

    public void setId(String id) {
        Id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setOrginalLang(String orginalLang) {
        this.orginalLang = orginalLang;
    }

    public void setBackdropPath(String backdropPath) {
        this.backdropPath = backdropPath;
    }

    public void setPopularity(Double popularity) {
        this.popularity = popularity;
    }

    public void setVoteCount(Integer voteCount) {
        this.voteCount = voteCount;
    }

    public void setVideo(Boolean video) {
        this.video = video;
    }

    public void setVoteAverage(Double voteAverage) {
        this.voteAverage = voteAverage;
    }

    public String getPosterPath() {

        return posterPath;
    }

    public boolean isAdult() {
        return adult;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public List<Integer> getGenreId() {
        return genreId;
    }

    public String getId() {
        return Id;
    }

    public String getTitle() {
        return title;
    }

    public String getOrginalLang() {
        return orginalLang;
    }

    public String getBackdropPath() {
        return backdropPath;
    }

    public Double getPopularity() {
        return popularity;
    }

    public Integer getVoteCount() {
        return voteCount;
    }

    public Boolean getVideo() {
        return video;
    }

    public Double getVoteAverage() {
        return voteAverage;
    }
}
