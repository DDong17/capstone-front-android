package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;

public class FindPasswordData {
    @SerializedName("userId")
    String userId;

    @SerializedName("email")
    String email;

    public FindPasswordData(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
