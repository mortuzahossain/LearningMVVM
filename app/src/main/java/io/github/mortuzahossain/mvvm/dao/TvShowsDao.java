package io.github.mortuzahossain.mvvm.dao;
/*
 * Created by mortuza on 4/2/21 | 7:46 PM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

import io.github.mortuzahossain.mvvm.models.TvShowsItem;
import io.reactivex.Completable;
import io.reactivex.Flowable;

@Dao
public interface TvShowsDao {

    @Query("Select * from tvShows")
    Flowable<List<TvShowsItem>> getAllTvShows();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    Completable addToWatchlist(TvShowsItem tvShowsItem);

    @Delete
    Completable removeFromWatchlist(TvShowsItem tvShowsItem);

    @Query("Select * from tvShows where id=:showId")
    Flowable<TvShowsItem> getAllTvShow(String showId);
}
