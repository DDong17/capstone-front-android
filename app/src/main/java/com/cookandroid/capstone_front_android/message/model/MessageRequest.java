package com.cookandroid.capstone_front_android.message.model;

public class MessageRequest {

    private String receiverUserId;
    private String title;
    private String content;

    public MessageRequest(String receiverUserId, String title, String content) {
        this.receiverUserId = receiverUserId;
        this.title = title;
        this.content = content;
    }

    public String getReceiverUserId() {
        return receiverUserId;
    }

    public void setReceiverUserId(String receiverUserId) {
        this.receiverUserId = receiverUserId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

}
