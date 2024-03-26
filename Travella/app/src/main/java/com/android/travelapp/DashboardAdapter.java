package com.android.travelapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class DashboardAdapter extends RecyclerView.Adapter<DashboardAdapter.ViewHolder> {

    private Context context;
    private ArrayList<String> imageUrls;
    private ArrayList<String> names;
    private ArrayList<String> descriptions;
    private ArrayList<Integer> prices;
    private ArrayList<String> locations;

    public DashboardAdapter(Context context, ArrayList<String> imageUrls, ArrayList<String> names, ArrayList<String> descriptions, ArrayList<Integer> prices, ArrayList<String> locations) {
        this.context = context;
        this.imageUrls = imageUrls;
        this.names = names;
        this.descriptions = descriptions;
        this.prices = prices;
        this.locations = locations;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_dashboard, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.nameTextView.setText(names.get(position));
        holder.descriptionTextView.setText(descriptions.get(position));
        holder.priceTextView.setText("CAD" + prices.get(position));
        holder.locationTextView.setText(locations.get(position));
        Glide.with(context).load(imageUrls.get(position)).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return names.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView nameTextView;
        TextView descriptionTextView;
        TextView priceTextView;
        TextView locationTextView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            descriptionTextView = itemView.findViewById(R.id.descriptionTextView);
            priceTextView = itemView.findViewById(R.id.priceTextView);
            locationTextView = itemView.findViewById(R.id.locationTextView);
        }
    }
}
