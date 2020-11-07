package com.example.tp4;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<String> cities_list;

    public MainAdapter(ArrayList<String> cities){
        cities_list = cities;
    }

    //ViewHolder = visually represent an item of the ArrayList in the RecyclerView
    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder holder, int position) {
        holder.city_name.setText(cities_list.get(position));
    }

    @Override
    public int getItemCount() {
        return cities_list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView city_name;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            city_name = (TextView) itemView.findViewById(R.id.city);
        }
    }
}
