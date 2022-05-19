package com.cookandroid.capstone_front_android.member.model.request;

public class NicknameModifyRequest {
    private String nickname;

    public NicknameModifyRequest(String nickname) {
        this.nickname = nickname;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}