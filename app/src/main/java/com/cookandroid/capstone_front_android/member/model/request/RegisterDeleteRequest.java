package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;
public class RegisterDeleteRequest {



    @SerializedName("password")
    String password;

    @SerializedName("passwordCheck")
    String passwordCheck;

    public RegisterDeleteRequest(String password, String passwordCheck) {
        this.password = password;
        this.passwordCheck = passwordCheck;

    }

}
