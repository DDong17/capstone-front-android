package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;



public class LoginRequest {
    @SerializedName("userId")
    String userId;

    @SerializedName("password")
    String password;

    public LoginRequest(String userId, String password) {
        this.userId = userId;
        this.password = password;
    }
}
