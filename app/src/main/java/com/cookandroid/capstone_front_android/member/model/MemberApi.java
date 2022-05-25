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
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MemberApi {

    @GET("/users/{memberId}")
    Call<MemberResponse> getMember(@Path("memberId") Long memberId);

    @POST("/users/new")
    Call<MemberResponse> postRegister(@Body RegisterRequest data);

    @POST("/users/login")
    Call<MemberResponse> postLogin(@Body LoginRequest data);

    @GET("/users/nickname-check/{nickname}")
    Call<BooleanDTO> getNicknameCheck(@Path("nickname") String nickname);

    @PUT("/users/password")
    Call<MemberResponse> putUpdatePassword(@Body ChangePasswordRequest data);


    @DELETE("/users")
    Call<MemberResponse> deleteMember(@Body DeleteMemberRequest data);

    @POST("/users/password")
    Call<MemberResponse> postFindPassword(@Body FindPasswordRequest data);

    @GET("/users/id-check/{userId}")
    Call<BooleanDTO> getUserIdCheck(@Path("userId") String userId);


    @POST("/users/user-id") //
    Call<MemberResponse> postFindUserId(@Body FindIdRequest data);

    @PUT("/users/nickname/{newNickname}")
    Call<MemberResponse> putUpdateNickname(@Path("newNickname") String newNickname);

}
