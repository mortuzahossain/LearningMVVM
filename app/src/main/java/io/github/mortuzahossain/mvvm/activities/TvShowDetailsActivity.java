package io.github.mortuzahossain.mvvm.activities;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.core.text.HtmlCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.util.Locale;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.adapter.EpisodeAdapter;
import io.github.mortuzahossain.mvvm.adapter.ImageSliderAdapter;
import io.github.mortuzahossain.mvvm.databinding.ActivityTvShowDetailsBinding;
import io.github.mortuzahossain.mvvm.databinding.LayoutEpisodeBottomSheetBinding;
import io.github.mortuzahossain.mvvm.models.TvShowDetailsResponse;
import io.github.mortuzahossain.mvvm.utils.IntentUtils;
import io.github.mortuzahossain.mvvm.viewmodels.TvShowDetailsViewModel;

public class TvShowDetailsActivity extends AppCompatActivity {

    private ActivityTvShowDetailsBinding activityDetailsBinding;
    private TvShowDetailsViewModel viewModel;
    private BottomSheetDialog episodeBottomSheetDialog;
    private LayoutEpisodeBottomSheetBinding layoutEpisodeBottomSheetBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_tv_show_details);
        InitUi();
    }

    private void InitUi() {
        viewModel = new ViewModelProvider(this).get(TvShowDetailsViewModel.class);
        getTvShowDetails();
        activityDetailsBinding.ivBackArrow.setOnClickListener(v -> onBackPressed());
        showTvInfoPart();
    }

    private void showTvInfoPart() {
        String showName = getIntent().getStringExtra("Name");
        String showNetwork = getIntent().getStringExtra("Network");
        String showStartDate = getIntent().getStringExtra("StartDate");
        String showCountry = getIntent().getStringExtra("Country");
        activityDetailsBinding.setShowName(showName);
        activityDetailsBinding.setShowNetwork(showNetwork);
        activityDetailsBinding.setShowStartedDate(showStartDate);
        activityDetailsBinding.setShowCountry(showCountry);
    }

    private void getTvShowDetails() {
        activityDetailsBinding.setIsLoading(true);
        int tvShowId = getIntent().getIntExtra("ID", -1);
        viewModel.getTvShowDetails(String.valueOf(tvShowId)).observe(this, new Observer<TvShowDetailsResponse>() {
            @Override
            public void onChanged(TvShowDetailsResponse tvShowDetailsResponse) {
                activityDetailsBinding.setIsLoading(false);

                if (tvShowDetailsResponse != null) {
                    if (tvShowDetailsResponse.getTvShow() != null) {
                        if (tvShowDetailsResponse.getTvShow().getPictures() != null) {
                            activityDetailsBinding.setImageUrl(
                                    tvShowDetailsResponse.getTvShow().getImageThumbnailPath()
                            );

                            activityDetailsBinding.setShowDetails(
                                    String.valueOf(
                                            HtmlCompat.fromHtml(
                                                    tvShowDetailsResponse.getTvShow().getDescription(),
                                                    HtmlCompat.FROM_HTML_MODE_LEGACY
                                            )
                                    )
                            );

                            if (tvShowDetailsResponse.getTvShow().getGenres() != null) {
                                activityDetailsBinding.setShowGenre(
                                        tvShowDetailsResponse.getTvShow().getGenres().get(0)
                                );
                            } else {
                                activityDetailsBinding.setShowGenre(
                                        "N/A"
                                );
                            }

                            activityDetailsBinding.setShowRatting(
                                    String.format(Locale.getDefault(),
                                            "%.2f",
                                            Double.parseDouble(tvShowDetailsResponse.getTvShow().getRating()))
                            );

                            activityDetailsBinding.setShowRuntime(String.valueOf(tvShowDetailsResponse.getTvShow().getRuntime()));

                            activityDetailsBinding.tvMoreText.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (activityDetailsBinding.tvMoreText.getText().toString().equals("Read More")) {
                                        activityDetailsBinding.tvDetails.setMaxLines(Integer.MAX_VALUE);
                                        activityDetailsBinding.tvDetails.setEllipsize(null);
                                        activityDetailsBinding.tvMoreText.setText(R.string.read_less);
                                    } else {
                                        activityDetailsBinding.tvDetails.setMaxLines(4);
                                        activityDetailsBinding.tvDetails.setEllipsize(TextUtils.TruncateAt.END);
                                        activityDetailsBinding.tvMoreText.setText(R.string.read_more);
                                    }
                                }
                            });


                            loadImageSliders(tvShowDetailsResponse.getTvShow().getPictures());


                            activityDetailsBinding.btnWebsite.setVisibility(View.VISIBLE);
                            activityDetailsBinding.btnEpisode.setVisibility(View.VISIBLE);
                            activityDetailsBinding.btnWebsite.setOnClickListener(v -> {
                                Intent intent = new Intent(Intent.ACTION_VIEW);
                                intent.setData(Uri.parse(tvShowDetailsResponse.getTvShow().getUrl()));
                                startActivity(intent);
                            });

                            activityDetailsBinding.btnEpisode.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    if (episodeBottomSheetDialog == null) {
                                        episodeBottomSheetDialog =
                                                new BottomSheetDialog(TvShowDetailsActivity.this);
                                        layoutEpisodeBottomSheetBinding = DataBindingUtil.inflate(
                                                LayoutInflater.from(TvShowDetailsActivity.this),
                                                R.layout.layout_episode_bottom_sheet,
                                                findViewById(R.id.episodeContainer),
                                                false
                                        );
                                        episodeBottomSheetDialog.setContentView(layoutEpisodeBottomSheetBinding.getRoot());
                                        layoutEpisodeBottomSheetBinding.episodeRecyclerView.setAdapter(
                                                new EpisodeAdapter(tvShowDetailsResponse.getTvShow().getEpisodes())
                                        );

                                        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(layoutEpisodeBottomSheetBinding.episodeRecyclerView.getContext(),
                                                new LinearLayoutManager(TvShowDetailsActivity.this).getOrientation());
                                        layoutEpisodeBottomSheetBinding.episodeRecyclerView.addItemDecoration(dividerItemDecoration);

                                        layoutEpisodeBottomSheetBinding.tvTitle.setText(
                                                String.format("Episode | %s", getIntent().getStringExtra("Name"))
                                        );
                                        layoutEpisodeBottomSheetBinding.ivClose.setOnClickListener(v1 -> episodeBottomSheetDialog.dismiss());
                                    }

                                    FrameLayout frameLayout = episodeBottomSheetDialog.findViewById(
                                            com.google.android.material.R.id.design_bottom_sheet
                                    );

                                    if (frameLayout != null) {
                                        BottomSheetBehavior<View> bottomSheetBehavior =
                                                BottomSheetBehavior.from(frameLayout);
                                        bottomSheetBehavior.setPeekHeight(Resources.getSystem().getDisplayMetrics().heightPixels);
                                        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                                    }

                                    episodeBottomSheetDialog.show();


                                }
                            });

                        }
                    }
                }
            }
        });
    }

    private void loadImageSliders(String[] images) {
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

    private void setupSliderIndicator(int count) {
        ImageView[] indicator = new ImageView[count];
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT
        );
        layoutParams.setMargins(8, 0, 8, 0);
        for (int i = 0; i < count; i++) {
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

    private void setCurrentSliderIndicator(int position) {
        int childCount = activityDetailsBinding.layoutSliderIndicator.getChildCount();
        for (int i = 0; i < childCount; i++) {
            ImageView imageView =
                    (ImageView) activityDetailsBinding.layoutSliderIndicator.getChildAt(i);
            if (i == position) {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.background_slider_indicator_actieve));
            } else {
                imageView.setImageDrawable(ContextCompat.getDrawable(getApplicationContext(),
                        R.drawable.background_slider_indicator_inactieve));
            }
        }
    }


}