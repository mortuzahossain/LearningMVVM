package io.github.mortuzahossain.mvvm.activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.models.PopularResponse;
import io.github.mortuzahossain.mvvm.viewmodels.MostPopularTvShowsViewModel;

public class MainActivity extends AppCompatActivity {

    MostPopularTvShowsViewModel mostPopularTvShowsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mostPopularTvShowsViewModel =
                new ViewModelProvider(this).get(MostPopularTvShowsViewModel.class);
        Toast.makeText(this, "Loading" , Toast.LENGTH_LONG).show();
        getMostPopularTvShows();

    }

    private void getMostPopularTvShows() {
        mostPopularTvShowsViewModel.getMostPopularTvShows(0).observe(this, popularResponse -> {
            Log.d("AAAA", "getMostPopularTvShows: "+popularResponse.getPages());
            Toast.makeText(this, "Page" + popularResponse.getPages(), Toast.LENGTH_LONG).show();
        });
    }

}