package com.example.patrickcummins.videogamepricetracker.Models.IGDBModels;

/**
 * Created by patrickcummins on 8/24/16.
 */




import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;


public class ReleaseDate {

    @SerializedName("category")
    @Expose
    private Integer category;
    @SerializedName("platform")
    @Expose
    private Integer platform;
    @SerializedName("date")
    @Expose
    private Integer date;

    /**
     * @return The category
     */
    public Integer getCategory() {
        return category;
    }

    /**
     * @param category The category
     */
    public void setCategory(Integer category) {
        this.category = category;
    }

    /**
     * @return The platform
     */
    public Integer getPlatform() {
        return platform;
    }

    /**
     * @param platform The platform
     */
    public void setPlatform(Integer platform) {
        this.platform = platform;
    }

    /**
     * @return The date
     */
    public Integer getDate() {
        return date;
    }

    /**
     * @param date The date
     */
    public void setDate(Integer date) {
        this.date = date;
    }

}
