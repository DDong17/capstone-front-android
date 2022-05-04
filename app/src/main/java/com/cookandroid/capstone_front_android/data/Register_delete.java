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
//import com.cookandroid.capstone_front_android.R;
//import com.cookandroid.capstone_front_android.my_info;
//import com.cookandroid.capstone_front_android.network.RetrofitClient;
//import com.cookandroid.capstone_front_android.network.ServiceApi;
//
//import retrofit2.Call;
//import retrofit2.Callback;
//import retrofit2.Response;
//public class Register_delete extends AppCompatActivity{
//
//    private EditText mpassword;
//    private EditText mpasswordCheck;
//    private Button delete;
//    private Button back;
//
//    private AlertDialog dialog;
//
//    private ServiceApi service;
//
//    @Override
//    protected void onCreate(@Nullable Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.password_modify);
//
//        mpassword = (EditText) findViewById(R.id.register_delete_password);
//        mpasswordCheck = (EditText) findViewById(R.id.register_delete_passwordcheck);
//        delete = (Button) findViewById(R.id.password_delete_btn);
//        back = (Button) findViewById(R.id.register_delete_back);
//
//        service = RetrofitClient.getClient().create(ServiceApi.class);
//
//        delete.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Register_delete();
//            }
//        });
//
//        back.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent intent = new Intent(getApplicationContext(), my_info.class);
//                startActivity(intent);
//            }
//        });
//
//}
//    private void Register_delete () {
//        mpassword.setError(null);
//        mpasswordCheck.setError(null);
//
//
//        String password =  mpassword.getText().toString();
//        String passwordCheck =  mpasswordCheck.getText().toString();
//
//
//        boolean cancel = false;
//        View focusView = null;
//
//        // 패스워드의 유효성 검사
//        if (!isPasswordValid(password)) {
//            mpassword.setError("6자 이상의 비밀번호를 입력해주세요.");
//            focusView = mpassword;
//            cancel = true;
//        }
//        if (!isPasswordValid(passwordCheck)) {
//            mpasswordCheck.setError("6자 이상의 비밀번호를 입력해주세요.");
//            focusView = mpasswordCheck;
//            cancel = true;
//        }
//
//
//
//        if (cancel) {
//            focusView.requestFocus();
//        } else {
//            start_register_delete(new DeleteData(password,passwordCheck));
//
//        }
//    }
//    private void start_register_delete (DeleteData data){
//        service.userDelete(data).enqueue(new Callback<DeleteResponse>() {
//            @Override
//            public void onResponse(Call<DeleteResponse> call, Response<DeleteResponse> response) {
//                DeleteResponse result = response.body();
//                AlertDialog.Builder builder = new AlertDialog.Builder(DeleteData.this);
//                dialog = builder.setMessage("탈퇴성공.").setPositiveButton("확인", null).create();
//                dialog.show();
//
//
//            }
//
//            @Override
//            public void onFailure(Call<DeleteResponse> call, Throwable t) {
//                Toast.makeText(DelteData.this, " 에러 발생", Toast.LENGTH_SHORT).show();
//                Log.e(" 에러 발생", t.getMessage());
//
//            }
//        });
//    }
//
//    private boolean isPasswordValid (String mpassword){
//        return mpassword.length() >= 6;
//    }
//    private boolean isPasswordValid (String mpasswordCheck){
//        return mpasswordCheck.length() >= 6;
//    }
//}
