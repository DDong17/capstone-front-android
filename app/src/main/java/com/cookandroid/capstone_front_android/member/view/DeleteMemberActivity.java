package com.cookandroid.capstone_front_android.member.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.request.DeleteMemberRequest;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.profile.view.MyInfoFragment;
import com.cookandroid.capstone_front_android.util.model.BooleanDTO;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
import com.cookandroid.capstone_front_android.member.model.MemberApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DeleteMemberActivity extends AppCompatActivity {

    private EditText editPassword;
    private EditText editPasswordCheck;

    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class, RetrofitClient.getSessionId());

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_delete);

        editPassword = (EditText) findViewById(R.id.btnDeletePassword);
        editPasswordCheck = (EditText) findViewById(R.id.btnDeletePasswordCheck);
        Button btnDeleteMember = (Button) findViewById(R.id.btnDeleteRegister);
        Button btnBack = (Button) findViewById(R.id.btnBack);

        btnDeleteMember.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                validatePassword();
            }
        });

        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyInfoFragment.class);
                startActivity(intent);
            }
        });
    }

    private void validatePassword() {
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
            new AlertDialog.Builder(DeleteMemberActivity.this)
                    .setMessage("회원 탈퇴를 하시겠습니까?").setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteMemberRequestToServer(new DeleteMemberRequest(password, passwordCheck));
                }
            })
                    .create()
                    .show();
        }
    }

    private void deleteMemberRequestToServer(DeleteMemberRequest data) {

        memberApi.deleteMember(data).enqueue(new Callback<BooleanDTO>() {
            @Override
            public void onResponse(@NonNull Call<BooleanDTO> call, @NonNull Response<BooleanDTO> response) {
                Toast.makeText(DeleteMemberActivity.this, "회원 탈퇴가 완료되었습니다.", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }

            @Override
            public void onFailure(@NonNull Call<BooleanDTO> call, @NonNull Throwable t) {
                Toast.makeText(DeleteMemberActivity.this, " 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e(" 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isPasswordValid(String editPassword) {
        return editPassword.length() < 6;
    }
}


