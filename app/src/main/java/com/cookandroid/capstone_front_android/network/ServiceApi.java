package com.cookandroid.capstone_front_android.network;

import com.cookandroid.capstone_front_android.data.BooleanDto;
import com.cookandroid.capstone_front_android.data.Find_password_data;
import com.cookandroid.capstone_front_android.data.JoinData;
import com.cookandroid.capstone_front_android.data.LoginData;
import com.cookandroid.capstone_front_android.data.MemberDTO;
import com.cookandroid.capstone_front_android.data.Nickname_check_Data;
import com.cookandroid.capstone_front_android.data.Password_modify_data;


import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface ServiceApi {


    String BASE_URL = "http://192.168.25.30:8080/";
    @POST("/users/new")
    Call<MemberDTO> userJoin(@Body JoinData data);

    @POST("/users/login")
    Call<MemberDTO> userLogin(@Body LoginData data);

    @GET("/users/nickname-check/{nickname}") //닉네임 중복
    Call<BooleanDto> Check_nickname(@Path("nickname") String nickname);

    @PUT("/users/password") //비번 변경
    Call<MemberDTO> passwordmodify(@Body Password_modify_data data);


//    @DELETE("/users") //DELETE는 Body 사용 x
//    Call<DeleteResponse> userDelete(@Body DeleteData data);

    @POST("/users/password") //비번 찾기
    Call<MemberDTO> findpassword(@Body Find_password_data data);







    @GET("/users/id-check/{userId}") //아이디 중복
    Call<BooleanDto> Check_ID(@Path("userId") String userId);





}