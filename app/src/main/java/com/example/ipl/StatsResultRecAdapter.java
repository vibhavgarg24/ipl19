package com.example.ipl;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.ArrayList;

public class StatsResultRecAdapter extends RecyclerView.Adapter<StatsResultRecAdapter.ViewHolder> {

    private ArrayList<String5Data> stats_query_result_list;
    private int card_res_id;

    public StatsResultRecAdapter(ArrayList<String5Data> stats_query_result_list, int card_res_id) {
        this.stats_query_result_list = stats_query_result_list;
        this.card_res_id = card_res_id;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(card_res_id, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String5Data playerRecords = stats_query_result_list.get(position);

        if (position != 0) {
            holder.two_data_numbering.setText(position + ".");
        }

        holder.two_data_data1.setText(playerRecords.getData1());
        holder.two_data_data2.setText(playerRecords.getData2());

    }

    @Override
    public int getItemCount() {
        return stats_query_result_list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        TextView two_data_numbering;
        TextView two_data_data1;
        TextView two_data_data2;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            two_data_numbering = itemView.findViewById(R.id.two_data_numbering);
            two_data_data1 = itemView.findViewById(R.id.two_data_data1);
            two_data_data2 = itemView.findViewById(R.id.two_data_data2);
        }
    }
}
