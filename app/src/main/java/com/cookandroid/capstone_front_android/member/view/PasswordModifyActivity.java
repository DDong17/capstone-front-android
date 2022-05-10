package com.cookandroid.capstone_front_android.member.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.member.model.request.PasswordModifyRequest;
import com.cookandroid.capstone_front_android.my_info;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordModifyActivity extends AppCompatActivity {

    private EditText edtCurrentPassword;
    private EditText edtNewPassword;
    private EditText edtNewPasswordCheck;
    private Button btnRegister;
    private Button btnBack;

    private AlertDialog dialog;

    private ServiceApi service;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_modify);

        edtCurrentPassword = (EditText) findViewById(R.id.currentPassword_btn);
        edtNewPassword = (EditText) findViewById(R.id.newPassword_btn);
        edtNewPasswordCheck = (EditText) findViewById(R.id.newPasswordCheck_btn);
        btnRegister = (Button) findViewById(R.id.Changepassword_btn);
        btnBack = (Button) findViewById(R.id.Changepassword_back);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Change_password();
            }
        });

        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), my_info.class);
                startActivity(intent);
            }
        });
    }

    private void Change_password() {
        edtCurrentPassword.setError(null);
        edtNewPassword.setError(null);
        edtNewPasswordCheck.setError(null);

        String currentPassword = edtCurrentPassword.getText().toString();
        String newPassword = edtNewPassword.getText().toString();
        String newPasswordCheck = edtNewPasswordCheck.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (!isPasswordValid(currentPassword)) {
            edtCurrentPassword.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = edtCurrentPassword;
            cancel = true;
        }
        if (!isPasswordValid(newPassword)) {
            edtNewPassword.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = edtNewPassword;
            cancel = true;
        }
        if (!isPasswordValid(newPasswordCheck)) {
            edtNewPasswordCheck.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = edtNewPasswordCheck;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            start_password_modify(new PasswordModifyRequest(currentPassword, newPassword, newPasswordCheck));

        }
    }

    private void start_password_modify(PasswordModifyRequest data) {
        service.passwordModify(data).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                MemberResponse result = response.body();
                AlertDialog.Builder builder = new AlertDialog.Builder(PasswordModifyActivity.this);
                dialog = builder.setMessage("변경성공.").setPositiveButton("확인", null).create();
                dialog.show();
            }
            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                Toast.makeText(PasswordModifyActivity.this, " 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e(" 에러 발생", t.getMessage());

            }
        });
    }

    private boolean isPasswordValid(String mnewPassword) {
        return mnewPassword.length() >= 6;
    }
}


