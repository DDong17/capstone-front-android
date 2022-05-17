package com.cookandroid.capstone_front_android.member.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.member.model.request.FindPasswordRequest;
import com.cookandroid.capstone_front_android.R;
//import com.cookandroid.capstone_front_android.data.Password_reset;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.member.model.MemberApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindPasswordActivity extends AppCompatActivity {

    private EditText edtUserId;
    private EditText edtEmail;
    private Button btnFindPassword;
    private Button btnBack;
    private AlertDialog dialog;

    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_password);
        edtUserId = (EditText) findViewById(R.id.findPasswordUser);
        edtEmail = (EditText) findViewById(R.id.findPasswordEmail);
        btnFindPassword = (Button) findViewById(R.id.findPasswordBtn);
        btnBack = (Button) findViewById(R.id.findPasswordBack);

        btnFindPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                findPassword();
            }
        });
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findPassword() {
        edtUserId.setError(null);
        edtEmail.setError(null);

        String userId = edtUserId.getText().toString();
        String email = edtEmail.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (cancel) {
            focusView.requestFocus();
        } else {
            startFindPassword(new FindPasswordRequest(userId, email));
        }
    }

    private void startFindPassword(FindPasswordRequest data) {
        memberApi.findPassword(data).enqueue(new Callback<MemberResponse>() {

            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(FindPasswordActivity.this);
                dialog = builder.setMessage("임시 비밀번호 발급되었습니다.").setPositiveButton("확인", null).create();
                dialog.show();
            }
            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                Log.e("로그인 에러 발생", t.getMessage());

            }

        });
    }
}