package com.example.go4lunch24.viewModel;


import com.example.go4lunch24.repositories.RestaurantRepository;
import com.example.go4lunch24.repositories.WorkMatesRepository;

public class MapsViewModel extends BaseViewModel {


    public MapsViewModel(RestaurantRepository restaurantRepository, WorkMatesRepository workMatesRepository) {
        this.restaurantRepository = restaurantRepository;
        this.workmatesRepository = workMatesRepository;
    }


}
