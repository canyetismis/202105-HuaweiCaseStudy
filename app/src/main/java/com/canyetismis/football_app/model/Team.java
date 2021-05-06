package com.canyetismis.football_app.model;

public class Team {
    private String teamName;

    public Team(String teamName){
        this.teamName = teamName;
    }

    public Team(){}

    public String getTeamName(){
        return teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }
}
