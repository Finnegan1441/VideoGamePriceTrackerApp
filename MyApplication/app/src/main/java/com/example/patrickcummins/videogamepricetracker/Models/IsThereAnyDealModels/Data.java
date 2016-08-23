package com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels;

/**
 * Created by patrickcummins on 8/23/16.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class Data {

    @SerializedName("plain")
    @Expose
    private String plain;

    /**
     *
     * @return
     * The plain
     */
    public String getPlain() {
        return plain;
    }

    /**
     *
     * @param plain
     * The plain
     */
    public void setPlain(String plain) {
        this.plain = plain;
    }

}
