package com.canyetismis.football_app.view.home;
import com.canyetismis.football_app.R;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = "HomeActivity";
    private final ArrayList<String> mTeams = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        Log.d(TAG, "onCreate: started");

        initTeams();
        initRecylerView();
    }

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

    private void initRecylerView(){
        Log.d(TAG, "initRecylerView: init recylerview");
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        RecylerViewAdapter adapter = new RecylerViewAdapter(mTeams);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}