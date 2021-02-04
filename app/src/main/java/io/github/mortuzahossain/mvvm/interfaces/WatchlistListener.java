package io.github.mortuzahossain.mvvm.interfaces;
/*
 * Created by mortuza on 5/2/21 | 1:06 AM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import io.github.mortuzahossain.mvvm.models.TvShow;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;

public interface WatchlistListener {
    void onTvShowClicked(TvShowsItem tvShowsItem);
    void removeTvShowFromList(TvShowsItem tvShowsItem,int position);
}
