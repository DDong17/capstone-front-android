package com.cookandroid.capstone_front_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

//import com.cookandroid.capstone_front_android.board.view.BoardAllFragment;
import com.cookandroid.capstone_front_android.board.view.BoardAllFragment;
import com.cookandroid.capstone_front_android.board.view.BoardReviewFragment;
import com.cookandroid.capstone_front_android.board.view.BoardTest;
import com.cookandroid.capstone_front_android.board.view.BoardTogetherFragment;
import com.cookandroid.capstone_front_android.board.view.BoardWriteFragment;
import com.cookandroid.capstone_front_android.categorymenu.*;

import com.cookandroid.capstone_front_android.location.model.LocationResponse;
import com.cookandroid.capstone_front_android.member.model.MemberApi;
import com.cookandroid.capstone_front_android.message.view.MessageFragment;
import com.cookandroid.capstone_front_android.message.view.MessageWriteFragment;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
import com.cookandroid.capstone_front_android.profile.view.MyInfoFragment;
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
    private LocationList locationList;
    private LocationInfo locationInfo;

    // 두번째 메뉴(커뮤니티).
    private BoardAllFragment communityAll; // 전체 보기.
    private BoardTogetherFragment communityTogether; // 같이 갈 사람.
    private BoardReviewFragment communityReview; // 리뷰.
    private BoardWriteFragment communityWrite; // 글쓰기.

    // 세번째 메뉴(지도).
    private Jmap jmap;

    // 네번째 메뉴(메시지).
//    private MessageReadId messageReadId;
    private MessageWriteFragment messageWrite;
    private MessageFragment messageReadReceived;
//    private MessageReadSent messageReadSent;

    // 다섯번째 메뉴(내정보).
    private MyInfoFragment myinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setSelectedItemId(R.id.actionStayCurrentPortrait);
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
                        setMessage(2);
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
        locationList = new LocationList();
        locationInfo = new LocationInfo();

        // 커뮤니티 객체생성.
        communityAll =new BoardAllFragment();
        communityTogether = new BoardTogetherFragment();
        communityReview = new BoardReviewFragment();
        communityWrite = new BoardWriteFragment();

        // 지도 객체생성.
        jmap =new Jmap();

        // 메시지 객체생성.
        messageWrite = new MessageWriteFragment();
//        messageReadId =new MessageReadId();
        messageReadReceived = new MessageFragment();
//        messageReadSent = new MessageReadSent();

        // 내정보 객체생성.
        myinfo =new MyInfoFragment();

        setMap(); // 지도가 첫 화면.
    }

    // 카테고리 화면이동
    public void setCategory(int categoryCode) {

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        // 카테고리 메인 메뉴
        if(categoryCode == 0) {
            ft.replace(R.id.mainFrame, category);
        // 지역별
        } else if((categoryCode & 0x00001000) == 0x00001000) {
            categoryCode &= ~(0x00001000);
            if(categoryCode == 99) return;
            ft.replace(R.id.mainFrame, locationList.setCategory(1, categoryCode));
        // 종류별
        } else if((categoryCode & 0x00002000) == 0x00002000) {
            categoryCode &= ~(0x00002000);
            if(categoryCode == 99) return;
            ft.replace(R.id.mainFrame, locationList.setCategory(2, categoryCode));
        } else return;

        ft.commit();

    }

    // 문화생활 상세정보
    public void setLocation(LocationResponse location, int categoryType, int categoryCode) {

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ft.replace(R.id.mainFrame, locationInfo.setLocation(location).setCategory(categoryType, categoryCode));

        ft.commit();

    }

    // 게시물 상세보기
    public void setBoardDetail(String title, String content) {

        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();

        ft.replace(R.id.mainFrame, new BoardTest().setTitle(title).setContent(content));

        ft.commit();
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
//            case 0:
//                ft.replace(R.id.mainFrame, messageReadId);
//                ft.commit();
//                break;
            case 1:
                ft.replace(R.id.mainFrame, messageWrite);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.mainFrame, messageReadReceived);
                ft.commit();
                break;
//            case 3:
//                ft.replace(R.id.mainFrame, messageReadSent);
//                ft.commit();
//                break;
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