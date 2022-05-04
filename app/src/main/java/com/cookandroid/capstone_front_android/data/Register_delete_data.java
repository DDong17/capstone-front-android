package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;
public class Register_delete_data {



    @SerializedName("password")
    String password;

    @SerializedName("passwordCheck")
    String passwordCheck;

    public Register_delete_data(String password, String passwordCheck) {
        this.password = password;
        this.passwordCheck = passwordCheck;

    }

}
