package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;


public class NicknameCheckRequest {
    @SerializedName("nickname")
    String nickname;


    public NicknameCheckRequest(String nickname) {
        this.nickname = nickname;
    }
}