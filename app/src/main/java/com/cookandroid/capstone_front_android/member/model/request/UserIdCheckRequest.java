package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;

public class UserIdCheckRequest {
    @SerializedName("userId")
    String userId;

    public UserIdCheckRequest(String userId) {
        this.userId = userId;

    }
}