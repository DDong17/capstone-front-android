package com.cookandroid.capstone_front_android;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.data.LoginData;
//import com.cookandroid.capstone_front_android.data.Password_reset;
import com.cookandroid.capstone_front_android.data.MemberDTO;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.network.ServiceApi;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    private EditText muserId;
    private EditText mpassword;
    private Button mlogin;
    private Button mregister;
    private Button mpasswordreset;

    private ServiceApi service;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        muserId = (EditText) findViewById(R.id.login_userId);
        mpassword = (EditText) findViewById(R.id.login_password);
        mlogin = (Button) findViewById(R.id.login_button);
        mregister = (Button) findViewById(R.id.register_button);
        mpasswordreset=(Button) findViewById((R.id.password_find_btn));


        service = RetrofitClient.getClient().create(ServiceApi.class);

        mlogin.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
            }
        });
        mregister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);
                startActivity(intent);
            }
        });
        mpasswordreset.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getApplicationContext(), Password_reset.class);
//                startActivity(intent);
            }
        });


    }

    private void attemptLogin() {
        muserId.setError(null);
        mpassword.setError(null);

        String userId = muserId.getText().toString();
        String password = mpassword.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            muserId.setError("비밀번호를 입력해주세요.");
            focusView = muserId;
            cancel = true;
        } else if (!isPasswordValid(password)) {
            mpassword.setError("비밀번호는 6~10글자 사이로 입력해주세요");
            focusView = mpassword;
            cancel = true;
        }



        if (cancel) {
            focusView.requestFocus();
        } else {
            startLogin(new LoginData(userId, password));

        }
    }

    private void startLogin(LoginData data) {
        service.userLogin(data).enqueue(new Callback<MemberDTO>() {
            @Override
            public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                MemberDTO result = response.body();

                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);

            }


            //   }

            @Override
            public void onFailure(Call<MemberDTO> call, Throwable t) {
                Toast.makeText(LoginActivity.this, "로그인 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }



    private boolean isPasswordValid(String password) {
        return password.length() >= 6;
    }


}