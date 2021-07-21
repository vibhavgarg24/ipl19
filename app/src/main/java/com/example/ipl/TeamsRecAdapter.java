package com.example.ipl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class TeamsRecAdapter extends RecyclerView.Adapter<TeamsRecAdapter.ViewHolder> {

    private ArrayList<String> team_list;

    public TeamsRecAdapter(ArrayList<String> team_list) {
        this.team_list = team_list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.team_card, parent, false);
        ViewHolder  viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Context context = holder.itemView.getContext();

        String team_name = team_list.get(position);
        holder.team_name.setText(team_name);

// Set Team Logo
        int team_id = position + 1;
        String team_logo_name = "drawable/team_logo_" + team_id;
        int team_logo_id =  context.getResources().getIdentifier(team_logo_name, null, context.getPackageName());
        holder.team_logo.setImageResource(team_logo_id);

// Team onClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, PlayersList.class);
                intent.putExtra("team_id", position + 1);
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return team_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView team_logo;
        public TextView team_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            team_logo = itemView.findViewById(R.id.team_logo);
            team_name = itemView.findViewById(R.id.team_name);

        }
    }
}