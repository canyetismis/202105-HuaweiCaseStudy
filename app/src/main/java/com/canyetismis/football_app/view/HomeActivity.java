package com.canyetismis.football_app.view;
import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.viewmodel.HomeActivityViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private RecyclerView mRecyclerView;
    private RecylerViewAdapter mAdapter;
    private HomeActivityViewModel mHomeActivityViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: started");

        mRecyclerView = findViewById(R.id.recycler_view);

        mHomeActivityViewModel = new ViewModelProvider(this).get(HomeActivityViewModel.class);
        mHomeActivityViewModel.init();

        mHomeActivityViewModel.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                mAdapter.notifyDataSetChanged();
            }
        });
        initRecylerView();
    }
    /*
    private void initTeams(){
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
        mTeams.add("Team B");
        mTeams.add("Team A");
    }
    */
    private void initRecylerView(){
        Log.d(TAG, "initRecylerView: init recylerview");
        mAdapter = new RecylerViewAdapter(mHomeActivityViewModel.getTeams().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}