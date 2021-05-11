package com.canyetismis.football_app.repository;

public class Config {
    private static Config instance;
    private boolean requestMade = false;

    public static Config getInstance(){
        if(instance == null){
            instance = new Config();
        }
        return instance;
    }

    public void setRequestMade(boolean bool){
        requestMade = bool;
    }

    public boolean isRequestMade(){
        return requestMade;
    }


}
