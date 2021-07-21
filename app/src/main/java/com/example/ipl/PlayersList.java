package com.example.ipl;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.os.Bundle;
import java.util.ArrayList;

public class PlayersList extends AppCompatActivity {

    private RecyclerView players_rec_view;
    private RecyclerView.Adapter players_rec_view_adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ArrayList<Player> players;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_players_list);

        getSupportActionBar().hide();

        int team_id = getIntent().getIntExtra("team_id", 0);

// Players from Db
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(PlayersList.this);
        dbAccess.openConnection();

        players = dbAccess.getPlayers(team_id);
        int captain_id = dbAccess.getCaptainID(team_id);

        dbAccess.closeConnection();
//

        players_rec_view = findViewById(R.id.players_rec_view);
        players_rec_view.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(PlayersList.this);
        players_rec_view_adapter = new PlayersRecAdapter(players,captain_id);
        players_rec_view.setLayoutManager(layoutManager);
        players_rec_view.setAdapter(players_rec_view_adapter);

    }
}