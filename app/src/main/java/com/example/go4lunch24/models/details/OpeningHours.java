package com.example.go4lunch24.models.details;

import com.google.gson.annotations.SerializedName;

public class OpeningHours {
    @SerializedName("open_now")
    private boolean openNow;

    public boolean getOpenNow() {
        return openNow;
    }

}
