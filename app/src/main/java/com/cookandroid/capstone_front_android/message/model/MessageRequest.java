package com.cookandroid.capstone_front_android.message.model;

public class MessageRequest {

    private String receiverNickname;
    private String title;
    private String content;

    public MessageRequest(String receiverNickname, String title, String content) {
        this.receiverNickname = receiverNickname;
        this.title = title;
        this.content = content;
    }

    public String getReceiverNickname() {
        return receiverNickname;
    }

    public void setReceiverNickname(String receiverNickname) {
        this.receiverNickname = receiverNickname;
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
