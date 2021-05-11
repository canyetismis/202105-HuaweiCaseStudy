package com.canyetismis.football_app.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.repository.TeamRepository;

import java.util.List;

public class TeamViewModel extends AndroidViewModel {

    private final TeamRepository mRepo;

    private final LiveData<List<Team>> mTeams;

    public TeamViewModel(Application application){
        super(application);
        mRepo = new TeamRepository(application);
        mTeams = mRepo.getTeams();
    }

    public LiveData<List<Team>> getTeams(){
        return mTeams;
    }
}
