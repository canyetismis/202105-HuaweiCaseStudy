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
        pageAdapter = new ViewPagerAdapterFixture(makeFixture());
        pages.setAdapter(pageAdapter);
    }

    private List<FixtureWeek> makeFixture(){
        List<FixtureWeek> fixtureWeeks = new ArrayList<>();
        for (int i=0; i<3; i++){
            fixtureWeeks.add(new FixtureWeek(generateMatches(), "Week " + i));
            Log.d(TAG, fixtureWeeks.get(i).getWeekTitle());
        }
        return fixtureWeeks;
    }

    private List<Match> generateMatches(){
        List<Match> matches = new ArrayList<>();
        for(int i=0; i < teamList.size(); i++ ){
            matches.add(new Match(teamList.get(i).getTeamName(), teamList.get(i).getTeamName()));
        }
        return matches;
    }

}