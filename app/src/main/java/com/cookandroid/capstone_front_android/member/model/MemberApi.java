package com.cookandroid.capstone_front_android.member.model;

import com.cookandroid.capstone_front_android.data.BooleanDTO;
import com.cookandroid.capstone_front_android.member.model.request.BoardRequest;
import com.cookandroid.capstone_front_android.member.model.request.ChangePasswordRequest;
import com.cookandroid.capstone_front_android.member.model.request.DeleteMemberRequest;
import com.cookandroid.capstone_front_android.member.model.request.FindIdRequest;
import com.cookandroid.capstone_front_android.member.model.request.FindPasswordRequest;
import com.cookandroid.capstone_front_android.member.model.request.JoinRequest;
import com.cookandroid.capstone_front_android.member.model.request.LoginRequest;
import com.cookandroid.capstone_front_android.member.model.response.BoardResponse;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;


import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.HTTP;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MemberApi {


//    String BASE_URL = "http://10.0.2.2:8080/";

    @POST("/users/new")
    Call<MemberResponse> userJoin(@Body JoinRequest data);

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

    @POST("/boards")
        //게시판 등록
    Call<BoardResponse> registerBoardWrite(@Body BoardRequest data);

    @GET("/boards")
        //게시판 조회
    Call<List<BoardResponse>> getBoard();

    @PUT("/boards/{boardId}")
        //게시판 수정
    Call<BoardResponse> putBoard(@Path("boardId") Long id, @Body BoardRequest data);

    @DELETE("/boards/{boardId}")
        //게시판 삭제
    Call<Void> deleteBoard(@Path("boardId") Long id);

}
