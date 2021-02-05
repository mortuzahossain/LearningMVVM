package io.github.mortuzahossain.mvvm.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextWatcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.adapter.TvShowsAdapter;
import io.github.mortuzahossain.mvvm.adapter.WatchlistAdapter;
import io.github.mortuzahossain.mvvm.databinding.ActivitySearchBinding;
import io.github.mortuzahossain.mvvm.interfaces.TvShowClickListener;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;
import io.github.mortuzahossain.mvvm.viewmodels.MostPopularTvShowsViewModel;
import io.github.mortuzahossain.mvvm.viewmodels.SearchViewModel;
import io.github.mortuzahossain.mvvm.viewmodels.WishlistViewModel;

public class SearchActivity extends AppCompatActivity implements TvShowClickListener {

    private SearchViewModel viewModel;
    private ActivitySearchBinding binding;
    private List<TvShowsItem> tvShowsItems = new ArrayList<>();
    private TvShowsAdapter adapter;
    private int currentPage = 1;
    private int totalAvailablePages = 1;
    private String searchKeyword;
    private Timer timer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_search);
        InitUI();

    }

    private void InitUI() {
        initToolbar();
        binding.recyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(SearchViewModel.class);
        adapter = new TvShowsAdapter(tvShowsItems, this);
        binding.recyclerView.setAdapter(adapter);

        binding.etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (timer != null) {
                    timer.cancel();
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (!s.toString().trim().isEmpty()) {
                    timer = new Timer();
                    timer.schedule(new TimerTask() {
                        @Override
                        public void run() {
                            new Handler(Looper.getMainLooper()).post(new TimerTask() {
                                @Override
                                public void run() {
                                    currentPage = 1;
                                    totalAvailablePages = 1;
                                    searchKeyword = s.toString().trim();
                                    SearchTvShows();
                                }
                            });
                        }
                    }, 800);
                } else {
                    tvShowsItems.clear();
                    adapter.notifyDataSetChanged();
                }
            }
        });

        binding.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (!binding.recyclerView.canScrollVertically(1)) {
                    if (currentPage <= totalAvailablePages) {
                        currentPage += 1;
                        SearchTvShows();
                    }
                }
            }
        });

        binding.etSearch.forceLayout();
    }

    private void initToolbar() {
        setSupportActionBar(binding.toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
    }


    private void SearchTvShows() {
        toggleLoading();
        viewModel.searchTvShows(searchKeyword, currentPage).observe(this, popularResponse -> {
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
            binding.setIsLoading(binding.getIsLoading() == null || !binding.getIsLoading());
        } else {
            binding.setIsLoadingMore(binding.getIsLoadingMore() == null || !binding.getIsLoadingMore());
        }
    }

    @Override
    public void onTvShowClicked(TvShowsItem tvShow) {
        Intent intent = new Intent(SearchActivity.this, TvShowDetailsActivity.class);
        intent.putExtra("tvShow", tvShow);
        startActivity(intent);
    }
}