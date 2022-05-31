package com.cookandroid.capstone_front_android.categorymenu;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.capstone_front_android.MainActivity;
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
    private Button backButton;                      // 뒤로 가기 버튼
    private TextView catTitle;                      // 분류 이름

    public LocationList setCategory(int type, int code) {
        this.categoryType = type;
        this.categoryCode = code;
        return this;
    }

    public int getCategoryCode() {
        return categoryCode;
    }

    public int getCategoryType() {
        return categoryType;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.location_list, container, false);

        backButton = view.findViewById(R.id.button);
        catTitle = view.findViewById(R.id.title);

        // 뒤로 가기 버튼 리스너
        backButton.setOnClickListener(v -> ((MainActivity) getActivity()).setCategory(0));

        // 카테고리 이름 설정
        catTitle.setText(getCategoryTitle());

        // 레트로핏 클라이언트 가져오기
        locationAPI = RetrofitClient.getClient(LocationAPI.class);

        recyclerView = view.findViewById(R.id.list);

        switch(categoryType) {
            case 1: // 지역으로 조회
                locationAPI.findAllByArea(categoryCode).enqueue(new Callback<LocationListResponse>() {
                    @Override
                    public void onResponse(Call<LocationListResponse> call, Response<LocationListResponse> response) {

                        LocationListResponse r = response.body();

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
                        //recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), new LinearLayoutManager(getContext()).getOrientation()));

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


    private String getCategoryTitle() {

        switch(categoryType) {
            case 1:
                switch(categoryCode) {
                    case 1:
                        return "서울";
                    case 31:
                        return "경기";
                    case 32:
                        return "강원";
                    case 33:
                        return "충청북도";
                    case 34:
                        return "충청남도";
                    case 35:
                        return "경상북도";
                    case 36:
                        return "경상남도";
                    case 37:
                        return "전라북도";
                    case 38:
                        return "전라남도";
                    case 39:
                        return "제주";
                    default:
                        return "지역별 문화 정보";
                }
            case 2:
                switch(categoryCode) {
                    case 12:
                        return "관광지";
                    case 14:
                        return "문화시설";
                    case 15:
                        return "여행코스";
                    case 25:
                        return "행사/공연/축제";
                    case 28:
                        return "레포츠";
                    case 32:
                        return "숙박";
                    case 38:
                        return "쇼핑";
                    case 39:
                        return "음식점";
                    default:
                        return "종류별 문화 정보";
                }
            default:
                return "문화 정보";
        }

    }

}
