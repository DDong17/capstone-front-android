package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;

public class ID_check_Data {
    @SerializedName("userId")
    String userId;


    public ID_check_Data(String userId) {
        this.userId = userId;

    }
}