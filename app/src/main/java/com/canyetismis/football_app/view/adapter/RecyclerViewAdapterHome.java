package com.canyetismis.football_app.view.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.Team;

import java.util.List;

public class RecyclerViewAdapterHome extends ListAdapter<Team, RecyclerViewAdapterHome.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterHome";

    public RecyclerViewAdapterHome(@NonNull DiffUtil.ItemCallback<Team> diffCallBack) {
        super(diffCallBack);
    }

    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        Team current = getItem(position);
        holder.bind(current.getTeamName());
    }

    public static class TeamDiff extends DiffUtil.ItemCallback<Team>{

        @Override
        public boolean areItemsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Team oldItem, @NonNull Team newItem) {
            return oldItem.getTeamName().equals(newItem.getTeamName());
        }
    }



    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView teamName;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.team_name);
        }

        public void bind(String text){
            teamName.setText(text);
        }

        public static ViewHolder create(ViewGroup parent){
            View view = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.layout_listitem_home, parent, false);
            return new ViewHolder(view);
        }
    }
}
