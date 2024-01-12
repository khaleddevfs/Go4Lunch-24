package com.example.go4lunch24.adapters;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.go4lunch24.databinding.RestaurantItemLayoutBinding;
import com.example.go4lunch24.models.Restaurant;

import java.util.List;

public class RestaurantAdapter extends RecyclerView.Adapter<RestaurantViewHolder> {

    private List<Restaurant> restaurantList;
    private List<String> workMateIds;

    public RestaurantAdapter() {
    }

    public void setRestaurantList(List<Restaurant> restaurantList, List<String> coworkerIds) {
        this.restaurantList = restaurantList;
        this.workMateIds = coworkerIds;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RestaurantViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RestaurantItemLayoutBinding binding = RestaurantItemLayoutBinding.inflate(LayoutInflater.from(parent.getContext()));
        return new RestaurantViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull RestaurantViewHolder holder, int position) {
        holder.updateRestaurantInfo(restaurantList.get(position), workMateIds);
    }

    @Override
    public int getItemCount() {
        if (restaurantList != null)
            return restaurantList.size();
        return 0;
    }
}
