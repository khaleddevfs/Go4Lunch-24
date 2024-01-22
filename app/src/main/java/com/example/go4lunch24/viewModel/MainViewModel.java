package com.example.go4lunch24.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainViewModel extends ViewModel {


    private final WorkMatesRepository workMatesRepository;

    public MutableLiveData<String> selectedRestaurantId = new MutableLiveData<>();

    public MainViewModel(WorkMatesRepository workMatesRepository) {
        this.workMatesRepository = workMatesRepository;
    }

    public void getSelectedRestaurant() {
        String uid = (getCurrentUser() != null) ? getCurrentUser().getUid() : "default";
        workMatesRepository.getWorkMateFromFirebase(uid)
                .addOnSuccessListener(documentSnapshot -> {
                    WorkMate workMate = documentSnapshot.toObject(WorkMate.class);
                    if (workMate != null && workMate.getWorkMateRestaurantChoice() != null) {
                        selectedRestaurantId.setValue(workMate.getWorkMateRestaurantChoice().getRestaurantId());
                    }
                });
    }




    private FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }


 }

