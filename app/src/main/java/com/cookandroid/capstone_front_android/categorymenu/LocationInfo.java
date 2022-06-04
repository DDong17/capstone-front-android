package com.cookandroid.capstone_front_android.categorymenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.location.model.LocationResponse;

public class LocationInfo extends Fragment {

    private int categoryType = 0;           // 문화 생활 분류 종류
    private int categoryCode = 99;          // 문화 생활 분류 코드
    private LocationResponse location;      // 문화 생활 정보 객체
    private View view;                      //
    private ImageView backButton;              // 뒤로 가기 버튼
    private ImageView locImage;             // 문화 생활 이미지
    private TextView locTitle;              // 문화 생활 이름
    private TextView locationTitle;               // 문화 생활 정보
    private TextView locationAddress;
    private TextView locationArea;
    private TextView locationContent;

    public LocationInfo setLocation(LocationResponse location) {
        this.location = location;
        return this;
    }

    public LocationInfo setCategory(int type, int code) {
        this.categoryType = type;
        this.categoryCode = code;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.location_info, container, false);

        backButton = view.findViewById(R.id.button);
        locImage = view.findViewById(R.id.image);
        locTitle = view.findViewById(R.id.title);
        locationTitle  = view.findViewById(R.id.tv_locationTitle);
        locationAddress  = view.findViewById(R.id.tv_locationAddress);
        locationArea  = view.findViewById(R.id.tv_locationArea);
        locationContent  = view.findViewById(R.id.tv_locationContent);

        // 뒤로 가기 버튼 리스너
        backButton.setOnClickListener(v -> {
            if(categoryType == 1 && categoryCode != 99) { // 지역 분류로 돌아가기
                ((MainActivity) getActivity()).setCategory(categoryCode | 0x00001000);
            } else if(categoryType == 2 && categoryCode != 99) { // 문화 종류별 분류로 돌아가기
                ((MainActivity) getActivity()).setCategory(categoryCode | 0x00002000);
            } else { // 카테고리 메인 화면으로 돌아가기
                ((MainActivity) getActivity()).setCategory(0);
            }
        });

        // 문화생활 상세정보 표시
        locTitle.setText(location.getTitle());
        locationTitle.setText(location.getTitle());
        locationAddress.setText(location.getAddress());
        locationArea.setText(location.getAreaName());
        locationContent.setText(location.getContentName());
        if(location.getFirstImage() != null) Glide.with(this).load(location.getFirstImage()).into(locImage);

        return view;
    }

}
