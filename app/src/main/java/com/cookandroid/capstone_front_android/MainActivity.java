package com.cookandroid.capstone_front_android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import category_menu.*;
import community_menu.*;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {

    private BottomNavigationView bottomNavigationView;
    private FragmentManager fm;
    private FragmentTransaction ft;

    // 첫번째 메뉴(카테고리).
    private category_menu.category category; // 카테고리 메뉴.
    private category_menu.category_all category_all; // 전체.
    private category_menu.category_show category_show; // 공연.
    private category_menu.category_display category_display; // 전시.
    private category_menu.category_concert category_concert; // 콘서트.
    private category_menu.category_musical category_musical; // 뮤지컬.
    private category_menu.category_movie category_movie; // 영화.
    private category_menu.category_festival category_festival; // 축제.
    private category_menu.category_content_etc category_content_etc; //기타(콘텐츠).
    private category_menu.category_whole category_whole; // 전국.
    private category_menu.category_seoul category_seoul; // 서울.
    private category_menu.category_gyeong_incheon category_gyeong_incheon; // 경기,인천.
    private category_menu.category_gangwon category_gangwon; // 강원도.
    private category_menu.category_chung category_chung; // 충청도.
    private category_menu.category_gyeong category_gyeong; // 경상도.
    private category_menu.category_jeolla category_jeolla; // 전라도.
    private category_menu.category_whole_etc category_whole_etc; // 기타(위치).

    // 두번째 메뉴(커뮤니티).
    private community_menu.community_all community_all; // 전체 보기.
    private community_menu.community_together community_together; // 같이 갈 사람.
    private community_menu.community_review community_review; // 리뷰.
    private community_menu.community_write community_write; // 글쓰기.

    // 세번째 메뉴(지도).
    private jmap jmap;

    // 네번째 메뉴(메시지).
    private message_read message_read;
    private message_write message_write;

    // 다섯번째 메뉴(내정보).
    private my_info myinfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavi);
        bottomNavigationView.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_categoty:
                        setCategory(0);
                        break;
                    case R.id.action_menu_book:
                        setCommunity(0);
                        break;
                    case R.id.action_stay_current_portrait:
                        setMap();
                        break;
                    case R.id.action_email:
                        setMessage(0);
                        break;
                    case R.id.action_person:
                        setMyInfo(0);
                        break;
                }
                return true;
            }
        });

        // 카테고리 객체생성.
        category =new category();
        category_all = new category_all();
        category_show = new category_show();
        category_display = new category_display();
        category_concert = new category_concert();
        category_musical = new category_musical();
        category_movie = new category_movie();
        category_festival = new category_festival();
        category_content_etc = new category_content_etc();
        category_whole = new category_whole();
        category_seoul = new category_seoul();
        category_gyeong_incheon = new category_gyeong_incheon();
        category_gangwon = new category_gangwon();
        category_chung = new category_chung();
        category_gyeong = new category_gyeong();
        category_jeolla = new category_jeolla();
        category_whole_etc = new category_whole_etc();

        // 커뮤니티 객체생성.
        community_all =new community_all();
        community_together = new community_together();
        community_review = new community_review();
        community_write = new community_write();

        // 지도 객체생성.
        jmap =new jmap();

        // 메시지 객체생성.
        message_write = new message_write();
        message_read =new message_read();

        // 내정보 객체생성.
        myinfo =new my_info();

        setMap(); // 지도가 첫 화면.
    }

    // 카테고리 화면이동(순서는 선언순서).
    public void setCategory(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, category);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, category_all);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, category_show);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, category_display);
                ft.commit();
                break;
            case 4:
                ft.replace(R.id.main_frame, category_concert);
                ft.commit();
                break;
            case 5:
                ft.replace(R.id.main_frame, category_musical);
                ft.commit();
                break;
            case 6:
                ft.replace(R.id.main_frame, category_movie);
                ft.commit();
                break;
            case 7:
                ft.replace(R.id.main_frame, category_festival);
                ft.commit();
                break;
            case 8:
                ft.replace(R.id.main_frame, category_content_etc);
                ft.commit();
                break;
            case 9:
                ft.replace(R.id.main_frame, category_whole);
                ft.commit();
                break;
            case 10:
                ft.replace(R.id.main_frame, category_seoul);
                ft.commit();
                break;
            case 11:
                ft.replace(R.id.main_frame, category_gyeong_incheon);
                ft.commit();
                break;
            case 12:
                ft.replace(R.id.main_frame, category_gangwon);
                ft.commit();
                break;
            case 13:
                ft.replace(R.id.main_frame, category_chung);
                ft.commit();
                break;
            case 14:
                ft.replace(R.id.main_frame, category_gyeong);
                ft.commit();
                break;
            case 15:
                ft.replace(R.id.main_frame, category_jeolla);
                ft.commit();
                break;
            case 16:
                ft.replace(R.id.main_frame, category_whole_etc);
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
                ft.replace(R.id.main_frame, community_all);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, community_together);
                ft.commit();
                break;
            case 2:
                ft.replace(R.id.main_frame, community_review);
                ft.commit();
                break;
            case 3:
                ft.replace(R.id.main_frame, community_write);
                ft.commit();
                break;
        }
    }

    // 지도 화면이동.
    public void setMap() {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        ft.replace(R.id.main_frame, jmap);
        ft.commit();
    }

    // 메시지 화면이동(순서는 선언순서).
    public void setMessage(int n) {
        fm = getSupportFragmentManager();
        ft = fm.beginTransaction();
        switch (n) {
            case 0:
                ft.replace(R.id.main_frame, message_read);
                ft.commit();
                break;
            case 1:
                ft.replace(R.id.main_frame, message_write);
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
                ft.replace(R.id.main_frame, myinfo);
                ft.commit();
                break;
        }
    }
}