package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;

public class Find_password_data {
    @SerializedName("userId")
    String userId;

    @SerializedName("email")
    String email;

    public Find_password_data(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
