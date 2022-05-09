package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;

public class PasswordModifyRequest {

    @SerializedName("currentPassword")
     String currentPassword;

    @SerializedName("newPassword")
     String newPassword;

    @SerializedName("newPasswordCheck")
     String newPasswordCheck;

    public PasswordModifyRequest(String currentPassword, String newPassword, String newPasswordCheck) {
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.newPasswordCheck = newPasswordCheck;
    }
}