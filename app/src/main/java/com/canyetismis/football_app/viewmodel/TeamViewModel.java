package com.canyetismis.football_app.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.canyetismis.football_app.model.FixtureWeeks;
import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.repository.TeamRepository;

import java.util.List;

public class TeamViewModel extends ViewModel {

    private MutableLiveData<List<Team>> mTeams;
    private MutableLiveData<List<FixtureWeeks>> mFixture;
    private TeamRepository mRepo;

    public void init(){
        if(mTeams != null){
            return;
        }
        mRepo = TeamRepository.getInstance();
        mTeams = mRepo.getTeams();
        mFixture = mRepo.getFixture();
    }

    public LiveData<List<Team>> getTeams(){
        return mTeams;
    }

    public LiveData<List<FixtureWeeks>> getFixture(){
        return mFixture;
    }

}
