package io.github.mortuzahossain.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.databinding.ItemContainerSliderImageBinding;

/**
 * Created by Mortuza Hossain on 03-Feb-2021
 * Email : mortuzahossain1997@gmail.com
 **/
public class ImageSliderAdapter extends RecyclerView.Adapter<ImageSliderAdapter.ImageSliderViewModel> {

    private String[] sliderImage;
    private LayoutInflater layoutInflater;

    public ImageSliderAdapter(String[] sliderImage) {
        this.sliderImage = sliderImage;
    }

    @NonNull
    @Override
    public ImageSliderViewModel onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerSliderImageBinding sliderImageBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_slider_image, parent, false
        );
        return new ImageSliderViewModel(sliderImageBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ImageSliderViewModel holder, int position) {
        holder.bindSliderImage(sliderImage[position]);
    }

    @Override
    public int getItemCount() {
        return sliderImage.length;
    }

    static class ImageSliderViewModel extends RecyclerView.ViewHolder {
        ItemContainerSliderImageBinding itemContainerSliderImageBinding;

        public ImageSliderViewModel(@NonNull ItemContainerSliderImageBinding itemContainerSliderImageBinding) {
            super(itemContainerSliderImageBinding.getRoot());
            this.itemContainerSliderImageBinding = itemContainerSliderImageBinding;
        }

        public void bindSliderImage(String url) {
            itemContainerSliderImageBinding.setImageUrl(url);
        }
    }
}
