package com.canyetismis.football_app.view;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.view.adapter.RecyclerViewAdapterHome;
import com.canyetismis.football_app.viewmodel.TeamViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.List;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";

    private RecyclerView mRecyclerView;
    private RecyclerViewAdapterHome mAdapter;
    private TeamViewModel mTeamViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: started");

        mRecyclerView = findViewById(R.id.recycler_view);

        initViewProvider();
        initRecylerView();
    }

    public void onFixtureButtonClicked(View v){
        Intent intent = new Intent(HomeActivity.this, FixtureActivity.class);
        startActivity(intent);
    }

    private void initViewProvider(){
        Log.d(TAG, "initRecylerView: init viewProvider");
        mTeamViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(TeamViewModel.class);
        mTeamViewModel.getTeams().observe(this, teamList -> {
            mAdapter.submitList(teamList);
        });
    }

    private void initRecylerView(){
        Log.d(TAG, "initRecylerView: init recylerview");
        mAdapter = new RecyclerViewAdapterHome(new RecyclerViewAdapterHome.TeamDiff());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}