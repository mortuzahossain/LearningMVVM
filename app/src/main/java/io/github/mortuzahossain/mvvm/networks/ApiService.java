package io.github.mortuzahossain.mvvm.networks;
/*
 * Created by mortuza on 3/2/21 | 12:40 AM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import io.github.mortuzahossain.mvvm.models.PopularResponse;
import io.github.mortuzahossain.mvvm.models.TvShowDetailsResponse;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("most-popular")
    Call<PopularResponse> getPopularTvShows(@Query("page") int page);

    @GET("show-details")
    Call<TvShowDetailsResponse> getTvShowDetails(@Query("q") String postId);

}
