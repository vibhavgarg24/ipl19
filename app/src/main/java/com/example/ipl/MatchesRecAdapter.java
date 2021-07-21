package com.example.ipl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class MatchesRecAdapter extends RecyclerView.Adapter<MatchesRecAdapter.ViewHolder> {

    private ArrayList<Match> match_list;

    public MatchesRecAdapter(ArrayList<Match> match_list) {
        this.match_list = match_list;
    }

    @NonNull
    @Override
    public MatchesRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.match_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MatchesRecAdapter.ViewHolder holder, int position) {

        final Context context = holder.itemView.getContext();
        Match match = match_list.get(position);

// Match Number
        final int match_id = match.getMatch_id();
        if (match_id > 56) {
            if (match_id == 57)
                holder.match_number_text.setText("Qualifier 1 - Indian Premier League 2019");
            else if (match_id == 58)
                holder.match_number_text.setText("Eliminator - Indian Premier League 2019");
            else if (match_id == 59)
                holder.match_number_text.setText("Qualifier 2 - Indian Premier League 2019");
            else if (match_id == 60)
                holder.match_number_text.setText("Final - Indian Premier League 2019");
        } else {
            holder.match_number_text.setText("Match " + match_id + " - Indian Premier League 2019");
        }


        if (match.getBat_first() == 1) {
// Team 1
            holder.team1_abv.setTypeface(Typeface.DEFAULT_BOLD);
            holder.team2_abv.setTypeface(Typeface.DEFAULT);
            holder.team1_score.setTypeface(Typeface.DEFAULT_BOLD);
            holder.team2_score.setTypeface(Typeface.DEFAULT);

            int team1_id = match.getTeam1_id();
            String team1_abv = match.getTeam1_abv();
            holder.team1_abv.setText(team1_abv);
            holder.team1_score.setText(match.getTeam1_score());
            String team1_logo_name = "drawable/team_logo_" + team1_id;
            int team_logo_id = context.getResources().getIdentifier(team1_logo_name, null, context.getPackageName());
            holder.team1_logo.setImageResource(team_logo_id);

// Team 2
            int team2_id = match.getTeam2_id();
            String team2_abv = match.getTeam2_abv();
            holder.team2_abv.setText(team2_abv);
            holder.team2_score.setText(match.getTeam2_score());
            String team2_logo_name = "drawable/team_logo_" + team2_id;
            int team2_logo_id = context.getResources().getIdentifier(team2_logo_name, null, context.getPackageName());
            holder.team2_logo.setImageResource(team2_logo_id);
        } else if (match.getBat_first() == 2){
// Team 1
            holder.team1_abv.setTypeface(Typeface.DEFAULT);
            holder.team2_abv.setTypeface(Typeface.DEFAULT_BOLD);
            holder.team1_score.setTypeface(Typeface.DEFAULT);
            holder.team2_score.setTypeface(Typeface.DEFAULT_BOLD);

            int team1_id = match.getTeam2_id();
            String team1_abv = match.getTeam2_abv();
            holder.team1_abv.setText(team1_abv);
            holder.team1_score.setText(match.getTeam2_score());
            String team1_logo_name = "drawable/team_logo_" + team1_id;
            int team_logo_id = context.getResources().getIdentifier(team1_logo_name, null, context.getPackageName());
            holder.team1_logo.setImageResource(team_logo_id);

// Team 2
            int team2_id = match.getTeam1_id();
            String team2_abv = match.getTeam1_abv();
            holder.team2_abv.setText(team2_abv);
            holder.team2_score.setText(match.getTeam1_score());
            String team2_logo_name = "drawable/team_logo_" + team2_id;
            int team2_logo_id = context.getResources().getIdentifier(team2_logo_name, null, context.getPackageName());
            holder.team2_logo.setImageResource(team2_logo_id);
        }

// Match Result
        String result = "";
        if (match.getResult().equals("No Result")) {
            result = "No Result - 1 point awarded to each team";
            holder.team1_abv.setTypeface(Typeface.DEFAULT);
            holder.team1_score.setTypeface(Typeface.DEFAULT);
            holder.team2_abv.setTypeface(Typeface.DEFAULT);
            holder.team2_score.setTypeface(Typeface.DEFAULT);

        } else {
            int win_by_runs = match.getWin_by_runs();
            int win_by_wickets = match.getWin_by_wickets();
            String team1_name = match.getTeam1_name();
            result = team1_name + " won";
            if (win_by_runs != 0) {
                result += " by " + win_by_runs + " run(s)";
            } else if (win_by_wickets != 0) {
                result += " by " + win_by_wickets + " wicket(s)";
            } else {
                result += " the Super Over";
            }
        }
        holder.match_result_text.setText(result);


// Match onClick
        final String finalResult = result;
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, Scorecard.class);
                intent.putExtra("match_id", match_id);
                intent.putExtra("match_result", finalResult);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return match_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView match_number_text;
        public TextView match_result_text;

        public TextView team1_abv;
        public TextView team1_score;
        public ImageView team1_logo;

        public TextView team2_abv;
        public TextView team2_score;
        public ImageView team2_logo;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            match_number_text = itemView.findViewById(R.id.match_number_text);
            match_result_text = itemView.findViewById(R.id.match_result_text);

            team1_abv = itemView.findViewById(R.id.team1_name);
            team1_score = itemView.findViewById(R.id.team1_score);
            team1_logo = itemView.findViewById(R.id.team1_logo);

            team2_abv = itemView.findViewById(R.id.team2_name);
            team2_score = itemView.findViewById(R.id.team2_score);
            team2_logo = itemView.findViewById(R.id.team2_logo);

        }
    }
}
