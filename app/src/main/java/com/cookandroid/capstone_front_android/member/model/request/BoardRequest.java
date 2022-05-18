package com.cookandroid.capstone_front_android.member.model.request;

import android.os.Build;

import androidx.annotation.RequiresApi;

import com.google.gson.annotations.SerializedName;

public class BoardRequest {

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    @RequiresApi(api = Build.VERSION_CODES.O)
    public BoardRequest(String title, String content){
        this.title = title;
        this.content = content;
    }

}
