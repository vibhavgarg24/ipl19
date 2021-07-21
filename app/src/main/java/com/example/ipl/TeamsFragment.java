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

public class TeamsFragment extends Fragment {

    private RecyclerView teams_rec_view;
    private RecyclerView.Adapter teams_rec_view_adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<String> teams;

    public TeamsFragment (ArrayList<String> teams) {
        this.teams = teams;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_teams, container, false);

        teams_rec_view = root.findViewById(R.id.teams_rec_view);
        teams_rec_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        teams_rec_view_adapter = new TeamsRecAdapter(teams);
        teams_rec_view.setLayoutManager(layoutManager);
        teams_rec_view.setAdapter(teams_rec_view_adapter);

        return root;
    }
}
