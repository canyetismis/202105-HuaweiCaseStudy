package com.canyetismis.football_app.viewmodel;

import android.app.Application;
import android.content.Context;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.canyetismis.football_app.model.FixtureWeeks;
import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.repository.TeamRepository;

import java.util.List;

public class TeamViewModel extends AndroidViewModel {

    private TeamRepository mRepo;

    private LiveData<List<Team>> mTeams;
    private LiveData<List<FixtureWeeks>> mFixture;

    public TeamViewModel(Application application){
        super(application);
        mRepo = new TeamRepository(application);
        mTeams = mRepo.getTeams();
        //mFixture = mRepo.getFixture();
    }

    public LiveData<List<Team>> getTeams(){
        return mTeams;
    }

    public LiveData<List<FixtureWeeks>> getFixture(){
        return mFixture;
    }
}
