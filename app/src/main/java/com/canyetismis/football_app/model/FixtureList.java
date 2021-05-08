package com.canyetismis.football_app.model;

import java.util.List;

public class FixtureList {
    private List<Fixture> list;

    public FixtureList(List<Fixture> list){
        this.list = list;
    }

    public List<Fixture> getList() {
        return list;
    }

    public void setList(List<Fixture> list) {
        this.list = list;
    }
}
