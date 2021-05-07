package com.canyetismis.football_app.view;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.Team;
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

public class FixtureActivity extends AppCompatActivity {
    private static final String TAG = "FixtureActivity";

    private RecyclerView mRecyclerView;
    private RecylerViewAdapterFixture mAdapter;
    private TeamViewModel mTeamViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture);
        Log.d(TAG, "onCreate: started");

        mRecyclerView = findViewById(R.id.recycler_view_f);

        initViewProvider();
        initRecylerView();
    }


    private void initViewProvider(){
        mTeamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        mTeamViewModel.init();

        mTeamViewModel.getTeams().observe(this, new Observer<List<Team>>() {
            @Override
            public void onChanged(List<Team> teams) {
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initRecylerView(){
        Log.d(TAG, "initRecylerView: init recylerview");
        mAdapter = new RecylerViewAdapterFixture(mTeamViewModel.getTeams().getValue());
        RecyclerView.LayoutManager linearLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(linearLayoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }
}