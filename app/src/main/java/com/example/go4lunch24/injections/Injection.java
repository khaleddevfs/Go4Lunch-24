package com.example.go4lunch24.injections;


import com.example.go4lunch24.factory.Go4LunchFactory;
import com.example.go4lunch24.repositories.RestaurantRepository;
import com.example.go4lunch24.repositories.SaveDataRepository;
import com.example.go4lunch24.repositories.WorkMatesRepository;

public class Injection {

    public static RestaurantRepository provideRestaurantRepository() {
        return RestaurantRepository.getInstance();
    }

    public static WorkMatesRepository provideWorkMateRepository() {
        return WorkMatesRepository.getInstance();
    }

    public static SaveDataRepository provideSaveDataRepository() {
        return SaveDataRepository.getInstance();
    }



    public static Go4LunchFactory provideViewModelFactory() {
        RestaurantRepository restaurantRepository = provideRestaurantRepository();
        WorkMatesRepository workMatesRepository = provideWorkMateRepository();
        SaveDataRepository saveDataRepository = provideSaveDataRepository();

        return new Go4LunchFactory(restaurantRepository, workMatesRepository, saveDataRepository);
    }



}
