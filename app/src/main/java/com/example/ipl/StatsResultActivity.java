package com.example.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import android.widget.TextView;
import java.util.ArrayList;

public class StatsResultActivity extends AppCompatActivity {

    /*
        MOST RUNS - 0
        MOST FOURS - 1
        MOST FOURS (INNINGS) - 2
        MOST SIXES - 3
        MOST SIXES (INNINGS) - 4
        HIGHEST SCORES - 5
        BEST BATTING STRIKE RATE - 6
        MOST WICKETS - 7
        BEST ECONOMY - 8
        BEST BOWLING STRIKE RATE - 9
        MOST CATCHES - 10
        MOST MAN OF THE MATCHES - 11
     */


    private RecyclerView stats_result_rec_view;
    private RecyclerView.Adapter stats_result_rec_view_adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String5Data> stats_query_result_list;

    private int card_res_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats_result);

        getSupportActionBar().hide();

// getIntent Data
        int stats_query_position = getIntent().getIntExtra("stats_query_position", -1);
        String stats_query = getIntent().getStringExtra("stats_query");


// Setting Header
        TextView stats_result_header = findViewById(R.id.stats_result_header);
        stats_result_header.setText(stats_query);


// Query from Database
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(getApplicationContext());
        dbAccess.openConnection();

        if (stats_query_position == 0) {
            stats_query_result_list = dbAccess.getMostRuns();
            card_res_id =  getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 1) {
            stats_query_result_list = dbAccess.getMostFours();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 2) {
            stats_query_result_list = dbAccess.getMostFoursInnings();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 3) {
            stats_query_result_list = dbAccess.getMostSixes();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 4) {
            stats_query_result_list = dbAccess.getMostSixesInnings();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 5) {
            stats_query_result_list = dbAccess.getHighestScore();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 6) {
            stats_query_result_list = dbAccess.getBestBattingSR();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 7) {
            stats_query_result_list = dbAccess.getMostWickets();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 8) {
            stats_query_result_list = dbAccess.getBestEconomy();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 9) {
            stats_query_result_list = dbAccess.getBestBowlingSR();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 10) {
            stats_query_result_list = dbAccess.getMostCatches();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        } else if (stats_query_position == 11) {
            stats_query_result_list = dbAccess.getMostMotMs();
            card_res_id = getResources().getIdentifier("layout/two_data_card", null, getPackageName());
        }

        dbAccess.closeConnection();


// Recycler View Setup
        stats_result_rec_view = findViewById(R.id.stats_result_rec_view);
    //        standings_rec_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(StatsResultActivity.this);
        stats_result_rec_view_adapter = new StatsResultRecAdapter(stats_query_result_list, card_res_id);
        stats_result_rec_view.setLayoutManager(layoutManager);
        stats_result_rec_view.setAdapter(stats_result_rec_view_adapter);
    }
}