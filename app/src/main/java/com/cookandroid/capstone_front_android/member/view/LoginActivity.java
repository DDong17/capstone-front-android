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

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.request.LoginRequest;
//import com.cookandroid.capstone_front_android.data.Password_reset;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.network.ServiceApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUserId;
    private EditText edtPassword;
    private Button btnLogin;
    private Button btnRegister;
    private Button btnFindPassword;

    private AlertDialog dialog;

    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserId = (EditText) findViewById(R.id.login_userId);
        edtPassword = (EditText) findViewById(R.id.login_password);
        btnLogin = (Button) findViewById(R.id.login_button);
        btnRegister = (Button) findViewById(R.id.register_button);
        btnFindPassword = (Button) findViewById((R.id.password_find_btn));

        service = RetrofitClient.getClient().create(ServiceApi.class);

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
        btnFindPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindPasswordActivity.class);
                startActivity(intent);
            }
        });


    }

    private void attemptLogin() {
        edtUserId.setError(null);
        edtPassword.setError(null);


        String userId = edtUserId.getText().toString();
        String password = edtPassword.getText().toString();

        boolean cancel = false;
        View focusView = null;


        //아이디 유효성 검사
        if (userId.isEmpty()) {
            edtUserId.setError("아이디를 입력해주세요");
            focusView = edtUserId;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            edtUserId.setError("아이디는 4~10글자 사이로 입력해주세요");
            focusView = edtUserId;
            cancel = true;
        }


        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            edtPassword.setError("비밀번호를 입력해주세요.");
            focusView = edtPassword;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            edtPassword.setError("비밀번호는 6~10글자 사이로 입력해주세요");
            focusView = edtPassword;
            cancel = true;
        }


        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginRequest(userId, password));

        }
    }

    private void startLogin(LoginRequest data) {
        service.userLogin(data).enqueue(new Callback<MemberResponse>() {

            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                MemberResponse result = response.body();
                try {
                    if (result.getUserId() == null) ;

                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(intent);


                } catch (Exception e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("로그인실패 아이디 비밀번호 확인 바랍니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                }


            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }


    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }


}