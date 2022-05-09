package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;

public class IdCheckData {
    @SerializedName("userId")
    String userId;


    public IdCheckData(String userId) {
        this.userId = userId;

    }
}