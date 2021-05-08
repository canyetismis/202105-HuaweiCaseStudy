package com.canyetismis.football_app.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.FixtureWeek;

import java.util.List;

public class ViewPagerAdapterFixture extends RecyclerView.Adapter<ViewPagerAdapterFixture.ViewHolder> {
    private static final String TAG = "ViewPagerAdapterFixture";

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final List<FixtureWeek> pages;

    public ViewPagerAdapterFixture(List<FixtureWeek> pages){
        this.pages = pages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_list, parent, false);
        return new ViewPagerAdapterFixture.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        FixtureWeek fixtureWeek = pages.get(position);
        Log.d(TAG, fixtureWeek.getWeekTitle());
        holder.weekTitle.setText(fixtureWeek.getWeekTitle());

        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.matchList.getContext());
        layoutManager.setInitialPrefetchItemCount(fixtureWeek.getList().size());
        RecyclerViewAdapterMatchList fixtureAdapter = new RecyclerViewAdapterMatchList(fixtureWeek.getList());

        holder.matchList.setLayoutManager(layoutManager);
        holder.matchList.setAdapter(fixtureAdapter);
        holder.matchList.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView weekTitle;
        RecyclerView matchList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            weekTitle =itemView.findViewById(R.id.week_title);
            matchList = itemView.findViewById(R.id.match_list);

        }
    }
}
