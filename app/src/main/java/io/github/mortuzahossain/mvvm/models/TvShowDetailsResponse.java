package io.github.mortuzahossain.mvvm.models;

import com.google.gson.annotations.SerializedName;

public class TvShowDetailsResponse{

	@SerializedName("tvShow")
	private TvShow tvShow;

	public TvShow getTvShow(){
		return tvShow;
	}
}