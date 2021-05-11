package com.canyetismis.football_app.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.repository.cache.TeamDao;
import com.canyetismis.football_app.repository.cache.TeamDatabase;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository {

    private final TeamDao mTeamDao;
    private LiveData<List<Team>> mTeams;

    public TeamRepository(Application application){
        TeamDatabase db = TeamDatabase.getDatabase(application);
        mTeamDao = db.teamDao();
        refresh();
    }

    public LiveData<List<Team>> geteTeams(){
        MutableLiveData<List<Team>> data = new MutableLiveData<>();
        data.setValue(setTeams());
        return data;
    }

    public LiveData<List<Team>> getTeams(){
        mTeams = mTeamDao.getAllTeams();
        return mTeams;
    }

    private void refresh(){
        TeamDatabase.databaseWriteExecutor.execute(()->{
            mTeamDao.deleteAllTeams();
            mTeamDao.insert(setTeams());
            mTeams = mTeamDao.getAllTeams();
        });
    }

    private List<Team> setTeams(){
        List<Team> teams = new ArrayList<>();
        for(int i=0; i<15; i++){
            teams.add(new Team("Team "+ i));
        }
        return teams;
    }
}

