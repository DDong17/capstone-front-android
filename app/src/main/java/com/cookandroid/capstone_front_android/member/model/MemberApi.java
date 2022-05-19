package com.cookandroid.capstone_front_android.member.model;

import com.cookandroid.capstone_front_android.util.model.BooleanDTO;
import com.cookandroid.capstone_front_android.member.model.request.ChangePasswordRequest;
import com.cookandroid.capstone_front_android.member.model.request.DeleteMemberRequest;
import com.cookandroid.capstone_front_android.member.model.request.FindIdRequest;
import com.cookandroid.capstone_front_android.member.model.request.FindPasswordRequest;
import com.cookandroid.capstone_front_android.member.model.request.RegisterRequest;
import com.cookandroid.capstone_front_android.member.model.request.LoginRequest;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MemberApi {

    @POST("/users/new")
    Call<MemberResponse> userJoin(@Body RegisterRequest data);

    @POST("/users/login")
    Call<MemberResponse> userLogin(@Body LoginRequest data);

    @GET("/users/nickname-check/{nickname}")
        //닉네임 중복
    Call<BooleanDTO> checkNickname(@Path("nickname") String nickname);

    @PUT("/users/password")
        //비번 변경
    Call<MemberResponse> changePassword(@Body ChangePasswordRequest data);


    @HTTP(method = "DELETE",path = "/users", hasBody = true)
        //회원 탈퇴
    Call<MemberResponse> deleteMember(@Body DeleteMemberRequest data);

    @POST("/users/password")
        //비번 찾기
    Call<MemberResponse> findPassword(@Body FindPasswordRequest data);

    @GET("/users/id-check/{userId}")
        //아이디 중복
    Call<BooleanDTO> checkID(@Path("userId") String userId);


    @POST("/users/user-id") //
       //아이디 찾기
    Call<MemberResponse> findId(@Body FindIdRequest data);

    @PUT("/users/nickname/{newNickname}")
        //닉네임변경
    Call<MemberResponse>registerNewNickname(@Path("newNickname") String newNickname);

}
