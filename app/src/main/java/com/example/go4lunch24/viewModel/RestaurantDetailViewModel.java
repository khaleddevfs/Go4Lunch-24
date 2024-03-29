package com.example.go4lunch24.viewModel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.go4lunch24.models.Restaurant;
import com.example.go4lunch24.models.WorkMate;
import com.example.go4lunch24.repositories.RestaurantRepository;
import com.example.go4lunch24.repositories.WorkMatesRepository;
import com.google.firebase.firestore.DocumentSnapshot;

import java.util.ArrayList;
import java.util.List;

public class RestaurantDetailViewModel extends BaseViewModel {

    private final String TAG = RestaurantDetailViewModel.class.getSimpleName();

    private final RestaurantRepository restaurantRepository;

    public Restaurant restaurant;


    public final MutableLiveData<List<WorkMate>> mWorkMateList = new MutableLiveData<>();

    public MutableLiveData<Boolean> isRestaurantLiked = new MutableLiveData<>();

    public MutableLiveData<Boolean> isRestaurantPicked = new MutableLiveData<>();

    public RestaurantDetailViewModel(RestaurantRepository restaurantRepository, WorkMatesRepository workmatesRepository) {
        this.restaurantRepository = restaurantRepository;
        this.workmatesRepository = workmatesRepository;

        workMate = workmatesRepository.getActualUser();
    }



    public LiveData<Restaurant> getRestaurantDetail(String placeId) {
        return restaurantRepository.getGoogleRestaurantDetail(placeId);
    }

    public void fetchInfoRestaurant(Restaurant restaurant){
        Log.d("like and pick", "fetch resto ok ");
        this.restaurant = restaurant;
        fetchUsersGoing();
        isRestaurantLiked.setValue(checkIfRestaurantIsLiked());
        if (workMate.getWorkMateRestaurantChoice() != null) {
            String uidSelection = workMate.getWorkMateRestaurantChoice().getRestaurantId();
            String restaurantId = restaurant.getRestaurantID();
            isRestaurantPicked.setValue(uidSelection.equals(restaurantId));
        } else {
            isRestaurantPicked.setValue(false);
        }

    }
    public void updateRestaurantLiked(Restaurant restaurant) {
        Log.d("like and pick", "update like ok ");
        if (isRestaurantLiked.getValue()) {
            isRestaurantLiked.setValue(false);
            workmatesRepository.removeLikedRestaurant(restaurant.getRestaurantID())
                    .addOnCompleteListener(result -> Log.i(TAG, "restaurant disliked"));
        } else {
            isRestaurantLiked.setValue(true);
            workmatesRepository.addLikedRestaurant(restaurant.getRestaurantID())
                    .addOnCompleteListener(result -> Log.i(TAG, "restaurant liked"));
        }
    }

    public void updateRestaurantPiked(Restaurant restaurant) {
        Log.d("like and pick", "fetch pick ok ");
        if (isRestaurantPicked.getValue()) {
            isRestaurantPicked.setValue(false);
            workmatesRepository.updateRestaurantPicked(null, null, null, workMate.getUid())
                    .addOnCompleteListener(result -> Log.i(TAG, "restaurant unselected"));
        } else {
            isRestaurantPicked.setValue(true);
            workmatesRepository.updateRestaurantPicked(restaurant.getRestaurantID(), restaurant.getName(),
                            restaurant.getAddress(), workMate.getUid())
                    .addOnCompleteListener(result -> Log.i(TAG, "restaurant selected"));
        }
        fetchUsersGoing();
    }


    public void fetchWorkMateLike(Restaurant restaurant){

        if (workMate != null && workMate.getLikedRestaurants() != null) {
            List<String> likedRestaurant = workMate.getLikedRestaurants();
            String restaurantUid = restaurant.getRestaurantID();
            if (likedRestaurant != null && restaurantUid != null && likedRestaurant.contains(restaurantUid)) {
                isRestaurantLiked.setValue(true);
                Log.d("like and pick", "fetch likes ok ");
            }
        }
    }

    private void fetchUsersGoing() {
        Log.d("like and pick", "fetch users going ok ");
        List<WorkMate> workMateToAdd = new ArrayList<>();
        workmatesRepository.getAllWorkMate()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (DocumentSnapshot documentSnapshot : queryDocumentSnapshots.getDocuments()) {
                        WorkMate workMate = documentSnapshot.toObject(WorkMate.class);
                        if (workMate != null && workMate.getWorkMateRestaurantChoice() != null && workMate.getWorkMateRestaurantChoice().getRestaurantId() != null) {
                            String restaurantUid = workMate.getWorkMateRestaurantChoice().getRestaurantId();
                            if (restaurantUid.equals(restaurant.getRestaurantID())) {
                                workMateToAdd.add(workMate);
                            }
                        }
                    }
                    restaurant.setWorkMatesGoingEating(workMateToAdd);
                    mWorkMateList.setValue(restaurant.getWorkMatesEatingHere());
                });
    }


    private Boolean checkIfRestaurantIsLiked() {
        List<String> restaurantLiked = workMate.getLikedRestaurants();
        if (restaurantLiked != null && restaurantLiked.size() > 0) {
            for (String uid : restaurantLiked) {
                if (uid.equals(restaurant.getRestaurantID())) return true;
                Log.d("like and pick", "check ok ");
            }
        }
        return false;
    }
}
