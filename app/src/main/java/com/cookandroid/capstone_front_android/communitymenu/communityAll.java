package com.cookandroid.capstone_front_android.communitymenu;

import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.MemberApi;
import com.cookandroid.capstone_front_android.member.model.request.BoardRequest;
import com.cookandroid.capstone_front_android.member.model.response.BoardResponse;
import com.cookandroid.capstone_front_android.network.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class communityAll extends Fragment {
    private View view;

    private MainActivity activity;

    // 커뮤니티 버튼.
    private Button btn_all; // 전체보기.
    private Button btn_together; // 같이 갈 사람.
    private Button btn_review; // 리뷰.
    private Button btn_write; // 글쓰기.

    private MemberApi service;
    Retrofit retrofit;

    RecyclerView recyclerView;
    RecyclerAdapter adapter;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_community_all,container,false);

        retrofit = new Retrofit.Builder()
                .baseUrl(RetrofitClient.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        service = retrofit.create(MemberApi.class);

        activity = (MainActivity) getActivity();

        // 버튼 설정.
        btn_all = view.findViewById(R.id.all);
        btn_together = view.findViewById(R.id.together);
        btn_review = view.findViewById(R.id.review);
        btn_write = view.findViewById(R.id.write);

        // 버튼이벤트.
        btn_all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setCommunity(0); }
        });

        btn_together.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(1);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(2);
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setCommunity(3); }
        });

        /*recyclerView = view.findViewById(R.id.list);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);

        adapter = new RecyclerAdapter();
        recyclerView.setAdapter(adapter);*/

        /*ItemDTO item = new ItemDTO();
        item.setTitle("제목1");
        item.setContent("내용1");
        adapter.addItem(item);

        //ItemDTO item2 = new ItemDTO();
        item.setTitle("제목2");
        item.setContent("내용2");
        adapter.addItem(item);*/


        //listView = (ListView) view.findViewById(R.id.list);

        //adapter = new ItemAdapter();
        //listView.setAdapter(adapter);

        getBoard();
        //putBoard(new BoardRequest("제목", "내용"));
        //deleteBoard();
        //postBoard(new BoardRequest("제목4", "내용4"));
        //adapter.addItem("제목1", "내용1");
        //adapter.addItem("제목2", "내용2");
        //adapter.notifyDataSetChanged();

        return view;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void postBoard(BoardRequest board) {
        //BoardRequest board = new BoardRequest("제목4", "내용4");

        Call<BoardResponse> call = service.postBoard(board);

        call.enqueue(new Callback<BoardResponse>() {
            @Override
            public void onResponse(Call<BoardResponse> call, Response<BoardResponse> response) {
                if (!response.isSuccessful()) {
                    //Log.i("제목", response.body().getTitle());
                    Log.i("반응성공", String.valueOf(response.code()));
                    return;
                }
                Log.i("반응성공", "성공");
                //BoardDTO boardResponse = response.body();
                //adapter.addItem(boardResponse.getTitle(), boardResponse.getContent());
                //adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<BoardResponse> call, Throwable t) {
                Log.i("반응실패", "실패");
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void getBoard(){
        Call<List<BoardResponse>> call = service.getBoard();

        call.enqueue(new Callback<List<BoardResponse>>() {
            @Override
            public void onResponse(Call<List<BoardResponse>> call, Response<List<BoardResponse>> response) {
                if (!response.isSuccessful()) {
                    Log.i("반응성공", "오류");
                    return;
                }
                Log.i("반응성공", "성공");
                List<BoardResponse> boardResponse = response.body();

                /*for(int i=0; i<boardResponse.size(); i++){
                    Log.i("반복", String.valueOf(i));
                }*/
                //adapter.addItem(boardResponse.get(0));
                ItemList(boardResponse);
                //adapter = new RecyclerAdapter(getActivity(), boardResponse);
                //recyclerView.setAdapter(adapter);
                /*ItemDTO item3 = new ItemDTO();
                item3.setTitle(boardResponse.get(0).getTitle());
                item3.setContent(boardResponse.get(0).getContent());
                adapter.addItem(item3);*/
                //adapter.addItem(String.valueOf(boardResponse.get(0).getTitle()), boardResponse.get(0).getContent());

                adapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<List<BoardResponse>> call, Throwable t) {
                Log.i("반응실패", t.getMessage());
            }
        });
    }

    private void putBoard(BoardRequest data){
        Call<BoardResponse> call = service.putBoard(9L, data);
        call.enqueue(new Callback<BoardResponse>() {
            @Override
            public void onResponse(Call<BoardResponse> call, Response<BoardResponse> response) {
                if (!response.isSuccessful()) {
                    Log.i("반응성공", String.valueOf(response.code()));
                    return;
                }
                Log.i("반응성공", "성공");

            }

            @Override
            public void onFailure(Call<BoardResponse> call, Throwable t) {
                Log.i("반응실패", t.getMessage());
            }
        });

    }

    private void deleteBoard(){
        Call<Void> call = service.deleteBoard(9L);
        call.enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                if (!response.isSuccessful()) {
                    Log.i("반응성공", String.valueOf(response.code()));
                    return;
                }
                Log.i("반응성공", "성공");
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {
                Log.i("반응실패", t.getMessage());
            }
        });
    }

    private void ItemList(List<BoardResponse> list){
        recyclerView = getActivity().findViewById(R.id.list);
        //RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new RecyclerAdapter(getContext(), list);
        recyclerView.setAdapter(adapter);
        //recyclerView.addItemDecoration(new RecyclerViewDecoration(25));
        adapter.notifyDataSetChanged();
    }
}
