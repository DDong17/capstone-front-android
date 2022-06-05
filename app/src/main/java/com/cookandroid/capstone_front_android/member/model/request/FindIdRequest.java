package com.cookandroid.capstone_front_android.member.model.request;

public class FindIdRequest {

    private String email;

    public FindIdRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
