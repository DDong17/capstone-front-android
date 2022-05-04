package category_menu;

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
    private Button btn_all; // 전체.
    private Button btn_show; // 공연.
    private Button btn_display; // 전시.
    private Button btn_concert; // 콘서트.
    private Button btn_musical; // 뮤지컬.
    private Button btn_movie; // 영화.
    private Button btn_festival; // 축제.
    private Button btn_content_etc; // 기타(콘텐츠).
    private Button btn_whole; // 전국.
    private Button btn_seoul; // 서울.
    private Button btn_gyeong_incheon; // 경기, 인천.
    private Button btn_gangwon; // 강원도.
    private Button btn_chung; // 충청도.
    private Button btn_gyeong; // 경상도.
    private Button btn_jeolla; // 전라도.
    private Button btn_whole_etc; // 기타(위치).

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.category,container,false);

        activity = (MainActivity) getActivity();

        // 버튼설정.
        btn_all = view.findViewById(R.id.all_content);
        btn_show = view.findViewById(R.id.show);
        btn_display = view.findViewById(R.id.display);
        btn_concert = view.findViewById(R.id.concert);
        btn_musical = view.findViewById(R.id.musical);
        btn_movie = view.findViewById(R.id.movie);
        btn_festival = view.findViewById(R.id.festival);
        btn_content_etc = view.findViewById(R.id.content_etc);
        btn_whole = view.findViewById(R.id.whole);
        btn_seoul = view.findViewById(R.id.seoul);
        btn_gyeong_incheon = view.findViewById(R.id.gyeong_incheon);
        btn_gangwon = view.findViewById(R.id.gangwon);
        btn_chung = view.findViewById(R.id.chung);
        btn_gyeong = view.findViewById(R.id.gyeong);
        btn_jeolla = view.findViewById(R.id.jeolla);
        btn_whole_etc = view.findViewById(R.id.whole_etc);

        // 버튼이벤트.
        btn_all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(1);
            }
        });

        btn_show.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(2);
            }
        });

        btn_display.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(3);
            }
        });

        btn_concert.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(4);
            }
        });

        btn_musical.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(5);
            }
        });

        btn_movie.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(6);
            }
        });

        btn_festival.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(7);
            }
        });

        btn_content_etc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(8);
            }
        });

        btn_whole.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(9);
            }
        });

        btn_seoul.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(10);
            }
        });

        btn_gyeong_incheon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(11);
            }
        });

        btn_gangwon.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(12);
            }
        });

        btn_chung.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(13);
            }
        });

        btn_gyeong.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(14);
            }
        });

        btn_jeolla.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(15);
            }
        });

        btn_whole_etc.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(16);
            }
        });

        return view;
    }

}
