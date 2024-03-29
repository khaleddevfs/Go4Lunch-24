package com.example.go4lunch24.models.distance;

import com.google.gson.annotations.SerializedName;

public class Elements {
    private final String TAG = Elements.class.getSimpleName();

    @SerializedName("distance")
    private Distance distance;
    @SerializedName("status")
    private String status;

    public Distance getDistance() {
        return distance;
    }

    public void setDistance(Distance distance) {
        this.distance = distance;
    }


    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
