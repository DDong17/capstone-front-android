package com.cookandroid.capstone_front_android.member.model.response;

import android.location.Location;

import com.google.gson.annotations.SerializedName;

import org.w3c.dom.Comment;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.List;

public class BoardResponse {

    @SerializedName("boardId")
    private Long boardId;
    @SerializedName("writerNickName")
    private String writerNickName;
    @SerializedName("title")
    private String title;
    @SerializedName("content")
    private String content;
    @SerializedName("viewCount")
    private int viewCount;
    @SerializedName("locationTitle")
    private String locationTitle;
    @SerializedName("createdTime")
    private LocalDateTime createdTime;
    @SerializedName("comments")
//    private List<CommentResponse> comments;

    public Long getBoardId() {
        return boardId;
    }

    public String getWriterNickName() {
        return writerNickName;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public int getViewCount() {
        return viewCount;
    }

    public String getLocationTitle() {
        return locationTitle;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

//    public List<CommentResponse> getComments() {
//        return comments;
    }









