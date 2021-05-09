package com.canyetismis.football_app.view;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.Match;
import com.canyetismis.football_app.model.FixtureWeek;
import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.viewmodel.TeamViewModel;

import androidx.appcompat.app.AppCompatActivity;
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
    private List<Team> teamList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fixture);
        Log.d(TAG, "onCreate: started");

        initViewProvider();
        initViewPager();
    }

    private void initViewProvider(){
        mTeamViewModel = new ViewModelProvider(this).get(TeamViewModel.class);
        mTeamViewModel.init();
        teamList = mTeamViewModel.getTeams().getValue();
    }

    private void initViewPager(){
        pages = findViewById(R.id.fixture_pages);
        pageAdapter = new ViewPagerAdapterFixture(generateFixture(teamList));
        pages.setAdapter(pageAdapter);
    }

    /*
    private List<FixtureWeek> makeFixture(){
        List<FixtureWeek> fixtureWeeks = new ArrayList<>();
        for (int i=0; i<3; i++){
            fixtureWeeks.add(new FixtureWeek(gMatches(), "Week " + i));
            Log.d(TAG, fixtureWeeks.get(i).getWeekTitle());
        }
        return fixtureWeeks;
    }

    private List<Match> gMatches(){
        List<Match> matches = new ArrayList<>();
        for(int i=0; i < teamList.size(); i++ ){
            matches.add(new Match(teamList.get(i).getTeamName(), teamList.get(i).getTeamName()));
        }
        return matches;
    }
    */

    private List<FixtureWeek> generateFixture(List<Team> teams){
        List<Team> team_list = new ArrayList<>(teams);
        List<FixtureWeek> fixtures = new ArrayList<>();

        if (team_list.size() % 2 != 0) {
            team_list.add(new Team("Bye")); // If odd number of teams add a dummy Team
        }

        int numWeeks = (team_list.size()-1); // Weeks needed to complete a single Half League
        int halfSize = team_list.size() / 2;

        List<Team> rotating_teams = new ArrayList<>(team_list);
        rotating_teams.remove(0);

        int size = rotating_teams.size();

        for(int i = 0; i<team_list.size();i++){
            Log.d(TAG, team_list.get(i).getTeamName());
        }

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

            fixtures.add(new FixtureWeek(matches, "Week " + (week+1) + " - League 1"));
        }
        fixtures.addAll(invert(fixtures,numWeeks));
        return fixtures;
    }

    private List<FixtureWeek> invert(List<FixtureWeek> league1, int numWeeks){
        List<FixtureWeek> league2 = new ArrayList<>();
        for(int week=0; week<numWeeks; week++){
            List<Match> temp = new ArrayList<>(league1.get(week).getList());
            List<Match> matches = new ArrayList<>();
            for (int i=0;i< temp.size(); i++){
                matches.add(new Match(temp.get(i).getTeam2Name(),temp.get(i).getTeam1Name()));
            }
            league2.add(new FixtureWeek(matches, "Week " + (week+1) + " - League 2"));
        }
        return league2;
    }
}