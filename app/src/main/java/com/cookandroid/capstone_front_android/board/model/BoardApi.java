package com.cookandroid.capstone_front_android.board.model;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface BoardApi {
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
