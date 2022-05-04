package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;

public class MemberDTO {




    @SerializedName("id")
    private Long id;

    @SerializedName("userId")
    private String userId;

    @SerializedName("email")
    private String email;

    @SerializedName("name")
    private String name;

    @SerializedName("password")
    private String password;

    @SerializedName("phoneNumber")
    private String phoneNumber;

    public Long getId() {
        return id;
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




}
