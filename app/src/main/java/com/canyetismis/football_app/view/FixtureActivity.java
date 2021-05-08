package com.canyetismis.football_app.view;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.Fixture;
import com.canyetismis.football_app.model.FixtureList;
import com.canyetismis.football_app.model.Team;
import com.canyetismis.football_app.viewmodel.TeamViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class FixtureActivity extends AppCompatActivity {
    private static final String TAG = "FixtureActivity";

    private ViewPager2 pages;
    private ViewPagerAdapter pageAdapter;
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
        pages = findViewById(R.id.pages);
        pageAdapter = new ViewPagerAdapter(makePages());
        pages.setAdapter(pageAdapter);
    }

    private List<FixtureList> makePages(){
        List<FixtureList> fixtureLists = new ArrayList<>();
        for (int i=0; i<3; i++){
            fixtureLists.add(new FixtureList(makeFixtures()));
        }
        return fixtureLists;
    }

    private List<Fixture> makeFixtures(){
        List<Fixture> fixtures = new ArrayList<>();
        for(int i=0; i < teamList.size(); i++ ){
            fixtures.add(new Fixture(teamList.get(i).getTeamName(), teamList.get(i).getTeamName()));
        }
        return fixtures;
    }

}