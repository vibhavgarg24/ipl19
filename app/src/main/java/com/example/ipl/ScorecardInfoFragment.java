package com.example.ipl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import java.io.InputStream;

public class ScorecardInfoFragment extends Fragment {

    private Match match;
    private String match_result;

    public ScorecardInfoFragment(Match match, String match_result) {
        this.match = match;
        this.match_result = match_result;
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_scorecard_info, container, false);

        TextView info_team1_name = root.findViewById(R.id.info_team1_name);
        TextView info_team1_score = root.findViewById(R.id.info_team1_score);
        TextView info_team2_name = root.findViewById(R.id.info_team2_name);
        TextView info_team2_score = root.findViewById(R.id.info_team2_score);


        if (match.getBat_first() == 1) {
            info_team1_name.setTextColor(Color.WHITE);
            info_team1_score.setTextColor(Color.WHITE);
            info_team2_name.setTextColor(Color.LTGRAY);
            info_team2_score.setTextColor(Color.LTGRAY);

// Team 1
            String team1_abv = match.getTeam1_abv();
            info_team1_name.setText(team1_abv);
            info_team1_score.setText(match.getTeam1_score());

// Team 2

            String team2_abv = match.getTeam2_abv();
            info_team2_name.setText(team2_abv);
            info_team2_score.setText(match.getTeam2_score());

        } else if (match.getBat_first() == 2){
            info_team2_name.setTextColor(Color.WHITE);
            info_team2_score.setTextColor(Color.WHITE);
            info_team1_name.setTextColor(Color.LTGRAY);
            info_team1_score.setTextColor(Color.LTGRAY);

// Team 1
            String team1_abv = match.getTeam2_abv();
            info_team1_name.setText(team1_abv);
            info_team1_score.setText(match.getTeam2_score());

// Team 2
            String team2_abv = match.getTeam1_abv();
            info_team2_name.setText(team2_abv);
            info_team2_score.setText(match.getTeam1_score());

        }


// Match Result
        if (match_result.equals("No Result - 1 point awarded to each team")) {
            info_team1_name.setTextColor(Color.LTGRAY);
            info_team1_score.setTextColor(Color.LTGRAY);
            info_team2_name.setTextColor(Color.LTGRAY);
            info_team2_score.setTextColor(Color.LTGRAY);
        }
        TextView info_result = root.findViewById(R.id.info_result);
        info_result.setText(match_result);


// Setting Match Info
        TextView match_date = root.findViewById(R.id.match_date);
        match_date.setText(match.getDate());

        TextView match_city = root.findViewById(R.id.match_city);
        match_city.setText(match.getCity());

        TextView match_toss = root.findViewById(R.id.match_toss);
        String toss_winner = match.getToss_winner();
        String toss_decision = match.getToss_decision();
        String toss = toss_winner + " opt to " + toss_decision;
        match_toss.setText(toss);

        TextView match_player_name = root.findViewById(R.id.match_player_name);
        final int match_winner_id = match.getMatch_winner_id();
        String match_winner_name = match.getMatch_winner();
        match_player_name.setText(match_winner_name);


// Setting Player Pic
        ImageView match_player_pic = root.findViewById(R.id.match_player_pic);
        String url_str = match.getMatch_winner_pic_url();
        try {
            new DownloadImage(match_player_pic).execute(url_str);
        } catch (Exception e) {
            Log.d("ipl", "image load error : "+e.getMessage());
        }

// Player Pic onClick
        match_player_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlayerStats.class);
                intent.putExtra("player_id", match_winner_id);
                startActivity(intent);
            }
        });


// Player Name onClick
        match_player_name.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), PlayerStats.class);
                intent.putExtra("player_id", match_winner_id);
                startActivity(intent);
            }
        });

        return root;
    }

    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {
            this.bmImage = (ImageView ) bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.d("ipl", "image load error : "+e.getMessage());
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            if (result != null)
                bmImage.setImageBitmap(result);
            else {
                bmImage.setImageResource(R.drawable.ic_person_white_24);
            }
        }
    }
}
