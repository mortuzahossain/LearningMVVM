package io.github.mortuzahossain.mvvm.models;

import java.util.List;
import com.google.gson.annotations.SerializedName;

public class TvShow{

	@SerializedName("end_date")
	private Object endDate;

	@SerializedName("country")
	private String country;

	@SerializedName("rating")
	private String rating;

	@SerializedName("countdown")
	private Countdown countdown;

	@SerializedName("description")
	private String description;

	@SerializedName("runtime")
	private int runtime;

	@SerializedName("url")
	private String url;

	@SerializedName("pictures")
	private List<String> pictures;

	@SerializedName("network")
	private String network;

	@SerializedName("rating_count")
	private String ratingCount;

	@SerializedName("youtube_link")
	private Object youtubeLink;

	@SerializedName("image_thumbnail_path")
	private String imageThumbnailPath;

	@SerializedName("image_path")
	private String imagePath;

	@SerializedName("genres")
	private List<String> genres;

	@SerializedName("description_source")
	private String descriptionSource;

	@SerializedName("name")
	private String name;

	@SerializedName("id")
	private int id;

	@SerializedName("permalink")
	private String permalink;

	@SerializedName("episodes")
	private List<EpisodesItem> episodes;

	@SerializedName("start_date")
	private String startDate;

	@SerializedName("status")
	private String status;

	public Object getEndDate(){
		return endDate;
	}

	public String getCountry(){
		return country;
	}

	public String getRating(){
		return rating;
	}

	public Countdown getCountdown(){
		return countdown;
	}

	public String getDescription(){
		return description;
	}

	public int getRuntime(){
		return runtime;
	}

	public String getUrl(){
		return url;
	}

	public List<String> getPictures(){
		return pictures;
	}

	public String getNetwork(){
		return network;
	}

	public String getRatingCount(){
		return ratingCount;
	}

	public Object getYoutubeLink(){
		return youtubeLink;
	}

	public String getImageThumbnailPath(){
		return imageThumbnailPath;
	}

	public String getImagePath(){
		return imagePath;
	}

	public List<String> getGenres(){
		return genres;
	}

	public String getDescriptionSource(){
		return descriptionSource;
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

	public List<EpisodesItem> getEpisodes(){
		return episodes;
	}

	public String getStartDate(){
		return startDate;
	}

	public String getStatus(){
		return status;
	}
}