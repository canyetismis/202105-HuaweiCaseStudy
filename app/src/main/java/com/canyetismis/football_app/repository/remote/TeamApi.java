package com.canyetismis.football_app.repository.remote;

import com.canyetismis.football_app.model.Team;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TeamApi {

    @GET("users")
    Call<List<Team>> getTeams();
}
