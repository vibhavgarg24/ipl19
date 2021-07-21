package com.example.ipl;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StatsRecAdapter extends RecyclerView.Adapter<StatsRecAdapter.ViewHolder> {

    private ArrayList<String> stats_queries;

    public StatsRecAdapter(ArrayList<String> stats_queries) {
        this.stats_queries = stats_queries;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.stats_query_card, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, final int position) {
        final Context context = holder.itemView.getContext();

        final String stats_query = stats_queries.get(position);

        holder.stat_query_text.setText(stats_query);


//Query onClick
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, StatsResultActivity.class);
                intent.putExtra("stats_query_position", position);
                intent.putExtra("stats_query", stats_query);
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return stats_queries.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView stat_query_text;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            stat_query_text = itemView.findViewById(R.id.stat_query_text);
        }
    }
}
