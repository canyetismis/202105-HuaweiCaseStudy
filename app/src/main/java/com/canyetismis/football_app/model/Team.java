package com.canyetismis.football_app.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "team_table")
public class Team {

    @PrimaryKey(autoGenerate = true)
    private int id;

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
/*
public class Team {
    private String teamName;

    public Team(String teamName){
        this.teamName = teamName;
    }

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
}


 */