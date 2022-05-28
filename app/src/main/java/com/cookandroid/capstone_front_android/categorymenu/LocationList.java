package com.cookandroid.capstone_front_android.categorymenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.location.model.LocationAPI;
import com.cookandroid.capstone_front_android.location.model.LocationListResponse;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LocationList extends Fragment {

    private LocationAPI locationAPI;                // 서버 통신을 위한 레트로핏 클라이언트
    private View view;                              //
    private RecyclerView recyclerView;              // 리사이클러 뷰 객체
    private LocationAdapter locationAdapter;        // 리사이클러 뷰에서 사용할 문화 생활 정보 어댑터
    private int categoryCode = 1;                   // 카테고리 코드
    private int categoryType = 1;                   // 카테고리 종류(1: 지역, 2: 종류)

    public LocationList setCategory(int type, int code) {
        this.categoryType = type;
        this.categoryCode = code;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.location_list, container, false);

        // 레트로핏 클라이언트 가져오기
        locationAPI = RetrofitClient.getClient(LocationAPI.class);

        recyclerView = view.findViewById(R.id.list);



        switch(categoryType) {
            case 1: // 지역으로 조회
                locationAPI.findAllByArea(categoryCode).enqueue(new Callback<LocationListResponse>() {
                    @Override
                    public void onResponse(Call<LocationListResponse> call, Response<LocationListResponse> response) {

                        LocationListResponse r = response.body();
                        ;
                        if (r == null) {
                            Log.e("tag", "정보가 없습니다.");
                            Toast.makeText(getActivity(), "현재 위치에 문화 생활 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Log.e("tag", "정보 가져옴");

                        locationAdapter = new LocationAdapter(r.getLocations(), LocationList.this);
                        recyclerView.setAdapter(locationAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation()));

                    }

                    @Override
                    public void onFailure(Call<LocationListResponse> call, Throwable t) {

                        Log.e("정보 받아오기 에러 발생", t.getMessage());
                        Toast.makeText(getActivity(), "정보 받아오기 에러 발생", Toast.LENGTH_SHORT).show();

                    }
                });
                break;
            case 2: // 문화 종류로 조회
                locationAPI.findAllByContent(categoryCode).enqueue(new Callback<LocationListResponse>() {
                    @Override
                    public void onResponse(Call<LocationListResponse> call, Response<LocationListResponse> response) {

                        LocationListResponse r = response.body();
                        ;
                        if (r == null) {
                            Log.e("tag", "정보가 없습니다.");
                            Toast.makeText(getActivity(), "현재 위치에 문화 생활 정보가 없습니다.", Toast.LENGTH_SHORT).show();
                            return;
                        }

                        Log.e("tag", "정보 가져옴");

                        locationAdapter = new LocationAdapter(r.getLocations(), LocationList.this);
                        recyclerView.setAdapter(locationAdapter);
                        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation()));

                    }

                    @Override
                    public void onFailure(Call<LocationListResponse> call, Throwable t) {

                        Log.e("정보 받아오기 에러 발생", t.getMessage());
                        Toast.makeText(getActivity(), "정보 받아오기 에러 발생", Toast.LENGTH_SHORT).show();

                    }
                });
                break;
            default:
        }

        return view;
    }

}
