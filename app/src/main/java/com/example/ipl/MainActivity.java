package com.example.ipl;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    TextView fragment_head;
    ArrayList<Match> matches;
    ArrayList<String> teamNames;
    ArrayList<Team> teams;
    ImageView info_image;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().hide();

        fragment_head = findViewById(R.id.fragment_head);
        info_image = findViewById(R.id.info_image);

//         Database
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
        dbAccess.openConnection();

        matches = dbAccess.getMatches();
        teamNames = dbAccess.getTeamNames();
        teams = dbAccess.getTeams();

        dbAccess.closeConnection();


        BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
        bottomNav.setOnNavigationItemSelectedListener(navListener);

        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new MatchesFragment(matches)).commit();
        fragment_head.setText("MATCHES");

// About Us OnClick
        info_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
            }
        });

    }



    private BottomNavigationView.OnNavigationItemSelectedListener navListener = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @SuppressLint("SetTextI18n")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment selectedFragment = null;

            switch(item.getItemId()) {
                case R.id.nav_matches :
                    selectedFragment = new MatchesFragment(matches);
                    fragment_head.setText("MATCHES");
                    break;
                case R.id.nav_teams :
                    selectedFragment = new TeamsFragment(teamNames);
                    fragment_head.setText("TEAMS");
                    break;
                case R.id.nav_stats :
                    selectedFragment = new StatsFragment();
                    fragment_head.setText("STATS");
                    break;
                case R.id.nav_standings :
                    selectedFragment = new StandingsFragment(teams);
                    fragment_head.setText("POINTS TABLE");
                    break;
                case R.id.nav_favourites :
                    selectedFragment = new FavouritesFragment();
                    fragment_head.setText("FAVOURITE PLAYERS");
                    break;
            }

            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, selectedFragment).commit();

            return true;
        }
    };
}

