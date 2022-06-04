package com.cookandroid.capstone_front_android.message.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.board.model.BoardApi;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.message.model.MessageApi;
import com.cookandroid.capstone_front_android.message.model.MessageRequest;
import com.cookandroid.capstone_front_android.message.model.MessageResponse;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Converter;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MessageWriteFragment extends Fragment {

    private View view;

    private MainActivity activity;

    private EditText receiver;
    private EditText title;
    private EditText content;

    private ImageView btnExit; // 나가기 버튼.
    private ImageView btnSent; // 보내기 버튼.
    private ImageView btnReceiverCheck;

    private final MessageApi messageApi = RetrofitClient.getClient(MessageApi.class, RetrofitClient.getSessionId());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.message_write,container,false);

        activity = (MainActivity) getActivity();

        receiver = (EditText) view.findViewById(R.id.messageReceiver);
        title = (EditText) view.findViewById(R.id.messageTitle);
        content = (EditText) view.findViewById(R.id.messageContent);
        btnExit = (ImageView) view.findViewById(R.id.exit);
        btnSent = (ImageView) view.findViewById(R.id.sent);
        btnReceiverCheck = (ImageView) view.findViewById(R.id.btn_receiverCheck);

        // 버튼 이벤트.
        btnExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setMessage(2); }
        });

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String messageReceiver = receiver.getText().toString();
                String messageTitle = title.getText().toString();
                String messageContent = content.getText().toString();
                startMessageWrite(new MessageRequest(messageReceiver, messageTitle, messageContent));
            }
        });

        btnReceiverCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                receiverCheck(receiver.getText().toString());
            }
        });

        return view;
    }

//    private void writeMessage(){
//        receiver.setError(null);
//        title.setError(null);
//        content.setError(null);
//
//        String messageReceiver = receiver.getText().toString();
//        String messageTitle = title.getText().toString();
//        String messageContent = content.getText().toString();
//
//        boolean cancel = false;
//        View focusView = null;
//
//        if (cancel) {
//            focusView.requestFocus();
//        } else {
//            startMessageWrite(new MessageRequest(messageReceiver, messageTitle, messageContent));
//        }
//    }

    private void startMessageWrite(MessageRequest data){

        messageApi.messageWrite(data).enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                new AlertDialog.Builder(getContext())
                        .setTitle("쪽지 보내기 확인")
                        .setMessage("쪽지를 보내시겠습니까?")
                        .setNegativeButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Toast.makeText(getActivity(), "쪽지를 보냈습니다.", Toast.LENGTH_SHORT).show();
                                activity.setMessage(2);
                            }
                        })
                        .setPositiveButton("아니요", null)
                        .create()
                        .show();
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Log.i(" 에러 발생", t.getMessage());
            }
        });
    }

    private void receiverCheck(String userId) {
        messageApi.getReceiver(userId).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(Call<MemberResponse> call, Response<MemberResponse> response) {
                new AlertDialog.Builder(getContext())
                        .setTitle("받는 사람 확인")
                        .setMessage(userId + "님에게 쪽지를 보냅니다.")
                        .setNegativeButton("예", null)
                        .setPositiveButton("아니요", null)
                        .create()
                        .show();
            }

            @Override
            public void onFailure(Call<MemberResponse> call, Throwable t) {
                Log.i(" 에러 발생", t.getMessage());
            }
        });
    }

}
