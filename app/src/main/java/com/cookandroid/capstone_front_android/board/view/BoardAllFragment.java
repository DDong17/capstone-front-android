package com.cookandroid.capstone_front_android.board.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.board.model.BoardApi;
import com.cookandroid.capstone_front_android.board.model.BoardListResponse;
import com.cookandroid.capstone_front_android.board.model.BoardResponse;
//import com.cookandroid.capstone_front_android.board.presenter.RecyclerAdapter;
import com.cookandroid.capstone_front_android.board.presenter.BoardAdapter;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardAllFragment extends Fragment {

    private final BoardApi boardApi = RetrofitClient.getClient(BoardApi.class, RetrofitClient.getSessionId());

    BoardListResponse dataList;
    List<BoardResponse> dataInfo;
    RecyclerView recyclerView;
    BoardAdapter boardAdapter;

    private View view;
    private MainActivity activity;

    // 커뮤니티 버튼.
    private Button btn_all; // 전체보기.
    private Button btn_together; // 같이 갈 사람.
    private Button btn_review; // 리뷰.
    private Button btn_write; // 글쓰기.



    @Override
    public View onCreateView( @NonNull LayoutInflater inflater,  @Nullable ViewGroup container,  Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dataInfo = new ArrayList<>();
        view = inflater.inflate(R.layout.item_community_all,container,false);
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        boardAdapter = new BoardAdapter(getContext(), dataInfo);
        recyclerView.setAdapter(boardAdapter);

        activity = (MainActivity) getActivity();
        // 버튼 설정.
        btn_all = view.findViewById(R.id.all);
        btn_together = view.findViewById(R.id.together);
        btn_review = view.findViewById(R.id.review);
        btn_write = view.findViewById(R.id.write);

        // 버튼이벤트.
//        btn_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(getContext(), "전체보기 눌러짐", Toast.LENGTH_SHORT).show();
//                activity.setCommunity(0);
//                start();
//                boardAdapter.setItemClickListener(new BoardAdapter.OnItemClickListener() {
//                    @Override
//                    public void onClick(View v, int position) {
//                        Toast.makeText(getContext(), "상세보기 눌러짐", Toast.LENGTH_SHORT).show();
//                    }
//                });
//            }
//        });

//        boardAdapter.onItemClickListener = new BoardAdapter.OnItemClickListener() {
//            @Override
//            public void onClick(View v, int position) {
//                Toast.makeText(getContext(), "상세보기 눌러짐", Toast.LENGTH_SHORT).show();
//                Log.d("Asdfasdfasdf", "ASdfasdfasdf");
//            }
//        };
        boardAdapter.setItemClickListener(new BoardAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Log.d("Asdfasdfasdf", "ASdfasdfasdf");
            }
        });

        btn_together.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setCommunity(1);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setCommunity(2);
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setCommunity(3);
            }
        });
        start();
        return view;
    }

    private void start() {
        Call<BoardListResponse> call = boardApi.getBoard();
        call.enqueue(new Callback<BoardListResponse>() {
            @Override
            public void onResponse(Call<BoardListResponse> call, Response<BoardListResponse> response) {
                dataList = response.body();
                dataInfo = dataList.boards;
                start2(dataInfo);
                //boardAdapter = new BoardAdapter(getContext(), dataInfo);
                boardAdapter = new BoardAdapter(getContext(), dataInfo, activity);
                recyclerView.setAdapter(boardAdapter);
//                boardAdapter.onItemClickListener = BoardAdapter.OnItemClickListener
                boardAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<BoardListResponse> call, Throwable t) {

            }

        });
    }

    private void start2(List<BoardResponse> dataInfo) {
        recyclerView = view.findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setReverseLayout(true);
        layoutManager.setStackFromEnd(true);
        recyclerView.setLayoutManager(layoutManager);
        boardAdapter = new BoardAdapter(getContext(), dataInfo);
        recyclerView.setAdapter(boardAdapter);
    }

}