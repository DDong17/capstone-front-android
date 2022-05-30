package com.cookandroid.capstone_front_android.board.model;

import com.cookandroid.capstone_front_android.board.model.BoardResponse;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BoardListResponse {

    @SerializedName("boards")
    public List<BoardResponse> boards;

}
