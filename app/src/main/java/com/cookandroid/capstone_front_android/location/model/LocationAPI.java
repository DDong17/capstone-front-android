package com.cookandroid.capstone_front_android.location.model;

import com.cookandroid.capstone_front_android.util.model.BooleanDTO;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface LocationAPI {

    // 위치 기반으로 검색(파라미터는 무조건 소수 형식으로)
    @GET("/locations/position/{X}/{Y}")
    Call<LocationListResponse> findAllByPostition(@Path("X") double X, @Path("Y") double Y);

    // 지역 기반 조회
    @GET("/locations/area/{areaCode}")
    Call<LocationListResponse> findAllByArea(@Path("areaCode") int area);

    // 문화 기반 조회
    @GET("/locations/content/{contentTypeId}")
    Call<LocationListResponse> findAllByContent(@Path("contentTypeId") int content);

}
