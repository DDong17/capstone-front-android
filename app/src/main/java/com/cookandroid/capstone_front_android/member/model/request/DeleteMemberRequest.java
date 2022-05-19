package com.cookandroid.capstone_front_android.member.model.request;

public class DeleteMemberRequest {

    private String password;
    private String passwordCheck;

    public DeleteMemberRequest(String password, String passwordCheck) {
        this.password = password;
        this.passwordCheck = passwordCheck;
    }

    public String getPassword() {
        return password;
    }

    public String getPasswordCheck() {
        return passwordCheck;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPasswordCheck(String passwordCheck) {
        this.passwordCheck = passwordCheck;
    }
}
