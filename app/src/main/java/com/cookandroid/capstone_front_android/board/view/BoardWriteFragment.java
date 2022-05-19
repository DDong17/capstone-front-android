package com.cookandroid.capstone_front_android.board.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.board.model.BoardApi;
import com.cookandroid.capstone_front_android.member.model.MemberApi;
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
    private Button btnBack;
    private MainActivity activity;


    private final BoardApi boardApi = RetrofitClient.getClient(BoardApi.class, RetrofitClient.getSessionId());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.community_write, container, false);

        activity = (MainActivity) getActivity();

        edtBoardWriteTitle = (EditText) view.findViewById(R.id.boardWriteTitle);
        edtBoardWriteContent = (EditText) view.findViewById(R.id.boardWriteContent);
        btnBoardWriteRegister = (Button) view.findViewById(R.id.BoardWriteRegister);
        btnBack = view.findViewById(R.id.boardWriteBtnBack);

        // 버튼이벤트.
        btnBack.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setCommunity(0); }
        });

        btnBoardWriteRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                BoardWrite() ;
            }
        });

        return view;
    }

    private void BoardWrite() {
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
            public void onResponse(Call<BoardResponse> call, Response<BoardResponse> response) {




            }
            @Override
            public void onFailure(Call<BoardResponse> call, Throwable t) {

                Log.e(" 에러 발생", t.getMessage());

            }
        });
    }
}
