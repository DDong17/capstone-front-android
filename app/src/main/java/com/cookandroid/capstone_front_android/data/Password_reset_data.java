package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;

public class Password_reset_data {

    @SerializedName("userId")
    String userId;

    @SerializedName("email")
    String email;

    public Password_reset_data(String userId, String email) {
        this.userId = userId;
        this.email = email;

    }

}
