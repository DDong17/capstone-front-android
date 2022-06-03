package com.cookandroid.capstone_front_android.member.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.ImageButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
import com.cookandroid.capstone_front_android.member.model.MemberApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateNicknameActivity extends AppCompatActivity {
    private EditText edtNewNickname;

    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class, RetrofitClient.getSessionId());

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_nickname);

        edtNewNickname = (EditText) findViewById(R.id.edt_updateNickname);
        Button btnUpdateNickname = (Button) findViewById(R.id.btn_nicknameCheck);
        ImageButton btnBack = (ImageButton) findViewById(R.id.btn_updateNickname_back);

        // 닉네임 중복 확인
        btnUpdateNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                edtNewNickname.setError(null);
                String newNickname = edtNewNickname.getText().toString();
                updateNickname(newNickname);
            }
        });

        // 뒤로가기
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void updateNickname(String newNickname) {
        memberApi.putUpdateNickname(newNickname).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(@NonNull Call<MemberResponse> call, @NonNull Response<MemberResponse> response) {
                if (response.body() == null) {
                    Toast.makeText(getApplicationContext(), "중복된 닉네임 입니다", Toast.LENGTH_SHORT).show();
                    return;
                }
                new AlertDialog.Builder(UpdateNicknameActivity.this)
                        .setMessage("[" + newNickname + "]" + "으로 닉네임을 변경하시겠습니까?")
                        .setPositiveButton("확인", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getApplicationContext(), "닉네임 변경 완료", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            }
                        })
                        .setNegativeButton("취소", null)
                        .create()
                        .show();
            }

            @Override
            public void onFailure(@NonNull Call<MemberResponse> call, @NonNull Throwable t) {
                Toast.makeText(UpdateNicknameActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
            }
        });
    }
}
