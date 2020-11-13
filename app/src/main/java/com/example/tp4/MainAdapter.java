package com.example.tp4;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.volley.RequestQueue;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {
    ArrayList<CityModel> cities_list;
    private Context context;
    private SelectedCity selectedCity;

    public MainAdapter(ArrayList<CityModel> cities, SelectedCity selectedCity) {
        this.cities_list = cities;
        this.selectedCity = selectedCity;
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
        holder.city_name.setText(cities_list.get(position).getCity_name());
        /*holder.temperature.setText(cities_list.get(position).getTemperature());
        holder.temp_feels_like.setText(cities_list.get(position).getTemp_feels_like());
        holder.temp_min.setText(cities_list.get(position).getTemp_min());
        holder.temp_max.setText(cities_list.get(position).getTemp_max());*/
    }

    @Override
    public int getItemCount() {
        return cities_list.size();
    }

    public interface SelectedCity {
        void selectedCity(CityModel cityModel);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView city_name;
        ImageView weather_icon;
        TextView temperature;
        TextView temp_feels_like;
        TextView temp_min;
        TextView temp_max;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            city_name = (TextView) itemView.findViewById(R.id.city);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    selectedCity.selectedCity(cities_list.get(getAdapterPosition()));
                }
            });
        }
    }


}
