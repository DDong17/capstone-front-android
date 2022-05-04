//package com.cookandroid.capstone_front_android.data;
//
//import android.app.AlertDialog;
//import android.content.Intent;
//import android.os.Bundle;
//import android.util.Log;
//import android.view.View;
//import android.view.View.OnClickListener;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import androidx.annotation.Nullable;
//import androidx.appcompat.app.AppCompatActivity;
//
//import com.cookandroid.capstone_front_android.LoginActivity;
//import com.cookandroid.capstone_front_android.R;
//import com.cookandroid.capstone_front_android.my_info;
//import com.cookandroid.capstone_front_android.network.RetrofitClient;
//import com.cookandroid.capstone_front_android.network.ServiceApi;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//
//public class Password_reset extends AppCompatActivity {
//
//    private EditText muserId;
//    private EditText memail;
//    private Button mpasswordreset;
//    private Button back;
//
//    private AlertDialog dialog;
//
//    private ServiceApi service;
//
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.password_reset);
//
//        muserId = (EditText) findViewById(R.id.password_reset_id);
//        memail = (EditText) findViewById(R.id.password_reset_email);
//        mpasswordreset = (Button) findViewById(R.id.password_reset_btn);
//        back = (Button) findViewById(R.id.register_reset_back);
//
//        service = RetrofitClient.getClient().create(ServiceApi.class);
//
//        mpasswordreset.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                password_reset();
//            }
//        });
//
//        back.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
//                startActivity(intent);
//            }
//        });
//    }
//    private void password_reset () {
//        muserId.setError(null);
//        memail.setError(null);
//
//
//        String userId =  muserId.getText().toString();
//        String email =  memail.getText().toString();
//
//        boolean cancel = false;
//        View focusView = null;
//
//
//
//
//        if (cancel) {
//            focusView.requestFocus();
//        } else {
//            start_password_reset(new FindpasswordData(userId,email));
//        }
//    }
//    private void start_password_reset (Password_modify_data data){
//        service.findpassword(data).enqueue(new Callback<FindpasswordResponse>() {
//            @Override
//            public void onResponse(Call<FindpasswordResponse> call, Response<FindpasswordResponse> response) {
//                FindpasswordResponse result = response.body();
//                AlertDialog.Builder builder = new AlertDialog.Builder(findpassword.this);
//                dialog = builder.setMessage("변경성공.").setPositiveButton("확인", null).create();
//                dialog.show();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<FindpasswordResponse> call, Throwable t) {
//                Toast.makeText(findpassword.this, " 에러 발생", Toast.LENGTH_SHORT).show();
//                Log.e(" 에러 발생", t.getMessage());
//
//            }
//        });
//    }
//
//    private boolean isPasswordValid (String mnewPassword){
//        return mnewPassword.length() >= 6;
//    }
//}
//
//
