package com.canyetismis.football_app.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.repository.cache.TeamDao;
import com.canyetismis.football_app.repository.cache.TeamDatabase;
import com.canyetismis.football_app.repository.remote.TeamRestClient;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository {

    private final TeamDao mTeamDao;
    private LiveData<List<Team>> mTeams;
    private TeamRestClient client;

    public TeamRepository(Application application){
        TeamDatabase db = TeamDatabase.getDatabase(application);
        mTeamDao = db.teamDao();
        client = TeamRestClient.getInstance();
        //Backend is checked only once when the application is first started
        if(client.madeRequest){
            mTeams = mTeamDao.getAllTeams();
        } else {
            mTeams = client.getTeams();
            refreshCache(client.getTeams().getValue());
        }

    }

    public LiveData<List<Team>> getTeams(){
        mTeams = mTeamDao.getAllTeams();
        return mTeams;
    }

    private void refreshCache(List<Team> teams){
        TeamDatabase.databaseWriteExecutor.execute(()->{
            mTeamDao.deleteAllTeams();
            mTeamDao.insert(teams);

        });
    }
}

