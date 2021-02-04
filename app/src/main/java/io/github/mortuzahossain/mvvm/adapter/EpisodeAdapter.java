package io.github.mortuzahossain.mvvm.adapter;
/*
 * Created by mortuza on 4/2/21 | 6:41 PM for MVVM
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
import io.github.mortuzahossain.mvvm.databinding.EpisodeContainer;
import io.github.mortuzahossain.mvvm.models.EpisodesItem;

public class EpisodeAdapter extends RecyclerView.Adapter<EpisodeAdapter.EpisodeViewHolder> {

    List<EpisodesItem> episodesItems;
    private LayoutInflater layoutInflater;

    public EpisodeAdapter(List<EpisodesItem> episodesItems) {
        this.episodesItems = episodesItems;
    }

    @NonNull
    @Override
    public EpisodeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (layoutInflater == null) {
            layoutInflater = LayoutInflater.from(parent.getContext());
        }
        EpisodeContainer container = DataBindingUtil.inflate(
                layoutInflater, R.layout.item_container_episode, parent, false
        );
        return new EpisodeViewHolder(container);
    }

    @Override
    public void onBindViewHolder(@NonNull EpisodeViewHolder holder, int position) {
        holder.BindView(episodesItems.get(position));
    }

    @Override
    public int getItemCount() {
        return episodesItems.size();
    }

    public class EpisodeViewHolder extends RecyclerView.ViewHolder {

        EpisodeContainer episodeContainer;

        public EpisodeViewHolder(@NonNull EpisodeContainer itemView) {
            super(itemView.getRoot());
            episodeContainer = itemView;
        }

        void BindView(EpisodesItem episodesItem) {
            String title = "S";
            if (episodesItem.getSeason() < 10)
                title = title.concat("0" + episodesItem.getSeason());
            else title = title.concat(String.valueOf(episodesItem.getSeason()));
            title = title.concat("E");
            if (episodesItem.getEpisode() < 10)
                title = title.concat("0" + episodesItem.getEpisode());
            else title = title.concat(String.valueOf(episodesItem.getEpisode()));

            episodeContainer.setTitle(title);
            episodeContainer.setName(episodesItem.getName());
            episodeContainer.setAirName(episodesItem.getAirDate());
        }
    }

}
