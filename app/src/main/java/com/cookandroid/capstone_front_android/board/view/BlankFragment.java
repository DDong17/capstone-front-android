//package com.cookandroid.capstone_front_android.board.view;
//
//import android.os.Bundle;
//import android.util.Log;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.Button;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//import androidx.fragment.app.Fragment;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import com.cookandroid.capstone_front_android.MainActivity;
//import com.cookandroid.capstone_front_android.R;
//import com.cookandroid.capstone_front_android.board.model.BoardApi;
//import com.cookandroid.capstone_front_android.board.model.BoardListResponse;
//import com.cookandroid.capstone_front_android.board.model.BoardRequest;
//import com.cookandroid.capstone_front_android.board.model.BoardResponse;
////import com.cookandroid.capstone_front_android.board.presenter.RecyclerAdapter;
//import com.cookandroid.capstone_front_android.board.presenter.RecyclerAdapter;
//import com.cookandroid.capstone_front_android.member.model.MemberApi;
//import com.cookandroid.capstone_front_android.util.model.BooleanDTO;
//import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class BlankFragment extends Fragment {
//    private final BoardApi boardApi = RetrofitClient.getClient(BoardApi.class, RetrofitClient.getSessionId());
//    private View view;
//    private MainActivity activity;
//
//    BoardListResponse dataList;
//    List<BoardResponse> dataInfo;
//    RecyclerView recyclerView;
//    RecyclerAdapter recyclerAdapter;
//
//    // 커뮤니티 버튼.
//    private Button btn_all; // 전체보기.
//    private Button btn_together; // 같이 갈 사람.
//    private Button btn_review; // 리뷰.
//    private Button btn_write; // 글쓰기.
//
//    @Override
//    public void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//
//    }
//
//
//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container,
//                             Bundle savedInstanceState) {
//        View view1 = inflater.inflate(R.layout.item_community_all,container,false);
//        activity = (MainActivity) getActivity();
//        // 버튼 설정.
//        btn_all = view.findViewById(R.id.all);
//        btn_together = view.findViewById(R.id.together);
//        btn_review = view.findViewById(R.id.review);
//        btn_write = view.findViewById(R.id.write);
//
//        // 버튼이벤트.
//        btn_all.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activity.setCommunity(0);
//                start();
//            }
//        });
//
//        btn_together.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activity.setCommunity(1);
//            }
//        });
//
//        btn_review.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activity.setCommunity(2);
//            }
//        });
//
//        btn_write.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                activity.setCommunity(3);
//            }
//        });
//        return view;
//
//
//    }
//
//    private void start() {
//        Call<BoardListResponse> call = boardApi.getBoard();
//        call.enqueue(new Callback<BoardListResponse>() {
//            @Override
//            public void onResponse(Call<BoardListResponse> call, Response<BoardListResponse> response) {
//                dataList=response.body();
//
//
//                dataInfo = dataList.boards;
//
//                recyclerAdapter = new RecyclerAdapter(activity.getApplicationContext(), dataInfo);
//                recyclerView.setAdapter(recyclerAdapter);
//
//
//
//
//
//            }
//
//            @Override
//            public void onFailure(Call<BoardListResponse> call, Throwable t) {
//
//            }
//
//        });
//    }
//
//
////    @Override
////    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//////        super.onViewCreated(view, savedInstanceState);
//////        dataInfo = new ArrayList<>();
//////        recyclerView = view.findViewById(R.id.recyclerView);
//////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
//////        recyclerView.setLayoutManager(layoutManager);
//////        view = inflater.inflate(R.layout.item_community_all,container,false);
//////
//////        activity = (MainActivity) getActivity();
//////        // 버튼 설정.
//////        btn_all = view.findViewById(R.id.all);
//////        btn_together = view.findViewById(R.id.together);
//////        btn_review = view.findViewById(R.id.review);
//////        btn_write = view.findViewById(R.id.write);
//////
//////        // 버튼이벤트.
//////        btn_all.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                activity.setCommunity(0);
//////                start();
//////            }
//////        });
//////
//////        btn_together.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                activity.setCommunity(1);
//////            }
//////        });
//////
//////        btn_review.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                activity.setCommunity(2);
//////            }
//////        });
//////
//////        btn_write.setOnClickListener(new View.OnClickListener() {
//////            @Override
//////            public void onClick(View view) {
//////                activity.setCommunity(3);
//////            }
//////        });
//////        return view;
//////
//////    }
//////    private void start() {
//////        Call<BoardListResponse> call = boardApi.getBoard();
//////        call.enqueue(new Callback<BoardListResponse>() {
//////            @Override
//////            public void onResponse(Call<BoardListResponse> call, Response<BoardListResponse> response) {
//////                dataList=response.body();
//////
//////
//////                dataInfo = dataList.boards;
//////
//////                recyclerAdapter = new RecyclerAdapter(activity.getApplicationContext(), dataInfo);
//////                recyclerView.setAdapter(recyclerAdapter);
//////
//////
//////
//////
//////
//////            }
//////
//////            @Override
//////            public void onFailure(Call<BoardListResponse> call, Throwable t) {
//////
//////            }
//////
//////        });
//////    }
////        super.onViewCreated(view,savedInstanceState);
////        dataInfo = new ArrayList<>();
////        view = inflater.inflate(R.layout.item_community_all,container,false);
////        recyclerView = view.findViewById(R.id.recyclerView);
////        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
////        recyclerView.setLayoutManager(layoutManager);
////
////
////
////        activity = (MainActivity) getActivity();
////        // 버튼 설정.
////        btn_all = view.findViewById(R.id.all);
////        btn_together = view.findViewById(R.id.together);
////        btn_review = view.findViewById(R.id.review);
////        btn_write = view.findViewById(R.id.write);
////
////        // 버튼이벤트.
////        btn_all.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                activity.setCommunity(0);
////                start();
////            }
////        });
////
////        btn_together.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                activity.setCommunity(1);
////            }
////        });
////
////        btn_review.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                activity.setCommunity(2);
////            }
////        });
////
////        btn_write.setOnClickListener(new View.OnClickListener() {
////            @Override
////            public void onClick(View view) {
////                activity.setCommunity(3);
////            }
////        });
////        return view;
////
////
////    }
//
////    private void start() {
////        Call<BoardListResponse> call = boardApi.getBoard();
////        call.enqueue(new Callback<BoardListResponse>() {
////            @Override
////            public void onResponse(Call<BoardListResponse> call, Response<BoardListResponse> response) {
////                dataList=response.body();
////
////
////                dataInfo = dataList.boards;
////
////                recyclerAdapter = new RecyclerAdapter(activity.getApplicationContext(), dataInfo);
////                recyclerView.setAdapter(recyclerAdapter);
////
////
////
////
////
////            }
////
////            @Override
////            public void onFailure(Call<BoardListResponse> call, Throwable t) {
////
////            }
////
////        });
////    }
////}