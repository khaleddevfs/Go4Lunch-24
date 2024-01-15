package com.example.go4lunch24.models;

import com.google.gson.annotations.SerializedName;

public class DetailsRestaurantResponseApi {

    @SerializedName("result")
    private RestaurantApi result;


    @SerializedName("status")
    private String status;

    public RestaurantApi getResult() {
        return result;
    }

    public void setResult(RestaurantApi result) {
        this.result = result;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
