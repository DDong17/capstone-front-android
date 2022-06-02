package com.cookandroid.capstone_front_android.message.model;

import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;

import java.time.LocalDateTime;

public class MessageResponse {
    private Long messageId;
    private MemberResponse sender;
    private MemberResponse receiver;
    private String title;
    private String content;
    private String createdTime;

    public MessageResponse(Long messageId, MemberResponse sender, MemberResponse receiver, String title, String content, String createdTime) {
        this.messageId = messageId;
        this.sender = sender;
        this.receiver = receiver;
        this.title = title;
        this.content = content;
        this.createdTime = createdTime;
    }

    public Long getMessageId() {
        return messageId;
    }

    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public MemberResponse getSender() {
        return sender;
    }

    public void setSender(MemberResponse sender) {
        this.sender = sender;
    }

    public MemberResponse getReceiver() {
        return receiver;
    }

    public void setReceiver(MemberResponse receiver) {
        this.receiver = receiver;
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

    public String getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(String createdTime) {
        this.createdTime = createdTime;
    }
}
