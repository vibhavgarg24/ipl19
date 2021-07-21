package com.example.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.Handler;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;

public class Scorecard extends AppCompatActivity {

    TabLayout scorecard_tabs;
    ViewPager scorecard_viewPager;
    Match match;
    int bat_first;
    String match_result;
    ArrayList<Batsman> batsmen1;
    ArrayList<Batsman> batsmen2;
    ArrayList<Bowler> bowlers1;
    ArrayList<Bowler> bowlers2;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_scorecard);

        getSupportActionBar().hide();

        int match_id = getIntent().getIntExtra("match_id", 0);
        match_result = getIntent().getStringExtra("match_result");

//  Match & Scorecard from Db
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(Scorecard.this);
        dbAccess.openConnection();

        match = dbAccess.getMatch(match_id);
        bat_first =match.getBat_first();

        batsmen1 = dbAccess.getBatsmen(match_id, match.getTeam1_id());
        batsmen2 = dbAccess.getBatsmen(match_id, match.getTeam2_id());

        bowlers1 = dbAccess.getBowlers(match_id, match.getTeam1_id());
        bowlers2 = dbAccess.getBowlers(match_id, match.getTeam2_id());

        dbAccess.closeConnection();



// Tab Layout
        scorecard_tabs = findViewById(R.id.scorecard_tabs);
        scorecard_viewPager = findViewById(R.id.scorecard_viewPager);

        getTabs();

    }

    public void getTabs() {

        final ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());

        new Handler().post(new Runnable() {
            @Override
            public void run() {

                viewPagerAdapter.addFragment(new ScorecardInfoFragment(match, match_result), "INFO");

                if (bat_first == 1) {
                    viewPagerAdapter.addFragment(new ScorecardTeam1Fragment(batsmen1, bowlers2, match.getTeam1_score()), match.getTeam1_abv());
                    viewPagerAdapter.addFragment(new ScorecardTeam2Fragment(batsmen2, bowlers1, match.getTeam2_score()), match.getTeam2_abv());
                } else if (bat_first == 2) {
                    viewPagerAdapter.addFragment(new ScorecardTeam1Fragment(batsmen2, bowlers1, match.getTeam2_score()), match.getTeam2_abv());
                    viewPagerAdapter.addFragment(new ScorecardTeam2Fragment(batsmen1, bowlers2, match.getTeam1_score()), match.getTeam1_abv());
                }

                scorecard_viewPager.setAdapter(viewPagerAdapter);
                scorecard_tabs.setupWithViewPager(scorecard_viewPager);
            }
        });
    }

}