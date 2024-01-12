package com.example.go4lunch24.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;


import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.RestaurantRepository;
import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;


public class WorkMateViewModel extends BaseViewModel {

    private MutableLiveData<List<WorkMate>> workMates = new MutableLiveData<>();

    public LiveData<List<WorkMate>> getWorkMates(){
        return workMates;
    }

    public WorkMateViewModel(WorkMatesRepository workmatesRepository, RestaurantRepository restaurantRepository) {
        this.workmatesRepository = workmatesRepository;
        this.restaurantRepository = restaurantRepository;
        workMate = workmatesRepository.getActualUser();
    }

    public void fetchListUsersFromFirebase(){
        workmatesRepository.getAllWorkMate().addOnSuccessListener(queryDocumentSnapshots -> {
            List<WorkMate> fetchedUsers = new ArrayList<>();
            for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                WorkMate userFetched = documentSnapshot.toObject(WorkMate.class);
                fetchedUsers.add(userFetched);
            }
            workMates.setValue(fetchedUsers);
        });
    }
}
