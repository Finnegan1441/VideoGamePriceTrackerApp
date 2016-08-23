package com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels;

/**
 * Created by patrickcummins on 8/23/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Meta {

    @SerializedName("match")
    @Expose
    private String match;
    @SerializedName("active")
    @Expose
    private Boolean active;

    /**
     *
     * @return
     * The match
     */
    public String getMatch() {
        return match;
    }

    /**
     *
     * @param match
     * The match
     */
    public void setMatch(String match) {
        this.match = match;
    }

    /**
     *
     * @return
     * The active
     */
    public Boolean getActive() {
        return active;
    }

    /**
     *
     * @param active
     * The active
     */
    public void setActive(Boolean active) {
        this.active = active;
    }

}

