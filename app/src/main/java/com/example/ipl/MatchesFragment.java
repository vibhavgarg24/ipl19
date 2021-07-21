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

public class MatchesFragment extends Fragment {

    private RecyclerView matches_rec_view;
    private RecyclerView.Adapter matches_rec_view_adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Match> matches;

    public MatchesFragment (ArrayList<Match> matches) {
        this.matches = matches;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_matches, container, false);

        matches_rec_view = root.findViewById(R.id.matches_rec_view);
        matches_rec_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        matches_rec_view_adapter = new MatchesRecAdapter(matches);
        matches_rec_view.setLayoutManager(layoutManager);
        matches_rec_view.setAdapter(matches_rec_view_adapter);

        return root;
    }
}
