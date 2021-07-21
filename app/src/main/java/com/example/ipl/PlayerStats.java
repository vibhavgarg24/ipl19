package com.example.ipl;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;


public class PlayerStats extends AppCompatActivity {

    boolean isFav;
    int player_id;
    ImageView fav_star;

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_stats);

        getSupportActionBar().hide();

        player_id = getIntent().getIntExtra("player_id", 0);

//  Player from Db
        DatabaseAccess dbAccess = DatabaseAccess.getInstance(PlayerStats.this);
        dbAccess.openConnection();

        Player player = dbAccess.getPlayer(player_id);
        PlayerRecords records = dbAccess.getPlayerRecords(player_id);
        isFav = dbAccess.isFavourite(player_id);

        dbAccess.closeConnection();


// Setting Player Pic
        ImageView stat_player_pic = findViewById(R.id.stat_player_pic);
        String url_str = player.getPic_url();
        try {
            new DownloadImage(stat_player_pic).execute(url_str);
        } catch (Exception e) {
            Log.d("ipl", "image load error : "+e.getMessage());
        }


// Setting Player Basic Info
        TextView stat_player_name = findViewById(R.id.stat_player_name);
        stat_player_name.setText(player.getPlayer_name());

        TextView player_role = findViewById(R.id.player_role);
        player_role.setText(player.getPlayer_type());

        TextView player_team = findViewById(R.id.player_team);
        int player_team_id = player.getTeam_id();
        String player_team_name = player.getTeam_name();
        player_team.setText(player_team_name);

        TextView player_origin = findViewById(R.id.player_origin);
        player_origin.setText(player.getCountry());

// Setting Player Statistics
        TextView player_total_runs_scored = findViewById(R.id.player_total_runs_scored);
        player_total_runs_scored.setText(String.valueOf(records.getTotal_runs_scored()));

        TextView player_total_balls_played = findViewById(R.id.player_total_balls_played);
        player_total_balls_played.setText(String.valueOf(records.getTotal_balls_played()));

        TextView player_strike_rate = findViewById(R.id.player_strike_rate);
        player_strike_rate.setText(String.format("%.2f", records.getStrike_rate()));

        TextView player_highest_score = findViewById(R.id.player_highest_score);
        player_highest_score.setText(String.valueOf(records.getHighest_score()));

        TextView player_total_fours = findViewById(R.id.player_total_fours);
        player_total_fours.setText(String.valueOf(records.getTotal_fours()));

        TextView player_total_sixes = findViewById(R.id.player_total_sixes);
        player_total_sixes.setText(String.valueOf(records.getTotal_sixes()));

        TextView player_total_fifties = findViewById(R.id.player_total_fifties);
        player_total_fifties.setText(String.valueOf(records.getTotal_fifties()));

        TextView player_total_centuries = findViewById(R.id.player_total_centuries);
        player_total_centuries.setText(String.valueOf(records.getTotal_centuries()));

        TextView player_total_runs_given = findViewById(R.id.player_total_runs_given);
        player_total_runs_given.setText(String.valueOf(records.getTotal_runs_given()));

        TextView player_total_balls_bowled = findViewById(R.id.player_total_balls_bowled);
        player_total_balls_bowled.setText(String.valueOf(records.getTotal_balls_bowled()));

        TextView player_economy = findViewById(R.id.player_economy);
        player_economy.setText(String.format("%.2f",records.getTotal_economy()));

        TextView player_total_wickets = findViewById(R.id.player_total_wickets);
        player_total_wickets.setText(String.valueOf(records.getTotal_wickets()));

        TextView player_bowling_strike_rate = findViewById(R.id.player_bowling_strike_rate);
        player_bowling_strike_rate.setText(String.format("%.2f",records.getBowling_strike_rate()));

        TextView player_total_catches = findViewById(R.id.player_total_catches);
        player_total_catches.setText(String.valueOf(records.getTotal_catches()));

// Favourites
        fav_star = findViewById(R.id.fav_star);
        if (isFav) {
            fav_star.setImageResource(R.drawable.ic_star_filled_yellow_24);
        } else {
            fav_star.setImageResource(R.drawable.ic_star_border_white_24);
        }

        fav_star.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseAccess dbAccess = DatabaseAccess.getInstance(PlayerStats.this);
                dbAccess.openConnection();

                if (isFav) {
                    dbAccess.removeFav(player_id);
                    isFav = false;
                    fav_star.setImageResource(R.drawable.ic_star_border_white_24);
                    Toast.makeText(PlayerStats.this, "Removed from Favourites", Toast.LENGTH_SHORT).show();

                } else {
                    dbAccess.addFav(player_id);
                    isFav = true;
                    fav_star.setImageResource(R.drawable.ic_star_filled_yellow_24);
                    Toast.makeText(PlayerStats.this, "Added to Favourites", Toast.LENGTH_SHORT).show();
                }

                dbAccess.closeConnection();
            }
        });

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
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
                Toast.makeText(PlayerStats.this, "Image Load Error", Toast.LENGTH_SHORT).show();
                bmImage.setImageResource(R.drawable.ic_person_white_24);
            }
        }
    }
}