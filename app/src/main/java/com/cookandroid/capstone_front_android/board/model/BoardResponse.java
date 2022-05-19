package com.cookandroid.capstone_front_android.board.model;

import java.time.LocalDateTime;

public class BoardResponse {

    private Long boardId;
    private String writerNickName;
    private String title;
    private String content;
    private int viewCount;
    private String locationTitle;
    private LocalDateTime createdTime;

//    @SerializedName("comments")
//    private List<CommentResponse> comments;

//    public List<CommentResponse> getComments() {
//        return comments;


    public BoardResponse(Long boardId, String writerNickName, String title, String content, int viewCount, String locationTitle, LocalDateTime createdTime) {
        this.boardId = boardId;
        this.writerNickName = writerNickName;
        this.title = title;
        this.content = content;
        this.viewCount = viewCount;
        this.locationTitle = locationTitle;
        this.createdTime = createdTime;
    }

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

    public void setBoardId(Long boardId) {
        this.boardId = boardId;
    }

    public void setWriterNickName(String writerNickName) {
        this.writerNickName = writerNickName;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setViewCount(int viewCount) {
        this.viewCount = viewCount;
    }

    public void setLocationTitle(String locationTitle) {
        this.locationTitle = locationTitle;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }
}









