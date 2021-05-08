package com.canyetismis.football_app.model;

public class Fixture {
    private String team1Name;
    private String team2Name;

    public Fixture(String team1Name, String team2Name){
        this.team1Name = team1Name;
        this.team2Name = team2Name;
    }

    public Fixture(){}

    public String getTeam1Name(){
        return team1Name;
    }

    public void setTeam1Name(String teamName){
        this.team1Name = teamName;
    }

    public String getTeam2Name(){
        return team2Name;
    }

    public void setTeam2Name(String teamName){
        this.team2Name = teamName;
    }
}
