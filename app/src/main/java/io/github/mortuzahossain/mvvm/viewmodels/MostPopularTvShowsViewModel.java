package io.github.mortuzahossain.mvvm.viewmodels;
/*
 * Created by mortuza on 3/2/21 | 12:49 AM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import io.github.mortuzahossain.mvvm.models.PopularResponse;
import io.github.mortuzahossain.mvvm.repositores.PopularTvShowsRepository;

public class MostPopularTvShowsViewModel extends ViewModel {
    private PopularTvShowsRepository popularTvShowsRepository;

    public MostPopularTvShowsViewModel() {
        popularTvShowsRepository = new PopularTvShowsRepository();
    }

    public LiveData<PopularResponse> getMostPopularTvShows(int pages) {
        return popularTvShowsRepository.getMostPopularTvShows(pages);
    }
}
