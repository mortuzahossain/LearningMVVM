package io.github.mortuzahossain.mvvm.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class PopularResponse{

	@SerializedName("tv_shows")
	private List<TvShowsItem> tvShows;

	@SerializedName("total")
	private String total;

	@SerializedName("pages")
	private int pages;

	@SerializedName("page")
	private int page;

	public List<TvShowsItem> getTvShows(){
		return tvShows;
	}

	public String getTotal(){
		return total;
	}

	public int getPages(){
		return pages;
	}

	public int getPage(){
		return page;
	}
}