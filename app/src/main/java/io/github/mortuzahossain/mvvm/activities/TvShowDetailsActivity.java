package io.github.mortuzahossain.mvvm.activities;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.adapter.ImageSliderAdapter;
import io.github.mortuzahossain.mvvm.databinding.ActivityTvShowDetailsBinding;
import io.github.mortuzahossain.mvvm.models.TvShowDetailsResponse;
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

                if (tvShowDetailsResponse != null){
                    if (tvShowDetailsResponse.getTvShow() != null){
                        if (tvShowDetailsResponse.getTvShow().getPictures() != null){
                            loadImageSliders(tvShowDetailsResponse.getTvShow().getPictures());
                        }
                    }
                }
            }
        });
    }

    private void loadImageSliders(String[] images){
        activityDetailsBinding.sliderViewPager.setOffscreenPageLimit(1);
        activityDetailsBinding.sliderViewPager.setAdapter(new ImageSliderAdapter(images));
        activityDetailsBinding.sliderViewPager.setVisibility(View.VISIBLE);
        activityDetailsBinding.viewFadingEdge.setVisibility(View.VISIBLE);
        setupSliderIndicator(images.length);
        setCurrentSliderIndicator(0);
        activityDetailsBinding.sliderViewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                setCurrentSliderIndicator(position);
            }
        });
    }

    private void setupSliderIndicator(int count){
        ImageView[] indicator = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8,0,8,0);
        for (int i = 0;i<count;i++){
            indicator[i] = new ImageView(getApplicationContext());
            indicator[i].setImageDrawable(ContextCompat.getDrawable(
                    getApplicationContext(),
                    R.drawable.background_slider_indicator_inactieve
            ));
            indicator[i].setLayoutParams(layoutParams);
            activityDetailsBinding.layoutSliderIndicator.addView(indicator[i]);
        }
        activityDetailsBinding.layoutSliderIndicator.setVisibility(View.VISIBLE);
    }

    private void setCurrentSliderIndicator(int position){
        int childCount = activityDetailsBinding.layoutSliderIndicator.getChildCount();
        for (int i=0;i<childCount;i++){
            ImageView imageView =
                    (ImageView) activityDetailsBinding.layoutSliderIndicator.getChildAt(i);
            if (i == position){
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.background_slider_indicator_actieve));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.background_slider_indicator_inactieve));
            }
        }
    }


}