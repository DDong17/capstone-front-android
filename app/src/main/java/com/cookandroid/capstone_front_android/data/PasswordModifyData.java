package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;

public class PasswordModifyData {

    @SerializedName("currentPassword")
     String currentPassword;

    @SerializedName("newPassword")
     String newPassword;

    @SerializedName("newPasswordCheck")
     String newPasswordCheck;

    public PasswordModifyData(String currentPassword, String newPassword, String newPasswordCheck) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newPasswordCheck = newPasswordCheck;
    }
}