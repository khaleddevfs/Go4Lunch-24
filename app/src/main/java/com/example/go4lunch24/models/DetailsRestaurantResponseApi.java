package com.example.go4lunch24.models;

import com.google.gson.annotations.SerializedName;

public class DetailsRestaurantResponseApi {

    @SerializedName("result")
    private RestaurantApi results;


    @SerializedName("status")
    private String status;

    public RestaurantApi getResults() {
        return results;
    }

    public void setResults(RestaurantApi results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
