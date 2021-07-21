package com.example.ipl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StandingsRecAdapter extends RecyclerView.Adapter<StandingsRecAdapter.ViewHolder> {

    private ArrayList<Team> teams;

    public StandingsRecAdapter(ArrayList<Team> teams) {
        this.teams = teams;
    }

    @NonNull
    @Override
    public StandingsRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.points_table_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull StandingsRecAdapter.ViewHolder holder, int position) {

        Team team = teams.get(position);

        holder.table_team_name.setText(team.getTeam_abv());
        holder.table_team_matches.setText(String.valueOf(team.getMatches()));
        holder.table_team_won.setText(String.valueOf(team.getWon()));
        holder.table_team_lost.setText(String.valueOf(team.getLost()));
        holder.table_team_points.setText(String.valueOf(team.getPoints()));
        holder.table_team_nrr.setText(team.getNrr());
    }

    @Override
    public int getItemCount() {
        return teams.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView table_team_name;
        public TextView table_team_matches;
        public TextView table_team_won;
        public TextView table_team_lost;
        public TextView table_team_points;
        public TextView table_team_nrr;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            table_team_name = itemView.findViewById(R.id.table_team_name);
            table_team_matches = itemView.findViewById(R.id.table_team_matches);
            table_team_won = itemView.findViewById(R.id.table_team_won);
            table_team_lost = itemView.findViewById(R.id.table_team_lost);
            table_team_points = itemView.findViewById(R.id.table_team_points);
            table_team_nrr = itemView.findViewById(R.id.table_team_nrr);

        }
    }
}
