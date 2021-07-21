package com.example.ipl;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StatsFragment extends Fragment {

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

    private RecyclerView stats_rec_view;
    private RecyclerView.Adapter stats_rec_view_adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> stats_queries;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_stats, container, false);

        stats_queries = new ArrayList<>();
        stats_queries.add("MOST RUNS");
        stats_queries.add("MOST FOURS");
        stats_queries.add("MOST FOURS (INNINGS)");
        stats_queries.add("MOST SIXES");
        stats_queries.add("MOST SIXES (INNINGS)");
        stats_queries.add("HIGHEST SCORES");
        stats_queries.add("BEST BATTING STRIKE RATE");
        stats_queries.add("MOST WICKETS");
        stats_queries.add("BEST ECONOMY");
        stats_queries.add("BEST BOWLING STRIKE RATE");
        stats_queries.add("MOST CATCHES");
        stats_queries.add("MOST MAN OF THE MATCHES");


        stats_rec_view = root.findViewById(R.id.stats_rec_view);
//        standings_rec_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        stats_rec_view_adapter = new StatsRecAdapter(stats_queries);
        stats_rec_view.setLayoutManager(layoutManager);
        stats_rec_view.setAdapter(stats_rec_view_adapter);

        return root;
    }
}
