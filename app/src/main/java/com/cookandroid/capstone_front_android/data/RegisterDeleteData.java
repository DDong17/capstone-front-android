package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;
public class RegisterDeleteData {



    @SerializedName("password")
    String password;

    @SerializedName("passwordCheck")
    String passwordCheck;

    public RegisterDeleteData(String password, String passwordCheck) {
        this.password = password;
        this.passwordCheck = passwordCheck;

    }

}
