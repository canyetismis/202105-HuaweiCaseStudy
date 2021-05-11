package com.canyetismis.football_app.repository;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.repository.cache.TeamDao;
import com.canyetismis.football_app.repository.cache.TeamDatabase;
import com.canyetismis.football_app.repository.remote.TeamApi;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class TeamRepository {
    private static final String TAG = "TeamRepository";

    private final TeamDao mTeamDao;
    private LiveData<List<Team>> mTeams;
    private Config config;
    private Retrofit retrofit;

    public TeamRepository(Application application){
        TeamDatabase db = TeamDatabase.getDatabase(application);
        mTeamDao = db.teamDao();
        //A singleton object to handle configurations
        config = Config.getInstance();
        //Creates retrofit to make REST requests
        retrofit = new Retrofit.Builder()
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //A request is made to the backend only once when the application is first started
        if(config.isRequestMade()){
            mTeams = mTeamDao.getAllTeams();
        } else {
            config.setRequestMade(true);
            fetchTeams();
        }

    }

    private void fetchTeams(){
        TeamApi teamApi = retrofit.create(TeamApi.class);
        Call<List<Team>> call = teamApi.getTeams();
        call.enqueue(new Callback<List<Team>>() {
            @Override
            public void onResponse(Call<List<Team>> call, Response<List<Team>> response) {
                if(!response.isSuccessful()){
                    Log.d(TAG, "Status Code: " + response.code());
                    refreshCache(setTeams());
                    return;
                }

                mTeams = new MutableLiveData<>( response.body());
                refreshCache(mTeams.getValue());
            }

            @Override
            public void onFailure(Call<List<Team>> call, Throwable t) {
                Log.d(TAG, "Error: " + t.toString());
                refreshCache(setTeams());
            }
        });
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
    //A failsafe function to populate livedata
    private List<Team> setTeams(){
        List<Team> teams = new ArrayList<>();
        for(int i=0; i<15; i++){
            teams.add(new Team("Team "+ i));
        }
        return teams;
    }
}

