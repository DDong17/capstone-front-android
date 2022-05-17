package com.cookandroid.capstone_front_android.categoryMenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;

public class category extends Fragment {
    private View view;

    private MainActivity activity;

    // 카테고리 버튼.
    private Button btnbtnAll; // 전체.
    private Button btnShow; // 공연.
    private Button btnDisplay; // 전시.
    private Button btnConcert; // 콘서트.
    private Button btnMusical; // 뮤지컬.
    private Button btnMovie; // 영화.
    private Button btnFestival; // 축제.
    private Button btnContentEtc; // 기타(콘텐츠).
    private Button btnWhole; // 전국.
    private Button btnSeoul; // 서울.
    private Button btnGyeongIncheon; // 경기, 인천.
    private Button btnGangwon; // 강원도.
    private Button btnChung; // 충청도.
    private Button btnGyeong; // 경상도.
    private Button btnJeolla; // 전라도.
    private Button btnWholeEtc; // 기타(위치).

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.category,container,false);

        activity = (MainActivity) getActivity();

        // 버튼설정.
        btnbtnAll = view.findViewById(R.id.allContent);
        btnShow = view.findViewById(R.id.show);
        btnDisplay = view.findViewById(R.id.display);
        btnConcert = view.findViewById(R.id.concert);
        btnMusical = view.findViewById(R.id.musical);
        btnMovie = view.findViewById(R.id.movie);
        btnFestival = view.findViewById(R.id.festival);
        btnContentEtc = view.findViewById(R.id.content_etc);
        btnWhole = view.findViewById(R.id.whole);
        btnSeoul = view.findViewById(R.id.seoul);
        btnGyeongIncheon = view.findViewById(R.id.gyeongIncheon);
        btnGangwon = view.findViewById(R.id.gangwon);
        btnChung = view.findViewById(R.id.chung);
        btnGyeong = view.findViewById(R.id.gyeong);
        btnJeolla = view.findViewById(R.id.jeolla);
        btnWholeEtc = view.findViewById(R.id.wholeEtc);

        // 버튼이벤트.
        btnbtnAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(1);
            }
        });

        btnShow.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(2);
            }
        });

        btnDisplay.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(3);
            }
        });

        btnConcert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(4);
            }
        });

        btnMusical.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(5);
            }
        });

        btnMovie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(6);
            }
        });

        btnFestival.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(7);
            }
        });

        btnContentEtc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(8);
            }
        });

        btnWhole.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(9);
            }
        });

        btnSeoul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(10);
            }
        });

        btnGyeongIncheon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(11);
            }
        });

        btnGangwon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(12);
            }
        });

        btnChung.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(13);
            }
        });

        btnGyeong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(14);
            }
        });

        btnJeolla.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(15);
            }
        });

        btnWholeEtc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(16);
            }
        });

        return view;
    }

}
