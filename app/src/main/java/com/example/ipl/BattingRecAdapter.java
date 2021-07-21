package com.example.ipl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class BattingRecAdapter extends RecyclerView.Adapter<BattingRecAdapter.ViewHolder> {

    private ArrayList<Batsman> batsmen;

    public BattingRecAdapter(ArrayList<Batsman> batsmen) {
        this.batsmen = batsmen;
    }

    @NonNull
    @Override
    public BattingRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.scorecard_batting_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BattingRecAdapter.ViewHolder holder, int position) {

        Batsman batsman = batsmen.get(position);

        holder.scorecard_batsman_name.setText(batsman.getPlayer_name());
        holder.scorecard_runs.setText(String.valueOf(batsman.getRuns()));
        holder.scorecard_balls.setText(String.valueOf(batsman.getBalls()));
        holder.scorecard_fours.setText(String.valueOf(batsman.getFours()));
        holder.scorecard_sixes.setText(String.valueOf(batsman.getSixes()));

    }

    @Override
    public int getItemCount() {
        return batsmen.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView scorecard_batsman_name;
        public TextView scorecard_runs;
        public TextView scorecard_balls;
        public TextView scorecard_fours;
        public TextView scorecard_sixes;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            scorecard_batsman_name = itemView.findViewById(R.id.scorecard_batsman_name);
            scorecard_runs = itemView.findViewById(R.id.scorecard_runs);
            scorecard_balls = itemView.findViewById(R.id.scorecard_balls);
            scorecard_fours = itemView.findViewById(R.id.scorecard_fours);
            scorecard_sixes = itemView.findViewById(R.id.scorecard_sixes);
        }
    }
}
