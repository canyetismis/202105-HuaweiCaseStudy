package com.canyetismis.football_app.model;

import java.util.List;

public class FixtureWeeks {
    private List<Match> matches;
    private String weekTitle;

    public FixtureWeeks(List<Match> matches, String weekTitle){
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
