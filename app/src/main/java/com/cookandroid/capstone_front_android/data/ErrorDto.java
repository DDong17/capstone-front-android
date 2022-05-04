package com.cookandroid.capstone_front_android.data;

import com.google.gson.annotations.SerializedName;

public class ErrorDto {

    @SerializedName("code")
    private String code;

    @SerializedName("message")
    private String message;

    public String getcode() {
        return code;
    }

    public String getmessage() {
        return message;
    }

}
