package com.example.ipl;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class ScorecardTeam1Fragment extends Fragment {

    private RecyclerView batting_rec_view;
    private RecyclerView.Adapter batting_rec_view_adapter;
    private RecyclerView.LayoutManager batting_layoutManager;
    private ArrayList<Batsman> batsmen;

    private RecyclerView bowling_rec_view;
    private RecyclerView.Adapter bowling_rec_view_adapter;
    private RecyclerView.LayoutManager bowling_layoutManager;
    private ArrayList<Bowler> bowlers;

    private TextView batting_total;
    private String batting_team_score;

    public ScorecardTeam1Fragment (ArrayList<Batsman> batsmen, ArrayList<Bowler> bowlers, String batting_team_score) {
        this.batsmen = batsmen;
        this.bowlers = bowlers;
        this.batting_team_score = batting_team_score;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scorecard_team1, container, false);

//Batting Scorecard
        batting_rec_view = root.findViewById(R.id.scorecard_batting1);
    //        standings_rec_view.setHasFixedSize(true);
        batting_layoutManager = new LinearLayoutManager(getContext());
        batting_rec_view_adapter = new BattingRecAdapter(batsmen);
        batting_rec_view.setLayoutManager(batting_layoutManager);
        batting_rec_view.setAdapter(batting_rec_view_adapter);


//Bowling Scorecard
        bowling_rec_view = root.findViewById(R.id.scorecard_bowling1);
    //        standings_rec_view.setHasFixedSize(true);
        bowling_layoutManager = new LinearLayoutManager(getContext());
        bowling_rec_view_adapter = new BowlingRecAdapter(bowlers);
        bowling_rec_view.setLayoutManager(bowling_layoutManager);
        bowling_rec_view.setAdapter(bowling_rec_view_adapter);

//Batting Total
        batting_total = root.findViewById(R.id.batting1_total);
        batting_total.setText(batting_team_score);

        return root;
    }
}
