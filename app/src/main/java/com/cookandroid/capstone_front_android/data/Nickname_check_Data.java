package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;


public class Nickname_check_Data {
    @SerializedName("nickname")
    String nickname;


    public Nickname_check_Data(String nickname) {
        this.nickname = nickname;
    }
}