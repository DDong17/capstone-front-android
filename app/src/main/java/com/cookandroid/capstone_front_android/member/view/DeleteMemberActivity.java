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
import com.cookandroid.capstone_front_android.member.model.request.DeleteMemberRequest;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.myInfo;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.member.model.MemberApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteMemberActivity extends AppCompatActivity {

    private EditText editPassword;
    private EditText editPasswordCheck;
    private Button btnDeleteRegister;
    private Button btnBack;

    private AlertDialog dialog;

    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class, RetrofitClient.getSessionId());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_delete);

        editPassword = (EditText) findViewById(R.id.btnDeletePassword);
        editPasswordCheck = (EditText) findViewById(R.id.btnDeletePasswordCheck);
        btnDeleteRegister = (Button) findViewById(R.id.btnDeleteRegister);
        btnBack = (Button) findViewById(R.id.btnBack);

        btnDeleteRegister.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                deleteMember() ;
            }
        });

        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), myInfo.class);
                startActivity(intent);
            }
        });
    }

    private void deleteMember() {
        editPassword.setError(null);
        editPasswordCheck.setError(null);

        String password = editPassword.getText().toString();
        String passwordCheck = editPasswordCheck.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (isPasswordValid(password)) {
            editPassword.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = editPassword;
            cancel = true;
        }
        if (isPasswordValid(passwordCheck)) {
            editPasswordCheck.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = editPasswordCheck;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            startDeleteMember(new DeleteMemberRequest(password,passwordCheck));
        }
    }

    private void startDeleteMember(DeleteMemberRequest data) {

        memberApi.deleteMember(data).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteMemberActivity.this);
                dialog = builder.setMessage("탈퇴성공.").setPositiveButton("확인", null).create();
                dialog.show();
                Intent intent = new Intent(getApplicationContext(), DeleteMemberRequest.class);
                startActivity(intent);
            }
            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                Toast.makeText(DeleteMemberActivity.this, " 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e(" 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isPasswordValid(String editPassword) {
        return editPassword.length() < 6;
    }
}


