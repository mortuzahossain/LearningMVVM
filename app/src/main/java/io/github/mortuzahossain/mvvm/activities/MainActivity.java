package io.github.mortuzahossain.mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.adapter.TvShowsAdapter;
import io.github.mortuzahossain.mvvm.databinding.ActivityMainBinding;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;
import io.github.mortuzahossain.mvvm.viewmodels.MostPopularTvShowsViewModel;

public class MainActivity extends AppCompatActivity {

    MostPopularTvShowsViewModel viewModel;
    ActivityMainBinding activityMainBinding;
    List<TvShowsItem> tvShowsItems = new ArrayList<>();
    TvShowsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        Init();
    }

    private void Init(){
        activityMainBinding.recyclerView.setHasFixedSize(true);
        viewModel = new ViewModelProvider(this).get(MostPopularTvShowsViewModel.class);
        adapter = new TvShowsAdapter(tvShowsItems);
        activityMainBinding.recyclerView.setAdapter(adapter);
        getMostPopularTvShows();
    }

    private void getMostPopularTvShows() {
        activityMainBinding.setIsLoading(true);
        viewModel.getMostPopularTvShows(0).observe(this, popularResponse -> {
            activityMainBinding.setIsLoading(false);
            if (popularResponse != null){
                if (popularResponse.getTvShows() != null){
                    tvShowsItems.addAll(popularResponse.getTvShows());
                    adapter.notifyDataSetChanged();
                }
            }
        });
    }

}