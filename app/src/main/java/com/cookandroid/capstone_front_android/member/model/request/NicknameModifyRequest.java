package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;


public class NicknameModifyRequest {
    @SerializedName("nickname")
    String nickname;


    public NicknameModifyRequest(String nickname) {
        this.nickname = nickname;
    }
}