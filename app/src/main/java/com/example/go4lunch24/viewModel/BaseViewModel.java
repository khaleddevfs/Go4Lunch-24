package com.example.go4lunch24.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.go4lunch24.models.Restaurant;
import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.RestaurantRepository;
import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class BaseViewModel extends ViewModel {

    public final MutableLiveData<List<String>> workMatesIdMutableLiveData = new MutableLiveData<>();

    protected WorkMate workMate;

    protected WorkMatesRepository workmatesRepository;

    protected RestaurantRepository restaurantRepository;


    public LiveData<List<Restaurant>> getRestaurantList(double latitude, double longitude) {
        return restaurantRepository.getGoogleRestaurantList(latitude, longitude);
    }

    public LiveData<List<Restaurant>> getRestaurants (){
        return  restaurantRepository.restaurantListMutableLiveData;
    }

    public void fetchWorkMatesGoing(){
        List<String> workMatesToAdd = new ArrayList<>();
        workmatesRepository.getAllWorkMate()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                        WorkMate workMate = documentSnapshot.toObject(WorkMate.class);
                        assert workMate != null;
                        if (workMate.getWorkMateRestaurantChoice() != null && workMate.getWorkMateRestaurantChoice(). getRestaurantId() !=null ) {
                            String restaurantUid = workMate.getWorkMateRestaurantChoice().getRestaurantId();
                            workMatesToAdd.add(restaurantUid);
                        }
                    }
                    workMatesIdMutableLiveData.setValue(workMatesToAdd);
                });
    }


}
