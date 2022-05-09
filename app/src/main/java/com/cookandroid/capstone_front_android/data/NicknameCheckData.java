package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;


public class NicknameCheckData {
    @SerializedName("nickname")
    String nickname;


    public NicknameCheckData(String nickname) {
        this.nickname = nickname;
    }
}