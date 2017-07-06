package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 7/6/17.
 */

public class ReviewItem {

    @SerializedName("id")
    private String id;

    @SerializedName("author")
    private String author;

    @SerializedName("content")
    private String content;

    @SerializedName("url")
    private String mUrl;

    public ReviewItem(String id, String author, String content, String mUrl) {
        this.id = id;
        this.author = author;
        this.content = content;
        this.mUrl = mUrl;
    }

    public String getReviewId() {
        return id;
    }

    public String getAuthor() {
        return author;
    }

    public String getReviewContent() {
        return content;
    }

    public String getmUrl() {
        return mUrl;
    }
}
