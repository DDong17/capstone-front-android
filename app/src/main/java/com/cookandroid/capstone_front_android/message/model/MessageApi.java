package com.cookandroid.capstone_front_android.message.model;

import com.cookandroid.capstone_front_android.board.model.BoardListResponse;
import com.cookandroid.capstone_front_android.board.model.BoardRequest;
import com.cookandroid.capstone_front_android.board.model.BoardResponse;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface MessageApi {

    // 메시지 등록.
    @POST("/messages")
    Call<MessageResponse> messageWrite(@Body MessageRequest data);

    // 메시지 조회(id).
    @GET("/messages/{messageId}")
    Call<MessageListResponse> getMessageId(@Path("messageId") Long id);

    // 메시지 삭제(id).
    @DELETE("/messages/{messageId}")
    Call<Void> deleteMessageId(@Path("messageId") Long id);

    // 메시지 조회(받은 메시지).
    @GET("/messages/received")
    Call<MessageListResponse> getMessageReceived();

    // 메시지 삭제(받은 메시지).
    @DELETE("/messages/received")
    Call<Void> deleteMessageReceived();

    // 메시지 조회(보낸 메시지).
    @GET("/messages/sent")
    Call<MessageListResponse> getMessageSent();

    // 메시지 삭제(보낸 메시지).
    @DELETE("/messages/sent")
    Call<Void> deleteMessageSent();

    // 받는 사람 조회
    @GET("/users/{userId}")
    Call<MemberResponse> getReceiver(@Path("userId") String userId);
}
