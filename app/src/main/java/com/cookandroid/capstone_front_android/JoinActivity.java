package com.cookandroid.capstone_front_android;

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

import com.cookandroid.capstone_front_android.data.BooleanDto;
import com.cookandroid.capstone_front_android.data.ErrorDto;
import com.cookandroid.capstone_front_android.data.ID_check_Data;
import com.cookandroid.capstone_front_android.data.JoinData;
import com.cookandroid.capstone_front_android.data.MemberDTO;
import com.cookandroid.capstone_front_android.data.Nickname_check_Data;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.network.ServiceApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class JoinActivity<ApiService> extends AppCompatActivity {
    private EditText muserId;
    private EditText mpassword;
    private EditText mpasswordCheck;
    private EditText mname;
    private EditText memail;
    private EditText mphoneNumber;
    private EditText mnickname;
    private Button register;
    private Button back;
    private Button valid;
    private Button valid2;

    private AlertDialog dialog;

    private ServiceApi service;
    Retrofit retrofit;
    ServiceApi apiService;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        retrofit=new Retrofit.Builder().baseUrl(ServiceApi.BASE_URL).build();
        apiService =retrofit.create(ServiceApi.class);

        muserId = (EditText) findViewById(R.id.join_userId);
        mpassword = (EditText) findViewById(R.id.join_password);
        mpasswordCheck = (EditText) findViewById(R.id.join_passwordCheck);
        mname = (EditText) findViewById(R.id.join_name);
        memail = (EditText) findViewById(R.id.join_email);
        mphoneNumber = (EditText) findViewById(R.id.join_password);
        mnickname = (EditText) findViewById(R.id.join_nickname);
        register = (Button) findViewById(R.id.join_register);
        back = (Button) findViewById(R.id.back);
        valid = (Button) findViewById(R.id.Id_valid);
        valid2 = (Button) findViewById(R.id.Nickname_valid);





        service = RetrofitClient.getClient().create(ServiceApi.class);

        register.setOnClickListener(new View.OnClickListener() { //회원가입
            @Override
            public void onClick(View view) {
                attemptJoin();
//

            }

        });
        valid.setOnClickListener(new View.OnClickListener() { //회원 ID 중복확인
            @Override
            public void onClick(View view) {
                muserId.setError(null);
                String userId = muserId.getText().toString();
                Idcheck((userId));
            }
        });

        valid2.setOnClickListener(new View.OnClickListener() { //닉네임 중복확인
            @Override
            public void onClick(View view) {
                mnickname.setError(null);
                String nickname = mnickname.getText().toString();
                Nicknamecheck((nickname));
            }
        });

        back.setOnClickListener(new View.OnClickListener() { //뒤로가기
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });



    }



    private void attemptJoin() {
        muserId.setError(null);
        mpassword.setError(null);
        mpasswordCheck.setError(null);
        mname.setError(null);
        memail.setError(null);
        mphoneNumber.setError(null);
        mnickname.setError(null);


        String userId = muserId.getText().toString();
        String password = mpassword.getText().toString();
        String passwordCheck = mpasswordCheck.getText().toString();
        String name = mname.getText().toString();
        String email = memail.getText().toString();
        String phoneNumber = mphoneNumber.getText().toString();
        String nickname = mnickname.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (cancel) {
            focusView.requestFocus();
        } else {
            startJoin(new JoinData(userId,password,passwordCheck,name,email,phoneNumber,nickname));

        }
    }

    private void startJoin(JoinData data) {
        service.userJoin(data).enqueue(new Callback<MemberDTO>() {
            @Override
            public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                dialog = builder.setMessage("회원가입성공.").setPositiveButton("확인", null).create();
                dialog.show();




                Intent intent = new Intent(getApplicationContext(), JoinActivity.class);

            }

            @Override
            public void onFailure(Call<MemberDTO> call, Throwable t) {
                Toast.makeText(JoinActivity.this, "회원가입 에러 발생", Toast.LENGTH_SHORT).show();
                Log.e("회원가입 에러 발생", t.getMessage());

            }
        });
    }



    private void Idcheck(String userId) {//아이디 중복확인
        service.Check_ID(userId).enqueue(new Callback<BooleanDto>() {
                @Override
                public void onResponse(Call<BooleanDto> call, Response<BooleanDto> response) {
                    BooleanDto result = response.body();



                    if(result.getresult()==true){

                        AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                        dialog=builder.setMessage("이미 존재하는 아이디 입니다.").setPositiveButton("확인",null).create();
                        dialog.show();
                    }else {
                        AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                        dialog=builder.setMessage("사용할 수 있습니다.").setPositiveButton("확인",null).create();
                        dialog.show();
                    }





                }

                @Override
                public void onFailure(Call<BooleanDto> call, Throwable t) {
                    Toast.makeText(JoinActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
                    Log.e("에러 발생", t.getMessage());

                }
            });

    }



    private void Nicknamecheck(String nickname) {
     service.Check_nickname(nickname).enqueue(new Callback<BooleanDto>() {
        @Override
        public void onResponse(Call<BooleanDto> call, Response<BooleanDto> response) {
            BooleanDto result = response.body();



            if(result.getresult()==true){

                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                dialog=builder.setMessage("이미 존재하는 닉네임 입니다.").setPositiveButton("확인",null).create();
                dialog.show();
            }else {

                AlertDialog.Builder builder = new AlertDialog.Builder(JoinActivity.this);
                dialog=builder.setMessage("사용 할수있는 닉네임 입니다.").setPositiveButton("확인",null).create();
                dialog.show();
            }

        }

        @Override
        public void onFailure(Call<BooleanDto> call, Throwable t) {
            Toast.makeText(JoinActivity.this, "에러 발생", Toast.LENGTH_SHORT).show();
            Log.e("에러 발생", t.getMessage());

        }
    });

}




}




