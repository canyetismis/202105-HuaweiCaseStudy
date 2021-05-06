package com.canyetismis.football_app.view.home;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.canyetismis.football_app.R;

import java.util.ArrayList;

public class RecylerViewAdapter extends RecyclerView.Adapter<RecylerViewAdapter.ViewHolder> {
    private static final String TAG = "RecyclerViewAdapter";

    private final ArrayList<String> mTeamNames;

    public RecylerViewAdapter(ArrayList<String> mTeamNames) {
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

        holder.teamName.setText(mTeamNames.get(position));

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
