package com.cookandroid.capstone_front_android.network;

import com.cookandroid.capstone_front_android.data.BooleanDto;
import com.cookandroid.capstone_front_android.member.model.request.FindPasswordRequest;
import com.cookandroid.capstone_front_android.member.model.request.JoinRequest;
import com.cookandroid.capstone_front_android.member.model.request.LoginRequest;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.member.model.request.PasswordModifyRequest;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceApi {


    String BASE_URL = "http://192.168.25.30:8080/";
    @POST("/users/new")
    Call<MemberResponse> userJoin(@Body JoinRequest data);

    @POST("/users/login")
    Call<MemberResponse> userLogin(@Body LoginRequest data);

    @GET("/users/nickname-check/{nickname}") //닉네임 중복
    Call<BooleanDto> Check_nickname(@Path("nickname") String nickname);

    @PUT("/users/password") //비번 변경
    Call<MemberResponse> passwordModify(@Body PasswordModifyRequest data);

//    @DELETE("/users") //DELETE는 Body 사용 x
//    Call<DeleteResponse> userDelete(@Body DeleteData data);

    @POST("/users/password") //비번 찾기
    Call<MemberResponse> findPassword(@Body FindPasswordRequest data);

    @GET("/users/id-check/{userId}") //아이디 중복
    Call<BooleanDto> Check_ID(@Path("userId") String userId);





}