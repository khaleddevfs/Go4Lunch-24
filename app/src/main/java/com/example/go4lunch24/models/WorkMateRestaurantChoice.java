package com.example.go4lunch24.models;

import com.google.firebase.Timestamp;
import com.google.gson.annotations.SerializedName;

public class WorkMateRestaurantChoice {

    @SerializedName("restaurantId")
    private String restaurantId;

    @SerializedName("restaurantName")
    private String restaurantName;

    @SerializedName("restaurantAddress")
    private String restaurantAddress;

    @SerializedName("restaurantDateChoice")
    private Timestamp restaurantDateChoice;

    public WorkMateRestaurantChoice() {
    }

    public WorkMateRestaurantChoice(String restaurantId, String restaurantName, String restaurantAddress, Timestamp restaurantDateChoice) {
        this.restaurantId = restaurantId;
        this.restaurantName = restaurantName;
        this.restaurantAddress = restaurantAddress;
        this.restaurantDateChoice = restaurantDateChoice;
    }

    public String getRestaurantId() {
        return restaurantId;
    }

    public void setRestaurantId(String restaurantId) {
        this.restaurantId = restaurantId;
    }

    public String getRestaurantName() {
        return restaurantName;
    }

    public void setRestaurantName(String restaurantName) {
        this.restaurantName = restaurantName;
    }

    public String getRestaurantAddress() {
        return restaurantAddress;
    }

    public void setRestaurantAddress(String restaurantAddress) {
        this.restaurantAddress = restaurantAddress;
    }

    public Timestamp getRestaurantDateChoice() {
        return restaurantDateChoice;
    }

    public void setRestaurantDateChoice(Timestamp restaurantDateChoice) {
        this.restaurantDateChoice = restaurantDateChoice;
    }
}
