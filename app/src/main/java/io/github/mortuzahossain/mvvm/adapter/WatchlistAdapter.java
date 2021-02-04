package io.github.mortuzahossain.mvvm.adapter;
/*
 * Created by mortuza on 5/2/21 | 1:14 AM for MVVM
 * Junior Programmer
 * Flora Systems
 * Email : mortuzahossain1997@gmail.com
 * Phone : +8801719200957
 * */


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import io.github.mortuzahossain.mvvm.R;
import io.github.mortuzahossain.mvvm.databinding.ItemContainerTvShowBinding;
import io.github.mortuzahossain.mvvm.interfaces.TvShowClickListener;
import io.github.mortuzahossain.mvvm.interfaces.WatchlistListener;
import io.github.mortuzahossain.mvvm.models.TvShowsItem;

public class WatchlistAdapter extends RecyclerView.Adapter<WatchlistAdapter.TvShowViewHolder> {

    private List<TvShowsItem> tvShowsItems;
    private LayoutInflater layoutInflater;
    private WatchlistListener watchlistListener;

    public WatchlistAdapter(List<TvShowsItem> tvShowsItems, WatchlistListener watchlistListener) {
        this.tvShowsItems = tvShowsItems;
        this.watchlistListener = watchlistListener;
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
        return new WatchlistAdapter.TvShowViewHolder(tvShowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull TvShowViewHolder holder, int position) {
        holder.BindTvShows(tvShowsItems.get(position));
    }

    @Override
    public int getItemCount() {
        return tvShowsItems.size();
    }

    public class TvShowViewHolder extends RecyclerView.ViewHolder {
        ItemContainerTvShowBinding itemContainerTvShowBinding;

        public TvShowViewHolder(@NonNull ItemContainerTvShowBinding itemView) {
            super(itemView.getRoot());
            this.itemContainerTvShowBinding = itemView;
        }

        public void BindTvShows(TvShowsItem tvShowsItem) {
            itemContainerTvShowBinding.setTvShow(tvShowsItem);
            itemContainerTvShowBinding.executePendingBindings();

            itemContainerTvShowBinding.getRoot().setOnClickListener(v -> watchlistListener.onTvShowClicked(tvShowsItem));

            itemContainerTvShowBinding.ivDelete.setVisibility(View.VISIBLE);
            itemContainerTvShowBinding.ivDelete.setOnClickListener(v -> watchlistListener.removeTvShowFromList(tvShowsItem, getAdapterPosition()));

        }
    }
}
