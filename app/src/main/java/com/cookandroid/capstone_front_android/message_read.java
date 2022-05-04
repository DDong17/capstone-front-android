package com.cookandroid.capstone_front_android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class message_read extends Fragment {

    private View view;

    private MainActivity activity;

    private Button btn_write; // 메시지 쓰기 버튼.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.message_read,container,false);

        activity = (MainActivity) getActivity();

        // 버튼 설정.
        btn_write = view.findViewById(R.id.write);

        // 버튼 이벤트.
        btn_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setMessage(1);
            }
        });

        return view;
    }
}
