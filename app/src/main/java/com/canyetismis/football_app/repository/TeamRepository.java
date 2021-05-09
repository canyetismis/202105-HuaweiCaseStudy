package com.canyetismis.football_app.repository;

import androidx.lifecycle.MutableLiveData;

import com.canyetismis.football_app.model.FixtureWeeks;
import com.canyetismis.football_app.model.Match;
import com.canyetismis.football_app.model.Team;

import java.util.ArrayList;
import java.util.List;

public class TeamRepository {

    private static TeamRepository instance;
    private static final ArrayList<Team> dataSet = new ArrayList<>();
    private static final ArrayList<FixtureWeeks> fixture = new ArrayList<>();

    public static TeamRepository getInstance(){
        if(instance == null){
            instance = new TeamRepository();
            setTeams();
        }
        return instance;
    }

    public MutableLiveData<List<Team>> getTeams(){
        MutableLiveData<List<Team>> data = new MutableLiveData<>();
        data.setValue(dataSet);
        return data;
    }

    private static void setTeams(){
        for(int i=0; i<4; i++){
            dataSet.add(new Team("Team "+ i));
        }
    }

    public MutableLiveData<List<FixtureWeeks>> getFixture(){
        fixture.addAll(generateFixture(dataSet));
        MutableLiveData<List<FixtureWeeks>> data = new MutableLiveData<>();
        data.setValue(fixture);
        return data;
    }

    private List<FixtureWeeks> generateFixture(List<Team> teams){
        List<Team> team_list = new ArrayList<>(teams);
        List<FixtureWeeks> fixtures = new ArrayList<>();

        if (team_list.size() % 2 != 0) {
            team_list.add(new Team("Bye")); // If odd number of teams add a dummy Team
        }

        int numWeeks = (team_list.size()-1); // Weeks needed to complete a single Half League
        int halfSize = team_list.size() / 2;

        List<Team> rotating_teams = new ArrayList<>(team_list);
        rotating_teams.remove(0);

        int size = rotating_teams.size();

        for(int week = 0; week<numWeeks; week++){
            List<Match> matches = new ArrayList<>();

            int team_idx = week % size;

            matches.add(new Match(
                    rotating_teams.get(team_idx).getTeamName(),
                    team_list.get(0).getTeamName()
            ));
            for (int i = 1; i<halfSize; i++){
                int firstTeam = (week + i) % size;
                int secondTeam = (week + size - i) % size;
                matches.add( new Match(
                        rotating_teams.get(firstTeam).getTeamName(),
                        rotating_teams.get(secondTeam).getTeamName())
                );
            }

            fixtures.add(new FixtureWeeks(matches, "Week " + (week+1) + " - 1st Half of League"));
        }
        fixtures.addAll(invert(fixtures,numWeeks));
        return fixtures;
    }

    private List<FixtureWeeks> invert(List<FixtureWeeks> league1, int numWeeks){
        List<FixtureWeeks> league2 = new ArrayList<>();
        for(int week=0; week<numWeeks; week++){
            List<Match> temp = new ArrayList<>(league1.get(week).getList());
            List<Match> matches = new ArrayList<>();
            for (int i=0;i< temp.size(); i++){
                matches.add(new Match(temp.get(i).getTeam2Name(),temp.get(i).getTeam1Name()));
            }
            league2.add(new FixtureWeeks(matches, "Week " + (week+1) + " - 2nd Half of League"));
        }
        return league2;
    }
}
