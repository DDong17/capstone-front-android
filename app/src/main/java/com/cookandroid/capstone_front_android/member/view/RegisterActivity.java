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
import com.cookandroid.capstone_front_android.data.BooleanDTO;
import com.cookandroid.capstone_front_android.member.model.request.JoinRequest;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.member.model.MemberApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {
    private EditText edtUserId;
    private EditText edtPassword;
    private EditText edtPasswordCheck;
    private EditText edtName;
    private EditText edtEmail;
    private EditText edtPhoneNumber;
    private EditText edtNickname;
    private Button btnRegister;
    private Button btnBack;
    private Button btnValid;
    private Button btnValid2;

    private AlertDialog dialog;

    // 서버 통신을 위한 레트로핏 객체 가져오기
    // 통신에 필요한 api 이 매핑된 인터페이스를 파라미터로 넘겨줘서 생성
    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edtUserId = (EditText) findViewById(R.id.joinUserId);
        edtPassword = (EditText) findViewById(R.id.joinPassword);
        edtPasswordCheck = (EditText) findViewById(R.id.joinPasswordCheck);
        edtName = (EditText) findViewById(R.id.joinName);
        edtEmail = (EditText) findViewById(R.id.joinEmail);
        edtPhoneNumber = (EditText) findViewById(R.id.joinPassword);
        edtNickname = (EditText) findViewById(R.id.joinNickname);
        btnRegister = (Button) findViewById(R.id.joinRegister);
        btnBack = (Button) findViewById(R.id.back);
        btnValid = (Button) findViewById(R.id.IdValid);
        btnValid2 = (Button) findViewById(R.id.NicknameValid);


//        memberApi = RetrofitClient.getClient().create(MemberApi.class);

        btnRegister.setOnClickListener(new View.OnClickListener() { //회원가입
            @Override
            public void onClick(View view) {
                attemptJoin();
//
            }
        });
        btnValid.setOnClickListener(new View.OnClickListener() { //회원 ID 중복확인
            @Override
            public void onClick(View view) {
                edtUserId.setError(null);
                String userId = edtUserId.getText().toString();
                idCheck((userId));
            }
        });
        btnValid2.setOnClickListener(new View.OnClickListener() { //닉네임 중복확인
            @Override
            public void onClick(View view) {
                edtNickname.setError(null);
                String nickname = edtNickname.getText().toString();
                nickNameCheck((nickname));
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() { //뒤로가기
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void attemptJoin() {
        edtUserId.setError(null);
        edtPassword.setError(null);
        edtPasswordCheck.setError(null);
        edtName.setError(null);
        edtEmail.setError(null);
        edtPhoneNumber.setError(null);
        edtNickname.setError(null);

        String userId = edtUserId.getText().toString();
        String password = edtPassword.getText().toString();
        String passwordCheck = edtPasswordCheck.getText().toString();
        String name = edtName.getText().toString();
        String email = edtEmail.getText().toString();
        String phoneNumber = edtPhoneNumber.getText().toString();
        String nickname = edtNickname.getText().toString();

        boolean cancel = false;
        View focusView = null;

        //아이디 유효성 검사
        if (userId.isEmpty()) {
            edtUserId.setError("아이디를 입력해주세요");
            focusView = edtUserId;
            cancel = true;
        } else if (!idValid(userId)) {
            edtUserId.setError("아이디는 4~10글자 사이로 입력해주세요");
            focusView = edtUserId;
            cancel = true;
        }
        // 패스워드의 유효성 검사
        if (password.isEmpty()) {
            edtPassword.setError("비밀번호를 입력해주세요.");
            focusView = edtPassword;
            cancel = true;
        } else if (!passwordValid(password)) {
            edtPassword.setError("비밀번호는 6~10글자 사이로 입력해주세요");
            focusView = edtPassword;
            cancel = true;
        }
        // 닉네임 유효성
        if (nickname.isEmpty()) {
            edtNickname.setError("닉네임을 입력해주세요");
            focusView = edtNickname;
            cancel = true;
        } else if (!nicknameValid(nickname)) {
            edtNickname.setError("닉네임은 2~6글자로 입력해주세요");
            focusView = edtNickname;
            cancel = true;
        }
        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinRequest(userId, password, passwordCheck, name, email, phoneNumber, nickname));
        }
    }

    private void startJoin(JoinRequest data) {
        memberApi.userJoin(data).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                dialog = builder.setMessage("회원가입성공.").setPositiveButton("확인", null).create();
                dialog.show();
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
            }
            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());
            }
        });
    }


    private void idCheck(String userId) {//아이디 중복확인
        memberApi.checkID(userId).enqueue(new Callback<BooleanDTO>() {
            @Override
            public void onResponse(Call<BooleanDTO> call, Response<BooleanDTO> response) {
                BooleanDTO result = response.body();
                if (result.getresult() == true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("이미 존재하는 아이디 입니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("사용할 수 있습니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                }
            }
            @Override
            public void onFailure(Call<BooleanDTO> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
            }
        });
    }

    private void nickNameCheck(String nickname) {
        memberApi.checkNickname(nickname).enqueue(new Callback<BooleanDTO>() {
            @Override
            public void onResponse(Call<BooleanDTO> call, Response<BooleanDTO> response) {
                BooleanDTO result = response.body();
                if (result.getresult() == true) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("이미 존재하는 닉네임 입니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(RegisterActivity.this);
                    dialog = builder.setMessage("사용 할수있는 닉네임 입니다.").setPositiveButton("확인", null).create();
                    dialog.show();
                }
            }
            @Override
            public void onFailure(Call<BooleanDTO> call, Throwable t) {
                Toast.makeText(RegisterActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("에러 발생", t.getMessage());
            }
        });
    }
    private boolean idValid(String userId) {
        return userId.length() <= 10;
    }
    private boolean passwordValid(String password) {
        return password.length() >= 6;
    }
    private boolean nicknameValid(String nickname) {
        return nickname.length() <= 6;
    }


}




