package com.example.ipl;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BowlingRecAdapter extends RecyclerView.Adapter<BowlingRecAdapter.ViewHolder> {

    private ArrayList<Bowler> bowlers;

    public BowlingRecAdapter(ArrayList<Bowler> bowlers) {
        this.bowlers = bowlers;
    }

    @NonNull
    @Override
    public BowlingRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scorecard_bowling_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("DefaultLocale")
    @Override
    public void onBindViewHolder(@NonNull BowlingRecAdapter.ViewHolder holder, int position) {

        Bowler bowler = bowlers.get(position);

        holder.scorecard_bowler_name.setText(bowler.getPlayer_name());
        holder.scorecard_overs.setText(bowler.getOvers());
        holder.scorecard_runs_given.setText(String.valueOf(bowler.getRuns_given()));
        holder.scorecard_wickets.setText(String.valueOf(bowler.getWickets()));
        holder.scorecard_economy.setText(String.format("%.2f",bowler.getEconomy()));

    }

    @Override
    public int getItemCount() {
        return bowlers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView scorecard_bowler_name;
        public TextView scorecard_overs;
        public TextView scorecard_runs_given;
        public TextView scorecard_wickets;
        public TextView scorecard_economy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            scorecard_bowler_name = itemView.findViewById(R.id.scorecard_bowler_name);
            scorecard_overs = itemView.findViewById(R.id.scorecard_overs);
            scorecard_runs_given = itemView.findViewById(R.id.scorecard_runs_given);
            scorecard_wickets = itemView.findViewById(R.id.scorecard_wickets);
            scorecard_economy = itemView.findViewById(R.id.scorecard_economy);

        }
    }
}
