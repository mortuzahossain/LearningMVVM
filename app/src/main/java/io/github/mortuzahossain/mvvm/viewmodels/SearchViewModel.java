package io.github.mortuzahossain.mvvm.viewmodels;
/*
 * Created by mortuza on 5/2/21 | 4:35 PM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import io.github.mortuzahossain.mvvm.models.PopularResponse;
import io.github.mortuzahossain.mvvm.repositores.SearchTvShowsRepository;

public class SearchViewModel extends ViewModel {
    SearchTvShowsRepository repository;

    public SearchViewModel() {
        repository = new SearchTvShowsRepository();
    }

    public LiveData<PopularResponse> searchTvShows(String searchKey, int pages) {
        return repository.searchTvShows(searchKey, pages);
    }
}
