package com.dipakkr.github.moviesapi.model;

import com.google.gson.annotations.SerializedName;
import com.google.gson.internal.Streams;

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

    public static  class ReviewItem{
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
}

