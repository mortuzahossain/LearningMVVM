package io.github.mortuzahossain.mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.databinding.ActivityMainBinding;
import io.github.mortuzahossain.mvvm.databinding.ActivityTvShowDetailsBinding;
import io.github.mortuzahossain.mvvm.models.TvShowDetailsResponse;
import io.github.mortuzahossain.mvvm.viewmodels.MostPopularTvShowsViewModel;
import io.github.mortuzahossain.mvvm.viewmodels.TvShowDetailsViewModel;

public class TvShowDetailsActivity extends AppCompatActivity {

    private ActivityTvShowDetailsBinding activityDetailsBinding;
    private TvShowDetailsViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details);
        InitUi();
    }

    private void InitUi() {
        viewModel = new ViewModelProvider(this).get(TvShowDetailsViewModel.class);
        getTvShowDetails();
    }

    private void getTvShowDetails() {
        activityDetailsBinding.setIsLoading(true);
        int tvShowId = getIntent().getIntExtra("ID",-1);
        viewModel.getTvShowDetails(String.valueOf(tvShowId)).observe(this, new Observer<TvShowDetailsResponse>() {
            @Override
            public void onChanged(TvShowDetailsResponse tvShowDetailsResponse) {
                activityDetailsBinding.setIsLoading(false);
                Toast.makeText(TvShowDetailsActivity.this, tvShowDetailsResponse.getTvShow().getImagePath(),
                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}