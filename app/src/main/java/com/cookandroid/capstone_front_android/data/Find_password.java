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

import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.JoinActivity;
import com.cookandroid.capstone_front_android.LoginActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.data.Find_password;
import com.cookandroid.capstone_front_android.data.LoginData;
//import com.cookandroid.capstone_front_android.data.Password_reset;
import com.cookandroid.capstone_front_android.data.MemberDTO;
import com.cookandroid.capstone_front_android.network.RetrofitClient;
import com.cookandroid.capstone_front_android.network.ServiceApi;



import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class Find_password extends AppCompatActivity {

    private EditText muserId;
    private EditText memail;
    private Button mfindpassword;
    private Button mback;
    private AlertDialog dialog;


    private ServiceApi service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_password);
        muserId = (EditText) findViewById(R.id.find_password_user);
        memail = (EditText) findViewById(R.id.find_password_email);
        mfindpassword = (Button) findViewById(R.id.find_password_btn);
        mback = (Button) findViewById(R.id.find_password_back);


        service = RetrofitClient.getClient().create(ServiceApi.class);

        mfindpassword.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                findpassword();
            }
        });
        mback.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });


    }

    private void findpassword() {
        muserId.setError(null);
        memail.setError(null);

        String userId = muserId.getText().toString();
        String email = memail.getText().toString();

        boolean cancel = false;
        View focusView = null;


        if (cancel) {
            focusView.requestFocus();
        } else {
            startfindpassword(new Find_password_data(userId, email));

        }
    }

    private void startfindpassword(Find_password_data data) {
        service.findpassword(data).enqueue(new Callback<MemberDTO>() {
            @Override
            public void onResponse(Call<MemberDTO> call, Response<MemberDTO> response) {
                AlertDialog.Builder builder = new AlertDialog.Builder(Find_password.this);
                dialog = builder.setMessage("임시 비밀번호 발급되었습니다.").setPositiveButton("확인", null).create();
                dialog.show();


            }


            //   }

            @Override
            public void onFailure(Call<MemberDTO> call, Throwable t) {

                Log.e("로그인 에러 발생", t.getMessage());

            }
        });
    }
}