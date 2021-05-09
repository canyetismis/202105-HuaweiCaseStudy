package com.canyetismis.football_app.view;

import com.canyetismis.football_app.R;
import com.canyetismis.football_app.model.FixtureWeeks;
import com.canyetismis.football_app.view.adapter.ViewPagerAdapterFixture;
import com.canyetismis.football_app.viewmodel.TeamViewModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class FixtureActivity extends AppCompatActivity {
    private static final String TAG = "FixtureActivity";

    private ViewPager2 pages;
    private ViewPagerAdapterFixture pageAdapter;
    private TeamViewModel mTeamViewModel;

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
        mTeamViewModel.getFixture().observe(this, new Observer<List<FixtureWeeks>>() {
            @Override
            public void onChanged(List<FixtureWeeks> teams) {
                pageAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initViewPager(){
        pages = findViewById(R.id.fixture_pages);
        pageAdapter = new ViewPagerAdapterFixture(mTeamViewModel.getFixture().getValue());
        pages.setAdapter(pageAdapter);
    }

}