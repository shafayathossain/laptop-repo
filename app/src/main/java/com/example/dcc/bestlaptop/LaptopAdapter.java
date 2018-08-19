package com.example.dcc.bestlaptop;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class LaptopAdapter extends RecyclerView.Adapter<LaptopAdapter.LaptopViewHolder> {

    List<Laptop>laptops;
    int rank = 1;

    public LaptopAdapter(List<Laptop>laptops){
        this.laptops = laptops;
    }

    @NonNull
    @Override
    public LaptopViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_laptop, parent, false);
        return new LaptopViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LaptopViewHolder holder, int position) {
        holder.laptopModelTextView.setText(laptops.get(position).getModel());
        if(laptops.get(position).getRank()==-1)
            laptops.get(position).setRank(rank);
        holder.rankTextView.setText(Integer.toString(laptops.get(position).getRank()));
        rank++;
        holder.ramTextView.setText(laptops.get(position).getRam());
        holder.cpuTextView.setText(laptops.get(position).getCpu());
        holder.priceTextView.setText(Integer.toString(laptops.get(position).getPrice()));
        holder.displayTextView.setText(laptops.get(position).getDisplay());
        holder.graphicsTextView.setText(laptops.get(position).getGraphics());
        holder.storageTextView.setText(laptops.get(position).getStorage());
    }

    @Override
    public int getItemCount() {
        return laptops.size();
    }

    public class LaptopViewHolder extends RecyclerView.ViewHolder{

        TextView laptopModelTextView, rankTextView, ramTextView, cpuTextView, priceTextView, displayTextView, graphicsTextView, storageTextView;

        public LaptopViewHolder(View itemView) {
            super(itemView);

            laptopModelTextView = itemView.findViewById(R.id.laptopModelTextView);
            rankTextView = itemView.findViewById(R.id.rankTextView);
            ramTextView = itemView.findViewById(R.id.ramTextView);
            cpuTextView = itemView.findViewById(R.id.cpuTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            displayTextView = itemView.findViewById(R.id.displayTextView);
            graphicsTextView = itemView.findViewById(R.id.graphicsTextView);
            storageTextView = itemView.findViewById(R.id.storageTextView);
        }
    }
}
