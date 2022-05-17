package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;

public class DeleteMemberRequest {
    @SerializedName("password")
    String password;

    @SerializedName("passwordCheck")
    String passwordCheck;

    public DeleteMemberRequest(String password, String passwordCheck) {
        this.password = password;
        this.passwordCheck = passwordCheck;
    }
}
