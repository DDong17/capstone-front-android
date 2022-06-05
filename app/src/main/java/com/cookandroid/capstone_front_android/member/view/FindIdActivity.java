package com.cookandroid.capstone_front_android.member.view;

import android.content.Intent;
import android.os.Bundle;

import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.cookandroid.capstone_front_android.member.model.request.FindIdRequest;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.R;
//import com.cookandroid.capstone_front_android.util.data.Password_reset;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;
import com.cookandroid.capstone_front_android.member.model.MemberApi;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FindIdActivity extends AppCompatActivity {

    private EditText edtEmail;
    private EditText edtName;
    private TextView edtIdResult;

    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.find_id);
        edtEmail = (EditText) findViewById(R.id.findIdUser);
//        edtName = (EditText) findViewById(R.id.findIdName);
        Button btnFindId = (Button) findViewById(R.id.findIdBtn);
        Button btnBack = (Button) findViewById(R.id.findIdBack);
        edtIdResult = (TextView) findViewById(R.id.idResult);

        btnFindId.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = edtEmail.getText().toString();
                findId(new FindIdRequest(email));
            }
        });
        btnBack.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    private void findId(FindIdRequest data) {
        Log.e("호출", "호출");
        memberApi.postFindUserId(data).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(@NonNull Call<MemberResponse> call, @NonNull Response<MemberResponse> response) {
                MemberResponse result = response.body();
                Log.e("호출2", "호출2");
                edtIdResult.setText("찾아진 계정은 " + result.getUserId());
            }

            @Override
            public void onFailure(@NonNull Call<MemberResponse> call, Throwable t) {
                Log.e("로그인 에러 발생", t.getMessage());
            }
        });
    }
}