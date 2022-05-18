package com.cookandroid.capstone_front_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.cookandroid.capstone_front_android.categorymenu.*;
import com.cookandroid.capstone_front_android.communitymenu.*;

import com.cookandroid.capstone_front_android.member.model.MemberApi;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class, RetrofitClient.getSessionId());
    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;

    // 첫번째 메뉴(카테고리).
    private com.cookandroid.capstone_front_android.categorymenu.category category; // 카테고리 메뉴.
    private categoryAll categoryAll; // 전체.
    private categoryShow categoryShow; // 공연.
    private categoryDisplay categoryDisplay; // 전시.
    private categoryConcert categoryConcert; // 콘서트.
    private categoryMusical categoryMusical; // 뮤지컬.
    private categoryMovie categoryMovie; // 영화.
    private categoryFestival categoryFestival; // 축제.
    private categoryContentEtc categoryContentEtc; //기타(콘텐츠).
    private categoryWhole categoryWhole; // 전국.
    private categorySeoul categorySeoul; // 서울.
    private categoryGyeongIncheon categoryGyeongIncheon; // 경기,인천.
    private categoryGangwon categoryGangwon; // 강원도.
    private categoryChung categoryChung; // 충청도.
    private categoryGyeong categoryGyeong; // 경상도.
    private categoryJeolla categoryJeolla; // 전라도.
    private categoryWholeEtc categoryWholeEtc; // 기타(위치).

    // 두번째 메뉴(커뮤니티).
    private communityAll communityAll; // 전체 보기.
    private communityTogether communityTogether; // 같이 갈 사람.
    private communityReview communityReview; // 리뷰.
    private communityWrite communityWrite; // 글쓰기.

    // 세번째 메뉴(지도).
    private Jmap jmap;

    // 네번째 메뉴(메시지).
    private MessageRead messageRead;
    private MessageWrite messageWrite;

    // 다섯번째 메뉴(내정보).
    private MyInfo myinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.actionCategoty:
                        setCategory(0);
                        break;
                    case R.id.actionMenuBook:
                        setCommunity(0);
                        break;
                    case R.id.actionStayCurrentPortrait:
                        setMap();
                        break;
                    case R.id.actionEmail:
                        setMessage(0);
                        break;
                    case R.id.actionPerson:
                        setMyInfo(0);
                        break;
                }
                return true;
            }
        });

        // 카테고리 객체생성.
        category =new category();
        categoryAll = new categoryAll();
        categoryShow = new categoryShow();
        categoryDisplay = new categoryDisplay();
        categoryConcert = new categoryConcert();
        categoryMusical = new categoryMusical();
        categoryMovie = new categoryMovie();
        categoryFestival = new categoryFestival();
        categoryContentEtc = new categoryContentEtc();
        categoryWhole = new categoryWhole();
        categorySeoul = new categorySeoul();
        categoryGyeongIncheon = new categoryGyeongIncheon();
        categoryGangwon = new categoryGangwon();
        categoryChung = new categoryChung();
        categoryGyeong = new categoryGyeong();
        categoryJeolla = new categoryJeolla();
        categoryWholeEtc = new categoryWholeEtc();

        // 커뮤니티 객체생성.
        communityAll =new communityAll();
        communityTogether = new communityTogether();
        communityReview = new communityReview();
        communityWrite = new communityWrite();

        // 지도 객체생성.
        jmap =new Jmap();

        // 메시지 객체생성.
        messageWrite = new MessageWrite();
        messageRead =new MessageRead();

        // 내정보 객체생성.
        myinfo =new MyInfo();

        setMap(); // 지도가 첫 화면.
    }

    // 카테고리 화면이동(순서는 선언순서).
    public void setCategory(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.mainFrame, category);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.mainFrame, categoryAll);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.mainFrame, categoryShow);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.mainFrame, categoryDisplay);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.mainFrame, categoryConcert);
                ft.commit();
                break;
            case 5:
                ft.replace(R.id.mainFrame, categoryMusical);
                ft.commit();
                break;
            case 6:
                ft.replace(R.id.mainFrame, categoryMovie);
                ft.commit();
                break;
            case 7:
                ft.replace(R.id.mainFrame, categoryFestival);
                ft.commit();
                break;
            case 8:
                ft.replace(R.id.mainFrame, categoryContentEtc);
                ft.commit();
                break;
            case 9:
                ft.replace(R.id.mainFrame, categoryWhole);
                ft.commit();
                break;
            case 10:
                ft.replace(R.id.mainFrame, categorySeoul);
                ft.commit();
                break;
            case 11:
                ft.replace(R.id.mainFrame, categoryGyeongIncheon);
                ft.commit();
                break;
            case 12:
                ft.replace(R.id.mainFrame, categoryGangwon);
                ft.commit();
                break;
            case 13:
                ft.replace(R.id.mainFrame, categoryChung);
                ft.commit();
                break;
            case 14:
                ft.replace(R.id.mainFrame, categoryGyeong);
                ft.commit();
                break;
            case 15:
                ft.replace(R.id.mainFrame, categoryJeolla);
                ft.commit();
                break;
            case 16:
                ft.replace(R.id.mainFrame, categoryWholeEtc);
                ft.commit();
                break;
        }
    }

    // 커뮤니티 화면이동(순서는 선언순서).
    public void setCommunity(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.mainFrame, communityAll);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.mainFrame, communityTogether);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.mainFrame, communityReview);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.mainFrame, communityWrite);
                ft.commit();
                break;
        }
    }

    // 지도 화면이동.
    public void setMap() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.mainFrame, jmap);
        ft.commit();
    }

    // 메시지 화면이동(순서는 선언순서).
    public void setMessage(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.mainFrame, messageRead);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.mainFrame, messageWrite);
                ft.commit();
                break;
        }
    }

    // 내정보 화면이동(순서는 선언순서).
    public void setMyInfo(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.mainFrame, myinfo);
                ft.commit();
                break;
        }
    }
}