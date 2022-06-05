package com.cookandroid.capstone_front_android.board.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.board.model.BoardApi;
import com.cookandroid.capstone_front_android.board.model.BoardRequest;
import com.cookandroid.capstone_front_android.board.model.BoardResponse;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BoardWriteFragment extends Fragment {
    private View view;
    private EditText edtBoardWriteTitle;
    private EditText edtBoardWriteContent;
    private Button btnBoardWriteRegister;
    private ImageView btnBack;
    private MainActivity activity;


    private final BoardApi boardApi = RetrofitClient.getClient(BoardApi.class, RetrofitClient.getSessionId());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_write_board, container, false);

        activity = (MainActivity) getActivity();

        edtBoardWriteTitle = (EditText) view.findViewById(R.id.edt_write_board_title);
        edtBoardWriteContent = (EditText) view.findViewById(R.id.edt_write_board_content);
        btnBoardWriteRegister = (Button) view.findViewById(R.id.btn_registerBoard);
        btnBack = view.findViewById(R.id.btn_write_board_back);

        // 뒤로가기 버튼
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setCommunity(0); }
        });

        // 게시글 등록
        btnBoardWriteRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeBoard() ;
            }
        });

        return view;
    }

    private void writeBoard() {
        edtBoardWriteTitle.setError(null);
        edtBoardWriteContent.setError(null);


        String title = edtBoardWriteTitle.getText().toString();
        String content = edtBoardWriteContent.getText().toString();


        boolean cancel = false;
        View focusView = null;


        if (cancel) {
            focusView.requestFocus();
        } else {
            startBoardWrite(new BoardRequest(title,content));

        }
    }

    private void startBoardWrite(BoardRequest data) {
        boardApi.registerBoardWrite(data).enqueue(new Callback<BoardResponse>() {
            @Override
            public void onResponse(@NonNull Call<BoardResponse> call, @NonNull Response<BoardResponse> response) {
                Toast.makeText(getContext(), "게시글을 작성했습니다.", Toast.LENGTH_SHORT).show();
                activity.setCommunity(0);
                edtBoardWriteTitle.setText("");
                edtBoardWriteContent.setText("");
            }
            @Override
            public void onFailure(@NonNull Call<BoardResponse> call, @NonNull Throwable t) {
                Log.e(" 에러 발생", t.getMessage());
            }
        });
    }
}
