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

public class FavouritesFragment extends Fragment {

    private RecyclerView fav_rec_view;
    private RecyclerView.Adapter fav_rec_view_adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Player> favourites;

    public FavouritesFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_favourites, container, false);


        DatabaseAccess dbAccess = DatabaseAccess.getInstance(getContext());
        dbAccess.openConnection();

        favourites = dbAccess.getFavourites();

        dbAccess.closeConnection();

        fav_rec_view = root.findViewById(R.id.fav_rec_view);
        fav_rec_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(getContext());
        fav_rec_view_adapter = new PlayersRecAdapter(favourites,0);
        fav_rec_view.setLayoutManager(layoutManager);
        fav_rec_view.setAdapter(fav_rec_view_adapter);


        return root;
    }

    @Override
    public void onResume() {

        DatabaseAccess dbAccess = DatabaseAccess.getInstance(getContext());
        dbAccess.openConnection();

        favourites = dbAccess.getFavourites();

        dbAccess.closeConnection();

        layoutManager = new LinearLayoutManager(getContext());
        fav_rec_view_adapter = new PlayersRecAdapter(favourites,0);
        fav_rec_view.setLayoutManager(layoutManager);
        fav_rec_view.setAdapter(fav_rec_view_adapter);

        super.onResume();

    }

}
