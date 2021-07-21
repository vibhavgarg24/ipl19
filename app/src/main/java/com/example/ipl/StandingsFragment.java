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

public class StandingsFragment extends Fragment {

    private RecyclerView standings_rec_view;
    private RecyclerView.Adapter standings_rec_view_adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Team> teams;

    public StandingsFragment (ArrayList<Team> teams) {
        this.teams = teams;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_standings, container, false);

        standings_rec_view = root.findViewById(R.id.standings_rec_view);
//        standings_rec_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        standings_rec_view_adapter = new StandingsRecAdapter(teams);
        standings_rec_view.setLayoutManager(layoutManager);
        standings_rec_view.setAdapter(standings_rec_view_adapter);

        return root;
    }
}
