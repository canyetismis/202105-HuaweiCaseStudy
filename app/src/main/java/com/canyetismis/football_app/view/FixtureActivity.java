package com.canyetismis.football_app.view;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.FixtureWeeks;
import com.canyetismis.football_app.model.Match;
import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.view.adapter.ViewPagerAdapterFixture;
import com.canyetismis.football_app.viewmodel.TeamViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class FixtureActivity extends AppCompatActivity {
    private static final String TAG = "FixtureActivity";

    private ViewPager2 pages;
    private ViewPagerAdapterFixture pageAdapter;
    private TeamViewModel mTeamViewModel;
    private List<Team> mTeams;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture);
        Log.d(TAG, "onCreate: started");

        pages = findViewById(R.id.fixture_pages);

        initViewProvider();

    }

    private void initViewProvider(){
        mTeamViewModel = new ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()))
                .get(TeamViewModel.class);
        mTeamViewModel.getTeams().observe(this, teamList -> {
            mTeams = new ArrayList<>(teamList);
            if(mTeams.size() > 0){
                initViewPager();
            }
        });
    }

    private void initViewPager(){
        pageAdapter = new ViewPagerAdapterFixture(generateFixture(mTeams));
        pages.setAdapter(pageAdapter);
    }

    //Fixture Generation Algorithm
    private final String notPlaying = "Not playing";

    private List<FixtureWeeks> generateFixture(List<Team> teams){
        List<Team> team_list = new ArrayList<>(teams);
        List<FixtureWeeks> fixture = new ArrayList<>();

        if (team_list.size() % 2 != 0) {
            team_list.add(new Team(notPlaying)); // If odd number of teams add a dummy team
        }

        int numWeeks = (team_list.size()-1); // Weeks needed to complete one half of league
        int halfSize = team_list.size() / 2;

        List<Team> rotating_teams = new ArrayList<>(team_list);
        rotating_teams.remove(0);

        int size = rotating_teams.size();
        String firstTeam= team_list.get(0).getTeamName();

        for(int week = 0; week<numWeeks; week++){
            List<Match> matches = new ArrayList<>();

            int team_idx = week % size;

            String team = rotating_teams.get(team_idx).getTeamName();
            matches.add(new Match(firstTeam, team));

            for (int i = 1; i<halfSize; i++){
                int idx1 = (week + i) % size;
                int idx2 = (week + size - i) % size;

                String team1 = rotating_teams.get(idx1).getTeamName();
                String team2 = rotating_teams.get(idx2).getTeamName();

                if(team1 == notPlaying){
                    matches.add(new Match(team2,team1));
                } else {
                    matches.add(new Match(team1,team2));
                }
            }

            fixture.add(new FixtureWeeks(matches, "Week " + (week+1) + " - 1st Half of League"));
        }
        fixture.addAll(invert(fixture,numWeeks));
        return fixture;
    }

    private List<FixtureWeeks> invert(List<FixtureWeeks> league1, int numWeeks){
        List<FixtureWeeks> league2 = new ArrayList<>();
        for(int week=0; week<numWeeks; week++){
            List<Match> temp = new ArrayList<>(league1.get(week).getList());
            List<Match> matches = new ArrayList<>();
            for (int i=0;i< temp.size(); i++){
                String team2 = temp.get(i).getTeam2Name();
                String team1 = temp.get(i).getTeam1Name();
                if(team2 == notPlaying){
                    matches.add(new Match(team1,team2));
                } else {
                    matches.add(new Match(team2,team1));
                }
            }
            league2.add(new FixtureWeeks(matches, "Week " + (week+1) + " - 2nd Half of League"));
        }
        return league2;
    }

}