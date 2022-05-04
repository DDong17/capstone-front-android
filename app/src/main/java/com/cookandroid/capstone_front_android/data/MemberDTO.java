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

    public Long getid() {
        return id;
    }

    public String getuserId() {
        return userId;
    }

    public String getemail() {
        return email;
    }

    public String getname() {
        return name;
    }

    public String getpassword() {
        return password;
    }

    public String getphoneNumber() {
        return phoneNumber;
    }




}
