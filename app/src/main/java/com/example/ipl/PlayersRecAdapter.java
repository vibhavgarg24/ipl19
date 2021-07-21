package com.example.ipl;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.io.InputStream;
import java.util.ArrayList;

public class PlayersRecAdapter extends RecyclerView.Adapter<PlayersRecAdapter.ViewHolder> {

    private ArrayList<Player> players;
    private int captain_id;

    public PlayersRecAdapter(ArrayList<Player> players, int captain_id) {
        this.players = players;
        this.captain_id = captain_id;
    }

    @NonNull
    @Override
    public PlayersRecAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.player_card,parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PlayersRecAdapter.ViewHolder holder, int position) {
        final Context context = holder.itemView.getContext();
        final Player player = players.get(position);

        String player_name = player.getPlayer_name();
        if (player.getPlayer_id() == captain_id) {
            player_name += "  (c)";
        }
        holder.player_name.setText(player_name);
        holder.player_type.setText(player.getPlayer_type());


// Player pic
        String url_str = player.getPic_url();
        try {
            new DownloadImage(holder.player_pic).execute(url_str);
        } catch (Exception e) {
            Log.d("ipl", "image load error : "+e.getMessage());
        }


// Player onClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayerStats.class);
                intent.putExtra("player_id", player.getPlayer_id());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView player_pic;
        public TextView player_name;
        public TextView player_type;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            player_pic = itemView.findViewById(R.id.player_pic);
            player_name = itemView.findViewById(R.id.player_name);
            player_type = itemView.findViewById(R.id.player_type);
        }
    }


    public class DownloadImage extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        public DownloadImage(ImageView bmImage) {
            this.bmImage = (ImageView) bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.d("ipl", "image load error : " + e.getMessage());
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
