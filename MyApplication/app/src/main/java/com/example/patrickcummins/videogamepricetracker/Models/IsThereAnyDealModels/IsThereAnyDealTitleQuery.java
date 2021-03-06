package com.example.patrickcummins.videogamepricetracker.Models.IsThereAnyDealModels;

/**
 * Created by patrickcummins on 8/23/16.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class IsThereAnyDealTitleQuery {

    @SerializedName(".meta")
    @Expose
    private Meta meta;
    @SerializedName("data")
    @Expose
    private Data data;

    /**
     *
     * @return
     * The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     * The .meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     *
     * @return
     * The data
     */
    public Data getData() {
        return data;
    }

    /**
     *
     * @param data
     * The data
     */
    public void setData(Data data) {
        this.data = data;
    }

}