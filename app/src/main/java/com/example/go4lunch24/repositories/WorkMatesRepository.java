package com.example.go4lunch24.repositories;


import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.models.WorkMateRestaurantChoice;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.Tasks;
import com.google.firebase.Timestamp;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.List;


public class WorkMatesRepository {

    private static final String COLLECTION_NAME ="workMate";

    private final CollectionReference workMateCollection;

    private WorkMate workMate;

    private static volatile WorkMatesRepository INSTANCE;

    public static WorkMatesRepository getInstance() {
        if (INSTANCE == null){
            INSTANCE = new WorkMatesRepository();
        }
        return INSTANCE;
    }



    public WorkMatesRepository(){
        this.workMateCollection = getUsersCollection();
    }

    public WorkMate getActualUser(){
        return workMate;
    }

    private CollectionReference getUsersCollection() {
        return FirebaseFirestore.getInstance().collection(COLLECTION_NAME);
    }

    public Task<Void> createWorkmate(WorkMate workMate) {
        if (workMate != null) {
            this.workMate = workMate;
            return workMateCollection.document(workMate.getUid()).set(workMate);
        } else {
            // Gérer le cas où workMate est null.
            return Tasks.forException(new NullPointerException("WorkMate object is null."));
        }
    }


    public Task<DocumentSnapshot> getWorkMateFromFirebase(String uid) {
        return workMateCollection.document(uid).get();
    }

    public Task<QuerySnapshot> getAllWorkMate() {
        return workMateCollection.get();
    }

    public Task<Void> updateRestaurantPicked(String id, String name, String address, String userUid) {
        if (workMate != null) {
            WorkMateRestaurantChoice choice = new WorkMateRestaurantChoice(id, name, address, Timestamp.now());
            workMate.setWorkMateRestaurantChoice(choice);
            WorkMateRestaurantChoice choiceToCreate = (id != null) ? choice : null;
            return workMateCollection.document(userUid).update("workMateRestaurantChoice", choiceToCreate);
        } else {
            // Gérer le cas où workMate est null.
            return Tasks.forException(new NullPointerException("WorkMate object is null."));
        }
    }


    public Task<Void> addLikedRestaurant(String likedRestaurant) {
        if (workMate != null) {
            workMate.addLikedRestaurant(likedRestaurant);
            return updateLikedRestaurants(workMate.getUid());
        } else {
            // Gérer le cas où workMate est null.
            return Tasks.forException(new NullPointerException("WorkMate object is null."));
        }
    }

    public Task<Void> removeLikedRestaurant(String likedRestaurant){
        if (workMate != null) {
            workMate.removeLikedRestaurant(likedRestaurant);
            return updateLikedRestaurants(workMate.getUid());
        } else {
            // Gérer le cas où workMate est null.
            return Tasks.forException(new NullPointerException("WorkMate object is null."));
        }
    }

    private Task<Void> updateLikedRestaurants(String uid) {
        List<String> likedRestaurantList = workMate.getLikedRestaurants();
        return workMateCollection.document(uid).update("likedRestaurants", likedRestaurantList);
    }

    public void updateCurrentUser(WorkMate workMate){
        this.workMate = workMate;
    }


}
