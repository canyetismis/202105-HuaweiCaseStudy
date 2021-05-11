package com.canyetismis.football_app.repository.remote;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.canyetismis.football_app.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamRestClient {

    private static final String TAG = "TeamRestClient";

    private MutableLiveData<List<Team>> mTeams;
    private static TeamRestClient instance;
    //A simple boolean to manage requests made to the backend
    public boolean madeRequest = false;

    public static TeamRestClient getInstance(){
        if (instance == null) {
            Log.d(TAG,"returning a new instance");
            instance = new TeamRestClient();
        }

        return instance;
    }

    public MutableLiveData<List<Team>> getTeams(){
        Log.d(TAG, "madeRequest: " + madeRequest);
        madeRequest = true;
        mTeams = new MutableLiveData<>(setTeams());
        return mTeams;
    }

    private List<Team> setTeams(){
        List<Team> teams = new ArrayList<>();
        for(int i=0; i<15; i++){
            teams.add(new Team("Team "+ i));
        }
        return teams;
    }
}
