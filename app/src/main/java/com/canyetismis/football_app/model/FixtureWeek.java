package com.canyetismis.football_app.model;

import java.util.List;

public class FixtureWeek {
    private List<Match> matches;
    private String weekTitle;

    public FixtureWeek(List<Match> matches, String weekTitle){
        this.matches = matches;
        this.weekTitle = weekTitle;
    }

    public List<Match> getList() {
        return matches;
    }

    public void setList(List<Match> list) {
        this.matches = matches;
    }

    public String getWeekTitle() {
        return weekTitle;
    }

    public void setWeekTitle(String weekTitle) {
        this.weekTitle = weekTitle;
    }
}
