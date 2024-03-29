package com.example.go4lunch24.models;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class WorkMate {
    @SerializedName("uid")
    private String uid;
    @SerializedName("name")
    private String name;
    @SerializedName("email")

    private String email;
    @SerializedName("photoUrl")

    private String photoUrl;
    @SerializedName("workMateRestaurantChoice")

    private WorkMateRestaurantChoice workMateRestaurantChoice;

    private List<String> likedRestaurants;

    public WorkMate(){

    }


    public WorkMate(String uid, String name, @Nullable String email, @Nullable String photoUrl) {
        this.uid = uid;
        this.name = name;
        this.email = email;
        this.photoUrl = photoUrl;

    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Nullable
    public String getEmail() {
        return email;
    }

    public void setEmail(@Nullable String email) {
        this.email = email;
    }

    @Nullable
    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(@Nullable String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Nullable
    public WorkMateRestaurantChoice getWorkMateRestaurantChoice() {
        return workMateRestaurantChoice;
    }

    public void setWorkMateRestaurantChoice(@Nullable WorkMateRestaurantChoice workMateRestaurantChoice) {
        this.workMateRestaurantChoice = workMateRestaurantChoice;
    }

    @Nullable
    public List<String> getLikedRestaurants() {
        return likedRestaurants;
    }

    public void setLikedRestaurants(@Nullable List<String> likedRestaurants) {
        this.likedRestaurants = likedRestaurants;
    }


    @NonNull
    @Override
    public String toString() {
        return "WorkMate{" +
                "uid='" + uid + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                '}';
    }


    public void addLikedRestaurant(String restaurantUid) {
        if (likedRestaurants == null) {
            this.likedRestaurants = new ArrayList<>();
        }
        this.likedRestaurants.add(restaurantUid);
    }



    public void removeLikedRestaurant(String restaurantUid) {
        if (likedRestaurants != null) {
            List<String> likeToRemove = new ArrayList<String>();
            for (String like : likedRestaurants) {
                if (like.equals(restaurantUid)) {
                    likeToRemove.add(like);
                }
            }
            likedRestaurants.removeAll(likeToRemove);
        }
    }



}
