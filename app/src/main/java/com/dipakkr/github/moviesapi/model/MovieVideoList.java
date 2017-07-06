package com.dipakkr.github.moviesapi.model;

import com.facebook.Profile;
import com.google.android.gms.common.stats.StatsEvent;
import com.google.gson.annotations.SerializedName;

/**
 * Created by root on 7/3/17.
 */

public class MovieVideoList {
    @SerializedName("id")
    private String video_id;

    @SerializedName("iso_639_1")
    private String iso_639;

    @SerializedName("iso_3166_1")
    private String iso_3166;

    @SerializedName("name")
    private String video_name;

    @SerializedName("size")
    private String video_size;

    @SerializedName("type")
    private String video_type;

    @SerializedName("key")
    private String video_key;

    public MovieVideoList(String video_id, String iso_639, String iso_3166, String video_name, String video_size, String video_type, String video_key) {
        this.video_id = video_id;
        this.iso_639 = iso_639;
        this.iso_3166 = iso_3166;
        this.video_name = video_name;
        this.video_size = video_size;
        this.video_type = video_type;
        this.video_key = video_key;
    }

    public String getVideo_id() {
        return video_id;
    }

    public void setVideo_id(String video_id) {
        this.video_id = video_id;
    }

    public String getIso_639() {
        return iso_639;
    }

    public void setIso_639(String iso_639) {
        this.iso_639 = iso_639;
    }

    public String getIso_3166() {
        return iso_3166;
    }

    public void setIso_3166(String iso_3166) {
        this.iso_3166 = iso_3166;
    }

    public String getVideo_name() {
        return video_name;
    }

    public void setVideo_name(String video_name) {
        this.video_name = video_name;
    }

    public String getVideo_size() {
        return video_size;
    }

    public void setVideo_size(String video_size) {
        this.video_size = video_size;
    }

    public String getVideo_type() {
        return video_type;
    }

    public void setVideo_type(String video_type) {
        this.video_type = video_type;
    }

    public String getVideo_key() {
        return video_key;
    }

    public void setVideo_key(String video_key) {
        this.video_key = video_key;
    }
}
