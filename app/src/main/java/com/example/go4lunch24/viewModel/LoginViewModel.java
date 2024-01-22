package com.example.go4lunch24.viewModel;

import android.app.Activity;
import android.util.Log;


import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.firebase.ui.auth.AuthUI;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.Collections;

public class LoginViewModel extends BaseViewModel {


    public static final int RC_SIGN_IN = 100;

    public LoginViewModel(WorkMatesRepository workMatesRepository){
        this.workmatesRepository = workMatesRepository;
    }


    public void startLoginActivityEmail(Activity activity) {
        activity.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Collections.singletonList(new
                                AuthUI.IdpConfig.EmailBuilder().build()))
                        .setIsSmartLockEnabled(false, true)
                        .build(),
                RC_SIGN_IN);
    }


    public void startLoginActivityGoogle(Activity activity) {
        activity.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Collections.singletonList(new
                                AuthUI.IdpConfig.GoogleBuilder().build()))
                        .setIsSmartLockEnabled(false,true)
                        .build(),
                RC_SIGN_IN);
    }

    public void startLoginActivityTwitter(Activity activity) {
        activity.startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setAvailableProviders(Collections.singletonList(new
                                AuthUI.IdpConfig.TwitterBuilder().build()))
                        .setIsSmartLockEnabled(false, true)
                        .build(),
                RC_SIGN_IN);
    }


    public void updateCurrentUser() {
        String uid = (isCurrentUserLogged()) ? getCurrentUser().getUid() : "default";
        workmatesRepository.getWorkMateFromFirebase(uid)
                .addOnSuccessListener(documentSnapshot -> {
                    workMate = documentSnapshot.toObject(WorkMate.class);
                    if (workMate != null) {
                        workmatesRepository.updateCurrentUser(workMate);
                    } else {
                        createUserInFirestore();
                    }
                })
                .addOnFailureListener(error -> {
                    Log.e("tagii", "error: "+error);
                });
    }

    private void createUserInFirestore() {
        FirebaseUser currentUser = getCurrentUser();

        if (currentUser != null) {
            String uid = currentUser.getUid();
            String name = currentUser.getDisplayName();
            String email = currentUser.getEmail();
            String urlPicture = (currentUser.getPhotoUrl() != null) ?
                    currentUser.getPhotoUrl().toString() : null;

            WorkMate workMate1 = new WorkMate(uid, name, email, urlPicture);
            workmatesRepository.createWorkmate(workMate1)
                    .addOnSuccessListener(result -> workmatesRepository.updateCurrentUser(workMate1))
                    .addOnFailureListener(error -> Log.e("tagii", "error create user: " + error));
        } else {
            Log.e("tagii", "Current user is null in createUserInFirestore");
        }
    }





    private FirebaseUser getCurrentUser() {
        return FirebaseAuth.getInstance().getCurrentUser();
    }

    public boolean isCurrentUserLogged() {
        return getCurrentUser() != null;
    }

}
