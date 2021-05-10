package com.canyetismis.football_app.repository.cache;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.canyetismis.football_app.model.Team;

import java.util.List;

@Dao
public interface TeamDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(Team team);

    @Query("DELETE FROM team_table")
    void deleteAllTeams();

    @Query("SELECT * FROM team_table")
    LiveData<List<Team>> getAllTeams();
}
