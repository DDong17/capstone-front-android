package com.cookandroid.capstone_front_android.categorymenu;

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

    private Button[] areaButtons              = new Button[10];                     // 지역 버튼
    private View.OnClickListener areaListener = new View.OnClickListener() {        // 지역 버튼 리스너
        @Override
        public void onClick(View view) {
            int code;
            switch(view.getId()) {
                case R.id.locationAreaButton01:
                    code = 1;
                    break;
                case R.id.locationAreaButton02:
                    code = 31;
                    break;
                case R.id.locationAreaButton03:
                    code = 32;
                    break;
                case R.id.locationAreaButton04:
                    code = 33;
                    break;
                case R.id.locationAreaButton05:
                    code = 34;
                    break;
                case R.id.locationAreaButton06:
                    code = 35;
                    break;
                case R.id.locationAreaButton07:
                    code = 36;
                    break;
                case R.id.locationAreaButton08:
                    code = 37;
                    break;
                case R.id.locationAreaButton09:
                    code = 38;
                    break;
                case R.id.locationAreaButton10:
                    code = 39;
                    break;
                default:
                    code = 99;
            }

            code |= 0x00001000;
            activity.setCategory(code);
        }
    };

    private Button[] contentButtons              = new Button[8];                   // 종류 버튼
    private View.OnClickListener contentListener = new View.OnClickListener() {     // 종류 버튼 리스너
        @Override
        public void onClick(View view) {
            int code;
            switch(view.getId()) {
                case R.id.locationContentButton01:
                    code = 12;
                    break;
                case R.id.locationContentButton02:
                    code = 14;
                    break;
                case R.id.locationContentButton03:
                    code = 15;
                    break;
//                case R.id.locationContentButton04:
//                    code = 25;
//                    break;
                case R.id.locationContentButton05:
                    code = 28;
                    break;
                case R.id.locationContentButton06:
                    code = 32;
                    break;
                case R.id.locationContentButton07:
                    code = 38;
                    break;
                case R.id.locationContentButton08:
                    code = 39;
                    break;
                default:
                    code = 99;
            }

            code |= 0x00002000;
            activity.setCategory(code);
        }
    };

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.category,container,false);
        activity = (MainActivity) getActivity();

        // 지역 버튼 설정
        areaButtons[0] = view.findViewById(R.id.locationAreaButton01);
        areaButtons[1] = view.findViewById(R.id.locationAreaButton02);
        areaButtons[2] = view.findViewById(R.id.locationAreaButton03);
        areaButtons[3] = view.findViewById(R.id.locationAreaButton04);
        areaButtons[4] = view.findViewById(R.id.locationAreaButton05);
        areaButtons[5] = view.findViewById(R.id.locationAreaButton06);
        areaButtons[6] = view.findViewById(R.id.locationAreaButton07);
        areaButtons[7] = view.findViewById(R.id.locationAreaButton08);
        areaButtons[8] = view.findViewById(R.id.locationAreaButton09);
        areaButtons[9] = view.findViewById(R.id.locationAreaButton10);

        // 종류 버튼 설정
        contentButtons[0] = view.findViewById(R.id.locationContentButton01);
        contentButtons[1] = view.findViewById(R.id.locationContentButton02);
        contentButtons[2] = view.findViewById(R.id.locationContentButton03);
//        contentButtons[3] = view.findViewById(R.id.locationContentButton04);
        contentButtons[4] = view.findViewById(R.id.locationContentButton05);
        contentButtons[5] = view.findViewById(R.id.locationContentButton06);
        contentButtons[6] = view.findViewById(R.id.locationContentButton07);
        contentButtons[7] = view.findViewById(R.id.locationContentButton08);

        // 버튼 이벤트 설정
        for(Button b: areaButtons)
            if(b != null) b.setOnClickListener(areaListener);
        for(Button b: contentButtons)
            if(b != null) b.setOnClickListener(contentListener);

        return view;
    }

}
