package com.canyetismis.football_app.view;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.FixtureList;

import java.util.List;

public class ViewPagerAdapter extends RecyclerView.Adapter<ViewPagerAdapter.ViewHolder> {
    private static final String TAG = "ViewPagerAdapter";

    private RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();
    private final List<FixtureList> pages;

    public ViewPagerAdapter(List<FixtureList> pages){
        this.pages = pages;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fixture_list, parent, false);
        return new ViewPagerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.d(TAG, "onBindViewHolder: called");

        FixtureList fixtureList = pages.get(position);

        LinearLayoutManager layoutManager = new LinearLayoutManager(
                holder.fixtureList.getContext(),
                LinearLayoutManager.VERTICAL,
                false
        );
        layoutManager.setInitialPrefetchItemCount(fixtureList.getList().size());

        RecyclerViewAdapterFixture fixtureAdapter = new RecyclerViewAdapterFixture(fixtureList.getList());

        holder.fixtureList.setLayoutManager(layoutManager);
        holder.fixtureList.setAdapter(fixtureAdapter);
        holder.fixtureList.setRecycledViewPool(viewPool);
    }

    @Override
    public int getItemCount() {
        return pages.size();
    }

    public  class ViewHolder extends RecyclerView.ViewHolder{
        RecyclerView fixtureList;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            fixtureList = itemView.findViewById(R.id.list);

        }
    }
}
