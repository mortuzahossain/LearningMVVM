package io.github.mortuzahossain.mvvm.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import io.github.mortuzahossain.mvvm.models.TvShowDetailsResponse;
import io.github.mortuzahossain.mvvm.repositores.TvShowDetailsRepository;

/**
 * Created by Mortuza Hossain on 03-Feb-2021
 * Email : mortuzahossain1997@gmail.com
 **/
public class TvShowDetailsViewModel extends ViewModel {
    private TvShowDetailsRepository tvShowDetailsRepository;

    public TvShowDetailsViewModel() {
        tvShowDetailsRepository = new TvShowDetailsRepository();
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(String tvShowId){
        return tvShowDetailsRepository.getDetailsOfTvShow(tvShowId);
    }
}
