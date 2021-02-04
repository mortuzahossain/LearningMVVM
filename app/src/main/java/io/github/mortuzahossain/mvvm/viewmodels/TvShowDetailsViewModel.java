package io.github.mortuzahossain.mvvm.viewmodels;

import android.app.Application;

import androidx.annotation.Nullable;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import io.github.mortuzahossain.mvvm.database.TvShowsDatabase;
import io.github.mortuzahossain.mvvm.models.TvShowDetailsResponse;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;
import io.github.mortuzahossain.mvvm.repositores.TvShowDetailsRepository;
import io.reactivex.Completable;

/**
 * Created by Mortuza Hossain on 03-Feb-2021
 * Email : mortuzahossain1997@gmail.com
 **/
public class TvShowDetailsViewModel extends AndroidViewModel {
    private TvShowDetailsRepository tvShowDetailsRepository;
    private TvShowsDatabase tvShowsDatabase;

    public TvShowDetailsViewModel(@Nullable Application application) {
        super(application);
        tvShowDetailsRepository = new TvShowDetailsRepository();
        tvShowsDatabase = TvShowsDatabase.getTvShowsDatabase(application);
    }

    public LiveData<TvShowDetailsResponse> getTvShowDetails(String tvShowId) {
        return tvShowDetailsRepository.getDetailsOfTvShow(tvShowId);
    }

    public Completable addToWatchlist(TvShowsItem tvShowsItem) {
        return tvShowsDatabase.getTvShowsDao().addToWatchlist(tvShowsItem);
    }
}
