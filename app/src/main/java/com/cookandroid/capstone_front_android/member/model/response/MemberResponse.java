package com.cookandroid.capstone_front_android.member.model.response;


public class MemberResponse {

    private Long memberId;
    private String userId;
    private String email;
    private String name;
    private String password;
    private String phoneNumber;
    private String sessionId;

    public MemberResponse(Long memberId, String userId, String email, String name, String password, String phoneNumber, String sessionId) {
        this.memberId = memberId;
        this.userId = userId;
        this.email = email;
        this.name = name;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.sessionId = sessionId;
    }

    public Long getMemberId() {
        return memberId;
    }

    public String getUserId() {
        return userId;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getSessionId() {
        return sessionId;
    }

    public void setMemberId(Long memberId) {
        this.memberId = memberId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setSessionId(String sessionId) {
        this.sessionId = sessionId;
    }
}
