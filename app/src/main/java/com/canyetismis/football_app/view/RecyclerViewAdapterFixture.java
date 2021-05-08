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

import java.util.List;

public class RecyclerViewAdapterFixture extends RecyclerView.Adapter<RecyclerViewAdapterFixture.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapterFixture";

    private final List<Fixture> fixtures;

    public RecyclerViewAdapterFixture(List<Fixture> fixtures ) {
        this.fixtures = fixtures;
    }

    @NonNull
    @Override
    public RecyclerViewAdapterFixture.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_listitem_fixture, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapterFixture.ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        holder.team1Name.setText(fixtures.get(position).getTeam1Name());
        holder.team2Name.setText(fixtures.get(position).getTeam2Name());
    }

    @Override
    public int getItemCount() {
        return fixtures.size();
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
