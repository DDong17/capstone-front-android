package com.cookandroid.capstone_front_android.data;

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
import com.cookandroid.capstone_front_android.my_info;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.network.ServiceApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Password_modify extends AppCompatActivity {

    private EditText mcurrentPassword;
    private EditText mnewPassword;
    private EditText mnewPasswordCheck;
    private Button register;
    private Button back;

    private AlertDialog dialog;

    private ServiceApi service;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_modify);

        mcurrentPassword = (EditText) findViewById(R.id.currentPassword_btn);
        mnewPassword = (EditText) findViewById(R.id.newPassword_btn);
        mnewPasswordCheck = (EditText) findViewById(R.id.newPasswordCheck_btn);
        register = (Button) findViewById(R.id.Changepassword_btn);
        back = (Button) findViewById(R.id.Changepassword_back);

        service = RetrofitClient.getClient().create(ServiceApi.class);

        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Change_password();
            }
        });

        back.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), my_info.class);
                startActivity(intent);
            }
        });
    }
        private void Change_password () {
            mcurrentPassword.setError(null);
            mnewPassword.setError(null);
            mnewPasswordCheck.setError(null);

            String currentPassword = mcurrentPassword.getText().toString();
            String newPassword = mnewPassword.getText().toString();
            String newPasswordCheck = mnewPasswordCheck.getText().toString();

            boolean cancel = false;
            View focusView = null;

            // 패스워드의 유효성 검사
            if (!isPasswordValid(currentPassword)) {
                mcurrentPassword.setError("6자 이상의 비밀번호를 입력해주세요.");
                focusView = mcurrentPassword;
                cancel = true;
            }
            if (!isPasswordValid(newPassword)) {
                mnewPassword.setError("6자 이상의 비밀번호를 입력해주세요.");
                focusView = mnewPassword;
                cancel = true;
            }
            if (!isPasswordValid(newPasswordCheck)) {
                mnewPasswordCheck.setError("6자 이상의 비밀번호를 입력해주세요.");
                focusView = mnewPasswordCheck;
                cancel = true;
            }


            if (cancel) {
                focusView.requestFocus();
            } else {
                start_password_modify(new Password_modify_data(currentPassword, newPassword, newPasswordCheck));

            }
        }
        private void start_password_modify (Password_modify_data data){
            service.passwordmodify(data).enqueue(new Callback<MemberDTO>() {
                @Override
                public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                    MemberDTO result = response.body();
                    AlertDialog.Builder builder = new AlertDialog.Builder(Password_modify.this);
                    dialog = builder.setMessage("변경성공.").setPositiveButton("확인", null).create();
                    dialog.show();


                }

                @Override
                public void onFailure(Call<MemberDTO> call, Throwable t) {
                    Toast.makeText(Password_modify.this, " 에러 발생", Toast.LENGTH_SHORT).show();
                    Log.e(" 에러 발생", t.getMessage());

                }
            });
        }

        private boolean isPasswordValid (String mnewPassword){
            return mnewPassword.length() >= 6;
        }
    }


