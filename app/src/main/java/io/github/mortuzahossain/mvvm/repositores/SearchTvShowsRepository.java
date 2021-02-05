package io.github.mortuzahossain.mvvm.repositores;
/*
 * Created by mortuza on 5/2/21 | 4:36 PM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import io.github.mortuzahossain.mvvm.models.PopularResponse;
import io.github.mortuzahossain.mvvm.networks.ApiClients;
import io.github.mortuzahossain.mvvm.networks.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SearchTvShowsRepository {
    ApiService apiService;

    public SearchTvShowsRepository() {
        apiService = ApiClients.getRetrofit().create(ApiService.class);
    }

    public LiveData<PopularResponse> searchTvShows(String searchKeyword, int pages) {
        MutableLiveData<PopularResponse> data = new MutableLiveData<>();
        apiService.searchTvShows(searchKeyword, pages).enqueue(new Callback<PopularResponse>() {
            @Override
            public void onResponse(Call<PopularResponse> call, Response<PopularResponse> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PopularResponse> call, Throwable t) {
                data.setValue(null);
            }
        });
        return data;
    }
}
