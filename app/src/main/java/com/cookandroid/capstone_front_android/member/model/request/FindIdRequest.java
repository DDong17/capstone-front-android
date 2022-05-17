package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;

public class FindIdRequest {
    @SerializedName("email")
    String email;

    @SerializedName("name")
    String name;

    public FindIdRequest(String email, String name) {
        this.email = email;
        this.name = name;
    }
}
