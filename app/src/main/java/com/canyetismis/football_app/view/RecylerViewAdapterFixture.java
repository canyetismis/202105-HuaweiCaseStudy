package com.canyetismis.football_app.view;

import android.annotation.SuppressLint;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.Fixture;
import com.canyetismis.football_app.model.Team;

import java.util.List;

public class RecylerViewAdapterFixture extends RecyclerView.Adapter<RecylerViewAdapterFixture.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterFixture";

    private final List<Team> teamNames;

    public RecylerViewAdapterFixture(List<Team> teamNames ) {
        this.teamNames = teamNames;
    }

    @NonNull
    @Override
    public RecylerViewAdapterFixture.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_fixture, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull RecylerViewAdapterFixture.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.team1Name.setText(teamNames.get(position).getTeamName());
        holder.team2Name.setText(teamNames.get(position).getTeamName());
    }

    @Override
    public int getItemCount() {
        return teamNames.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView team1Name;
        TextView team2Name;
        RelativeLayout parentLayout;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            team1Name = itemView.findViewById(R.id.team_1);
            team2Name = itemView.findViewById(R.id.team_2);
            parentLayout = itemView.findViewById(R.id.parent_layout_f);
        }
    }
}
