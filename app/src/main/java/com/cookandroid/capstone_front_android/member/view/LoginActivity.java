package com.cookandroid.capstone_front_android.member.view;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.CookieManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.request.LoginRequest;
//import com.cookandroid.capstone_front_android.util.data.Password_reset;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
import com.cookandroid.capstone_front_android.member.model.MemberApi;
import com.google.android.gms.common.util.SharedPreferencesUtils;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText edtUserId;
    private EditText edtPassword;
    private AlertDialog dialog;
    private SharedPreferencesUtils pref;
    private SharedPreferences.Editor editor;

    // 로그인할때는 세션값을 생성하는 단계이므로, 레트로핏 클라이언트를 생성할때 세션값을 가져올 필요가 없음
    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUserId = (EditText) findViewById(R.id.loginUserId);
        edtPassword = (EditText) findViewById(R.id.loginPassword);
        Button btnLogin = (Button) findViewById(R.id.loginButton);
        Button btnRegister = (Button) findViewById(R.id.registerButton);
        Button btnIdPassword = (Button) findViewById(R.id.idFindBtn);
        Button btnFindPassword = (Button) findViewById((R.id.passwordFindBtn));

        btnLogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                validateLoginForm();
            }
        });
        btnRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });
        btnIdPassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), FindIdActivity.class);
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

    private void validateLoginForm() {
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
        } else if (isPasswordValid(password)) {
            edtUserId.setError("아이디는 4~10글자 사이로 입력해주세요");
            focusView = edtUserId;
            cancel = true;
        }

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            edtPassword.setError("비밀번호를 입력해주세요.");
            focusView = edtPassword;
            cancel = true;
        } else if (isPasswordValid(password)) {
            edtPassword.setError("비밀번호는 6~10글자 사이로 입력해주세요");
            focusView = edtPassword;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            login(new LoginRequest(userId, password));

        }
    }

    private void login(LoginRequest data) {
        memberApi.postLogin(data).enqueue(new Callback<MemberResponse>() {
            @SuppressLint("LongLogTag")
            @Override
            public void onResponse(@NonNull Call<MemberResponse> call, @NonNull Response<MemberResponse> response) {
                // 로그인 실패시 NPE 를 던짐
                try {
                    MemberResponse result = response.body();

                    // 싱글톤으로 관리되는 쿠키매니저 객체 가져오기
                    CookieManager cookieManager = CookieManager.getInstance();

                    // 캐쉬되어있던 정보 모두 날리기
                    cookieManager.removeAllCookie();

                    // 프로필을 그리기위한 회원 PK를 저장함
                    Context mContext = getApplicationContext();
                    SharedPreferences pref = mContext.getSharedPreferences("pref", MODE_PRIVATE);
                    SharedPreferences.Editor editor = pref.edit();
                    editor.putLong("memberId", result.getMemberId());
                    editor.commit();

                    // 쿠키매니저에 쿠키 저장 키 : BASE_URL - 값 : 서버로부터 전송받은 세션값
                    cookieManager.setCookie(RetrofitClient.BASE_URL, result.getSessionId());
                    Log.d("sessionId From Server", result.getSessionId());
                    Log.d("sessionId From CookieManager", cookieManager.getCookie(RetrofitClient.BASE_URL));
                    Toast.makeText(LoginActivity.this, result.getName() + "님 환영합니다 :)", Toast.LENGTH_SHORT).show();

                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                } catch (NullPointerException e) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginActivity.this);
                    dialog = builder.setMessage("로그인실패 아이디 비밀번호 확인 바랍니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MemberResponse> call, @NonNull Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }


    private boolean isPasswordValid(String password) {
        return password.length() < 6;
    }


}