package com.cookandroid.capstone_front_android.member.model.request;

public class FindPasswordRequest {

    private String userId;
    private String email;

    public FindPasswordRequest(String userId, String email) {
        this.userId = userId;
        this.email = email;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
