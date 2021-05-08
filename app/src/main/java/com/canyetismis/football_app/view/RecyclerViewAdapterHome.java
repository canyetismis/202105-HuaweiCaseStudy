package com.canyetismis.football_app.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.Team;

import java.util.List;

public class RecyclerViewAdapterHome extends RecyclerView.Adapter<RecyclerViewAdapterHome.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterHome";

    private final List<Team> mTeamNames;

    public RecyclerViewAdapterHome(List<Team> mTeamNames) {
        this.mTeamNames = mTeamNames;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_home, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.teamName.setText(mTeamNames.get(position).getTeamName());

    }

    @Override
    public int getItemCount() {
        return mTeamNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView teamName;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            teamName = itemView.findViewById(R.id.team_name);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}
