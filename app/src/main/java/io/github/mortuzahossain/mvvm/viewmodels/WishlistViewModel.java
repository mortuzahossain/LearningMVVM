package io.github.mortuzahossain.mvvm.viewmodels;
/*
 * Created by mortuza on 4/2/21 | 11:28 PM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import java.util.List;

import io.github.mortuzahossain.mvvm.database.TvShowsDatabase;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;
import io.reactivex.Completable;
import io.reactivex.Flowable;

public class WishlistViewModel extends AndroidViewModel {

    private final TvShowsDatabase database;

    public WishlistViewModel(@NonNull Application application) {
        super(application);
        database = TvShowsDatabase.getTvShowsDatabase(application);
    }

    public Flowable<List<TvShowsItem>> loadWishList() {
        return database.getTvShowsDao().getAllTvShows();
    }

    public Completable removeTvShowFromWatchList(TvShowsItem tvShowsItem) {
        return database.getTvShowsDao().removeFromWatchlist(tvShowsItem);
    }

}
