package com.example.go4lunch24.viewModel;

import com.example.mygo4lunch.repositories.RestaurantRepository;
import com.example.mygo4lunch.repositories.WorkMatesRepository;

public class RestaurantViewModel extends BaseViewModel{

    public RestaurantViewModel(RestaurantRepository restaurantRepository, WorkMatesRepository workMatesRepository){
        this.restaurantRepository = restaurantRepository;
        this.workmatesRepository = workMatesRepository;
    }
}
