package io.github.mortuzahossain.mvvm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.adapter.TvShowsAdapter;
import io.github.mortuzahossain.mvvm.databinding.ActivityMainBinding;
import io.github.mortuzahossain.mvvm.interfaces.TvShowClickListener;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;
import io.github.mortuzahossain.mvvm.viewmodels.MostPopularTvShowsViewModel;

public class MainActivity extends AppCompatActivity implements TvShowClickListener {

    private MostPopularTvShowsViewModel viewModel;
    private ActivityMainBinding activityMainBinding;
    private List<TvShowsItem> tvShowsItems = new ArrayList<>();
    private TvShowsAdapter adapter;
    private int currentPage = 1;
    private int totalAvailablePages = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Init();
    }

    private void Init() {
        activityMainBinding.recyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTvShowsViewModel.class);
        adapter = new TvShowsAdapter(tvShowsItems, this);
        activityMainBinding.recyclerView.setAdapter(adapter);

        activityMainBinding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!activityMainBinding.recyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1;
                        getMostPopularTvShows();
                    }
                }
            }
        });

        getMostPopularTvShows();
    }

    private void getMostPopularTvShows() {
        toggleLoading();
        viewModel.getMostPopularTvShows(currentPage).observe(this, popularResponse -> {
            toggleLoading();
            if (popularResponse != null) {
                totalAvailablePages = popularResponse.getPages();
                if (popularResponse.getTvShows() != null) {
                    int oldCount = tvShowsItems.size();
                    tvShowsItems.addAll(popularResponse.getTvShows());
                    adapter.notifyDataSetChanged();
                    adapter.notifyItemChanged(oldCount, tvShowsItems.size());
                }
            }
        });
    }

    private void toggleLoading() {
        if (currentPage == 1) {
            activityMainBinding.setIsLoading(activityMainBinding.getIsLoading() == null || !activityMainBinding.getIsLoading());
        } else {
            activityMainBinding.setIsLoadingMore(activityMainBinding.getIsLoadingMore() == null || !activityMainBinding.getIsLoadingMore());
        }
    }

    @Override
    public void onTvShowClicked(TvShowsItem tvShow) {
        Intent intent = new Intent(MainActivity.this, TvShowDetailsActivity.class);
        intent.putExtra("ID", tvShow.getId());
        intent.putExtra("Name", tvShow.getName());
        intent.putExtra("StartDate", tvShow.getStartDate());
        intent.putExtra("Country", tvShow.getCountry());
        intent.putExtra("Network", tvShow.getNetwork());
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }
}