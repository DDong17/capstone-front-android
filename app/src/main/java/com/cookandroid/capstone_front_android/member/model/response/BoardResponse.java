package com.cookandroid.capstone_front_android.member.model.response;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Comment;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.List;

public class BoardResponse {

    @SerializedName("title")
    private String title;

    @SerializedName("content")
    private String content;

    private Long id;

    public String getTitle(){
        return title;
    }

    public String getContent(){
        return content;
    }

    public Long getId(){ return id; }

}
