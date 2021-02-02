package io.github.mortuzahossain.mvvm.models;

import com.google.gson.annotations.SerializedName;

public class TvShowsItem{

	@SerializedName("end_date")
	private Object endDate;

	@SerializedName("country")
	private String country;

	@SerializedName("image_thumbnail_path")
	private String imageThumbnailPath;

	@SerializedName("name")
	private String name;

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

	public Object getEndDate(){
		return endDate;
	}

	public String getCountry(){
		return country;
	}

	public String getImageThumbnailPath(){
		return imageThumbnailPath;
	}

	public String getName(){
		return name;
	}

	public int getId(){
		return id;
	}

	public String getPermalink(){
		return permalink;
	}

	public String getStartDate(){
		return startDate;
	}

	public String getNetwork(){
		return network;
	}

	public String getStatus(){
		return status;
	}
}