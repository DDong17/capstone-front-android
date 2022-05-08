package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;

public class JoinRequest {
    @SerializedName("userId")
    private String userId;

    @SerializedName("password")
    private String password;

    @SerializedName("passwordCheck")
    private String passwordCheck;

    @SerializedName("name")
    private String name;

    @SerializedName("email")
    private String email;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    @SerializedName("nickname")
    private String nickname;




    public JoinRequest(String userId, String password, String passwordCheck, String name, String email, String phoneNumber, String nickname) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck=passwordCheck;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
    }



}
