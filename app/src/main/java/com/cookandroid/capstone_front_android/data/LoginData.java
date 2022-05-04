package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;



public class LoginData {
    @SerializedName("userId")
    String userId;

    @SerializedName("password")
    String password;

    public LoginData(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
