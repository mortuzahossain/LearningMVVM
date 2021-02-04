package io.github.mortuzahossain.mvvm.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Entity(tableName = "tvShows")
public class TvShowsItem implements Serializable {


    @SerializedName("country")
    private String country;

    @SerializedName("image_thumbnail_path")
    private String imageThumbnailPath;

    @SerializedName("name")
    private String name;

    @PrimaryKey
    @SerializedName("id")
    private int id;

    @SerializedName("permalink")
    private String permalink;

    @SerializedName("start_date")
    private String startDate;

    @SerializedName("network")
    private String network;

    @SerializedName("status")
    private String status;


    public String getCountry() {
        return country;
    }

    public String getImageThumbnailPath() {
        return imageThumbnailPath;
    }

    public String getName() {
        return name;
    }

    public int getId() {
        return id;
    }

    public String getPermalink() {
        return permalink;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getNetwork() {
        return network;
    }

    public String getStatus() {
        return status;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setImageThumbnailPath(String imageThumbnailPath) {
        this.imageThumbnailPath = imageThumbnailPath;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setPermalink(String permalink) {
        this.permalink = permalink;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public void setNetwork(String network) {
        this.network = network;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}