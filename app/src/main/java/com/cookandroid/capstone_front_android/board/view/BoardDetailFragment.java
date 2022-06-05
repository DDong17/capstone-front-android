package com.cookandroid.capstone_front_android.board.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;

public class BoardDetailFragment extends Fragment {

    private View view;
    private String title;
    private String content;
    private TextView tvTitle;
    private TextView tvContent;
    private ImageView btnBack;
    private MainActivity activity;

    public BoardDetailFragment setTitle(String title) {
        this.title = title;
        return this;
    }

    public BoardDetailFragment setContent(String content) {
        this.content = content;
        return this;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        view = inflater.inflate(R.layout.fragment_board_detail, container, false);
        tvTitle = view.findViewById(R.id.tv_board_detail_title);
        tvContent = view.findViewById(R.id.tv_board_detail_content);
        btnBack = view.findViewById(R.id.btn_board_detail_back);

        tvTitle.setText(title);
        tvContent.setText(content);

        activity = (MainActivity) getActivity();

        // 뒤로가기 버튼
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setCommunity(0); }
        });

        return view;
    }

}
