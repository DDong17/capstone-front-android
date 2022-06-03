package com.cookandroid.capstone_front_android.board.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.R;

public class BoardTest extends Fragment {

    private View view;                      //
    private TextView locTitle;              // 문화 생활 이름
    private TextView locInfo;               // 문화 생활 정보

    private String title;
    private String content;

    public BoardTest setTitle(String title) {
        this.title = title;
        return this;
    }

    public BoardTest setContent(String content) {
        this.content = content;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.location_info, container, false);

        locTitle = view.findViewById(R.id.title);
        locInfo  = view.findViewById(R.id.info);

        locTitle.setText(title);
        locInfo.setText(content);

        return view;
    }

}
