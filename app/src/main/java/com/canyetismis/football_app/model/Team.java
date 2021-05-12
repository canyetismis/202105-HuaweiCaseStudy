package com.canyetismis.football_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

@Entity(tableName = "team_table")
public class Team {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @SerializedName("name")
    private String teamName;

    public Team(String teamName){
        this.teamName = teamName;
    }

    public int getId(){ return id; }

    public void setId(int id){this.id = id;}

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
}