package com.cookandroid.capstone_front_android.member.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.request.ChangePasswordRequest;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.profile.view.MyInfoFragment;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
import com.cookandroid.capstone_front_android.member.model.MemberApi;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordActivity extends AppCompatActivity {

    private EditText edtCurrentPassword;
    private EditText edtNewPassword;
    private EditText edtNewPasswordCheck;

    /* 
    로그인 정보가 필요한 액티비티에서는 쿠키매니저에서 가져온 세션아이디를 필수적으로 넘겨줘야
    하기때문에 세션 정보를 가져와서 넣어주는 작업을 해줘야함
     */
    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class, RetrofitClient.getSessionId());


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_modify);
        edtCurrentPassword = (EditText) findViewById(R.id.currentPasswordBtn);
        edtNewPassword = (EditText) findViewById(R.id.newPasswordBtn);
        edtNewPasswordCheck = (EditText) findViewById(R.id.newPasswordCheckBtn);

        // 비밀번호 변경 버튼
        findViewById(R.id.ChangepasswordBtn).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                ChangePasswordRequest requestData = validatePasswordForm();
                changePasswordRequestToServer(requestData);
                Intent intent = new Intent(getApplicationContext(), MyInfoFragment.class);
                startActivity(intent);
            }
        });

        // 뒤로가기 버튼
        findViewById(R.id.ChangepasswordBack).setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyInfoFragment.class);
                startActivity(intent);
            }
        });
    }

    private ChangePasswordRequest validatePasswordForm() {
        edtCurrentPassword.setError(null);
        edtNewPassword.setError(null);
        edtNewPasswordCheck.setError(null);

        String currentPassword = edtCurrentPassword.getText().toString();
        String newPassword = edtNewPassword.getText().toString();
        String newPasswordCheck = edtNewPasswordCheck.getText().toString();

        boolean cancel = false;
        View focusView = null;

        // 패스워드의 유효성 검사
        if (isPasswordValid(currentPassword)) {
            edtCurrentPassword.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = edtCurrentPassword;
            cancel = true;
        }
        if (isPasswordValid(newPassword)) {
            edtNewPassword.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = edtNewPassword;
            cancel = true;
        }
        if (isPasswordValid(newPasswordCheck)) {
            edtNewPasswordCheck.setError("6자 이상의 비밀번호를 입력해주세요.");
            focusView = edtNewPasswordCheck;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            return new ChangePasswordRequest(currentPassword, newPassword, newPasswordCheck);
        }
        return null;
    }

    private void changePasswordRequestToServer(ChangePasswordRequest data) {
        memberApi.putUpdatePassword(data).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(@NonNull Call<MemberResponse> call, @NonNull Response<MemberResponse> response) {
//                AlertDialog.Builder builder = new AlertDialog.Builder(ChangePasswordActivity.this);
//                dialog = builder.setMessage("변경성공.").setPositiveButton("확인", null).create();
//                dialog.show();
                Toast.makeText(ChangePasswordActivity.this, "비밀번호 변경 완료", Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onFailure(@NonNull Call<MemberResponse> call, @NonNull Throwable t) {
                Log.e(" 에러 발생", t.getMessage());
            }
        });
    }

    private boolean isPasswordValid(String edtNewPassword) {
        return edtNewPassword.length() < 6;
    }
}


