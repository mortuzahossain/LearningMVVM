package io.github.mortuzahossain.mvvm.repositores;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.github.mortuzahossain.mvvm.models.PopularResponse;
import io.github.mortuzahossain.mvvm.models.TvShowDetailsResponse;
import io.github.mortuzahossain.mvvm.networks.ApiClients;
import io.github.mortuzahossain.mvvm.networks.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Mortuza Hossain on 03-Feb-2021
 * Email : mortuzahossain1997@gmail.com
 **/
public class TvShowDetailsRepository {
    private ApiService apiService;

    public TvShowDetailsRepository() {
        apiService = ApiClients.getRetrofit().create(ApiService.class);
    }

    public LiveData<TvShowDetailsResponse> getDetailsOfTvShow(String postId){
        MutableLiveData<TvShowDetailsResponse> data = new MutableLiveData<>();
        apiService.getTvShowDetails(postId).enqueue(new Callback<TvShowDetailsResponse>() {
            @Override
            public void onResponse(Call<TvShowDetailsResponse> call, Response<TvShowDetailsResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<TvShowDetailsResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
