package io.github.mortuzahossain.mvvm.models;

import com.google.gson.annotations.SerializedName;

public class Countdown{

	@SerializedName("air_date")
	private String airDate;

	@SerializedName("name")
	private String name;

	@SerializedName("season")
	private int season;

	@SerializedName("episode")
	private int episode;

	public String getAirDate(){
		return airDate;
	}

	public String getName(){
		return name;
	}

	public int getSeason(){
		return season;
	}

	public int getEpisode(){
		return episode;
	}
}