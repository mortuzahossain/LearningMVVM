package io.github.mortuzahossain.mvvm.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingComponent;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.databinding.ItemContainerTvShowBinding;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;

/**
 * Created by Mortuza Hossain on 03-Feb-2021
 * Email : mortuzahossain1997@gmail.com
 **/
public class TvShowsAdapter extends RecyclerView.Adapter<TvShowsAdapter.TvShowViewHolder> {

    private List<TvShowsItem> tvShowsItems;
    private LayoutInflater layoutInflater;

    public TvShowsAdapter(List<TvShowsItem> tvShowsItems) {
        this.tvShowsItems = tvShowsItems;
    }

    @NonNull
    @Override
    public TvShowViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        ItemContainerTvShowBinding tvShowBinding = DataBindingUtil.inflate(
                layoutInflater, R.layout.single_tv_show, parent, false
        );
        return new TvShowViewHolder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        holder.BindTvShows(tvShowsItems.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowsItems.size();
    }

    static class TvShowViewHolder extends RecyclerView.ViewHolder {
        ItemContainerTvShowBinding itemContainerTvShowBinding;

        public TvShowViewHolder(@NonNull ItemContainerTvShowBinding itemView) {
            super(itemView.getRoot());
            this.itemContainerTvShowBinding = itemView;
        }

        public void BindTvShows(TvShowsItem tvShowsItem) {
            itemContainerTvShowBinding.setTvShow(tvShowsItem);
            itemContainerTvShowBinding.executePendingBindings();
        }
    }
}
