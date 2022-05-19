package com.cookandroid.capstone_front_android.member.view;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.profile.view.MyInfoFragment;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
import com.cookandroid.capstone_front_android.member.model.MemberApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterNewNIcknameActivity extends AppCompatActivity {
    private EditText edtNewNickname;
    private Button btnRegisterNewNickname;
    private Button btnBack;

    private AlertDialog dialog;

    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class, RetrofitClient.getSessionId());

    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_newnickname);

        edtNewNickname = (EditText) findViewById(R.id.registerNewNinckname);
        btnRegisterNewNickname = (Button) findViewById(R.id.btnNicknameRegister);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnRegisterNewNickname.setOnClickListener(new View.OnClickListener() { //닉네임 중복확인
            @Override
            public void onClick(View view) {
                edtNewNickname.setError(null);
                String newNickname = edtNewNickname.getText().toString();
                newNickNameCheck((newNickname));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() { //뒤로가기
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyInfoFragment.class);
                startActivity(intent);
            }
        });
    }

    private void newNickNameCheck(String newNickname) {
        memberApi.registerNewNickname(newNickname).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                try {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterNewNIcknameActivity.this);
                    dialog = builder.setMessage("변경완료").setPositiveButton("확인", null).create();
                    dialog.show();
                    Intent intent = new Intent(getApplicationContext(), RegisterNewNIcknameActivity.class);
                    startActivity(intent);
                }catch (Exception e){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterNewNIcknameActivity.this);
                    dialog = builder.setMessage("중복된 닉네임").setPositiveButton("확인", null).create();
                    dialog.show();
                }


            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                Toast.makeText(RegisterNewNIcknameActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
            }


        });
    }
}
