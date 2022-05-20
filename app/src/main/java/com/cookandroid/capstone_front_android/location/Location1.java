package com.cookandroid.capstone_front_android.location;

import android.util.Log;
import android.widget.Toast;

import com.cookandroid.capstone_front_android.location.model.LocationAPI;
import com.cookandroid.capstone_front_android.location.model.LocationListResponse;
import com.cookandroid.capstone_front_android.location.model.LocationResponse;
import com.cookandroid.capstone_front_android.member.view.LoginActivity;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Location1 {

    // 테스트용으로 임시 좌표
    public static final double SEOUL_CITY_HALL_X = 126.9779;
    public static final double SEOUL_CITY_HALL_Y = 37.5663;
    public static final double SUWON_HWASEONG_X = 127.0119;
    public static final double SUWON_HWASEONG_Y = 37.2871;
    public static final double YONGIN_CITY_HALL_X = 127.1779;
    public static final double YONGIN_CITY_HALL_Y = 37.2410;
    public static final double MJU_NS_X = 127.187559;
    public static final double MJU_NS_Y = 37.224158;
    // =====================================


    public void setMarkerByPosition(GoogleMap map, double x, double y) {

        LocationAPI locationAPI = RetrofitClient.getClient(LocationAPI.class);

        locationAPI.findAllByPostition(x, y).enqueue(new Callback<LocationListResponse>() {
            @Override
            public void onResponse(Call<LocationListResponse> call, Response<LocationListResponse> response) {

                LocationListResponse r = response.body();

                List<LocationResponse> l = r.getLocations();

                Log.e("Location1", "정보 가져옴");

                for(LocationResponse i : l) {
                    Log.e("Location1", "정보 추가:" + i.getTitle());
                    map.addMarker(new MarkerOptions().
                            position(new LatLng(i.getMapY(), i.getMapX())).
                            title(i.getTitle()).
                            snippet(i.getAddress()).
                            icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_ORANGE)));
                }

                Log.e("Location1", "정보 추가완료");

            }

            @Override
            public void onFailure(Call<LocationListResponse> call, Throwable t) {

                Log.e("정보 받아오기 에러 발생", t.getMessage());

            }
        });

    }


}
