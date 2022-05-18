package com.cookandroid.capstone_front_android.communitymenu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.MemberApi;

import retrofit2.Retrofit;

public class communityAll extends Fragment {
    private View view;
    private ListView listView;

    private MainActivity activity;

    // 커뮤니티 버튼.
    private Button btnAll; // 전체보기.
    private Button btnTogether; // 같이 갈 사람.
    private Button btnReview; // 리뷰.
    private Button btnWrite; // 글쓰기.
    
    private MemberApi service;
    Retrofit retrofit;
    ItemAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_community_all,container,false);

        activity = (MainActivity) getActivity();

        // 버튼 설정.
        btnAll = view.findViewById(R.id.all);
        btnTogether = view.findViewById(R.id.together);
        btnReview = view.findViewById(R.id.review);
        btnWrite = view.findViewById(R.id.write);

        // 버튼이벤트.
        btnAll.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setCommunity(0); }
        });

        btnTogether.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(1);
            }
        });

        btnReview.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(2);
            }
        });

        btnWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setCommunity(3); }
        });

        listView = (ListView) view.findViewById(R.id.list);

        adapter = new ItemAdapter();
        listView.setAdapter(adapter);

        adapter.addItem("제목1", "내용1");
        adapter.addItem("제목2", "내용2");
        adapter.addItem("제목3", "내용3");
        adapter.notifyDataSetChanged();
        
        return view;
    }
}
