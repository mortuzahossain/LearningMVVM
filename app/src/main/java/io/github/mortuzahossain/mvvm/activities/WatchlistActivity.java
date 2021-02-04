package io.github.mortuzahossain.mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.nfc.cardemulation.OffHostApduService;
import android.os.Bundle;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.adapter.WatchlistAdapter;
import io.github.mortuzahossain.mvvm.databinding.ActivityWatchlistBinding;
import io.github.mortuzahossain.mvvm.interfaces.WatchlistListener;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;
import io.github.mortuzahossain.mvvm.viewmodels.TvShowDetailsViewModel;
import io.github.mortuzahossain.mvvm.viewmodels.WishlistViewModel;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;

import static io.github.mortuzahossain.mvvm.database.TempData.isWatchlistUpdated;

public class WatchlistActivity extends AppCompatActivity implements WatchlistListener {

    ActivityWatchlistBinding watchlistBinding;
    WishlistViewModel viewModel;
    WatchlistAdapter watchlistAdapter;
    List<TvShowsItem> tvShowsItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        watchlistBinding = DataBindingUtil.setContentView(this, R.layout.activity_watchlist);
        watchlistAdapter = new WatchlistAdapter(tvShowsItems, this);
        initUI();
    }

    private void initUI() {
        setSupportActionBar(watchlistBinding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        viewModel = new ViewModelProvider.AndroidViewModelFactory(getApplication()).create(WishlistViewModel.class);
        watchlistBinding.recyclerView.setAdapter(watchlistAdapter);
        loadWishList();
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (isWatchlistUpdated) {
            loadWishList();
            isWatchlistUpdated = false;
        }
    }

    private void loadWishList() {
        watchlistBinding.setIsLoading(true);
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(viewModel.loadWishList()
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(tvShowsItems -> {
                    if (this.tvShowsItems.size() > 0) {
                        this.tvShowsItems.clear();
                    }
                    this.tvShowsItems.addAll(tvShowsItems);
                    watchlistAdapter.notifyDataSetChanged();
                    watchlistBinding.setIsLoading(false);
                    disposable.dispose();
                })
        );


    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onTvShowClicked(TvShowsItem tvShowsItem) {
        Intent intent = new Intent(WatchlistActivity.this, TvShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShowsItem);
        startActivity(intent);
    }

    @Override
    public void removeTvShowFromList(TvShowsItem tvShowsItem, int position) {
        CompositeDisposable disposable = new CompositeDisposable();
        disposable.add(viewModel.removeTvShowFromWatchList(tvShowsItem)
                .subscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(() -> {
                    tvShowsItems.remove(position);
                    watchlistAdapter.notifyItemRemoved(position);
                    watchlistAdapter.notifyItemRangeChanged(position, tvShowsItems.size());
                    disposable.dispose();
                    isWatchlistUpdated = true;
                })
        );
    }
}