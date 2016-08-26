package com.example.patrickcummins.videogamepricetracker.Models.GiantBombModels;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by patrickcummins on 8/25/16.
 */
public class GiantBombImage implements Serializable{

    @SerializedName("icon_url")
    @Expose
    private String icon_url;

    @SerializedName("small_url")
    @Expose
    private String small_url;

    @SerializedName("medium_url")
    @Expose
    private String medium_url;

    public String getIcon_url() {
        return icon_url;
    }

    public void setIcon_url(String icon_url) {
        this.icon_url = icon_url;
    }

    public String getSmall_url() {
        return small_url;
    }

    public void setSmall_url(String small_url) {
        this.small_url = small_url;
    }

    public String getMedium_url() {
        return medium_url;
    }

    public void setMedium_url(String medium_url) {
        this.medium_url = medium_url;
    }
}
