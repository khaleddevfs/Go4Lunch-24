package com.example.go4lunch24.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;


import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainViewModel extends ViewModel {


    private final WorkMatesRepository workMatesRepository;

    public MutableLiveData<String> selectedRestaurantId = new MutableLiveData<>();

    public MainViewModel(WorkMatesRepository workMatesRepository) {
        this.workMatesRepository = workMatesRepository;
    }



    private FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }


}
