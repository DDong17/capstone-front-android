package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;

public class FindPasswordRequest {
    @SerializedName("userId")
    String userId;

    @SerializedName("email")
    String email;

    public FindPasswordRequest(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }
}
