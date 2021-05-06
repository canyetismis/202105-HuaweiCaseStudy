package com.canyetismis.football_app.repository;

import androidx.lifecycle.MutableLiveData;

import com.canyetismis.football_app.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository {

    private static TeamRepository instance;
    private final ArrayList<Team> dataSet = new ArrayList<>();

    public static TeamRepository getInstance(){
        if(instance == null){
            instance = new TeamRepository();
        }
        return instance;
    }

    public MutableLiveData<List<Team>> getTeams(){
        setTeams();
        MutableLiveData<List<Team>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private void setTeams(){
        for(int i=0; i<12; i++){
            dataSet.add(new Team("Team "+ i));
        }
    }
}
