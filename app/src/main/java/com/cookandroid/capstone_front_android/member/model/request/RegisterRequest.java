package com.cookandroid.capstone_front_android.member.model.request;

import com.google.gson.annotations.SerializedName;

public class RegisterRequest {
    private String userId;
    private String password;
    private String passwordCheck;
    private String name;
    private String email;
    private String phoneNumber;
    private String nickname;

    public RegisterRequest(String userId, String password, String passwordCheck, String name, String email, String phoneNumber, String nickname) {
        this.userId = userId;
        this.password = password;
        this.passwordCheck = passwordCheck;
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.nickname = nickname;
    }

    public String getUserId() {
        return userId;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getNickname() {
        return nickname;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }
}
