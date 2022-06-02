package com.cookandroid.capstone_front_android.message.view;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.board.model.BoardApi;
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

public class MessageWrite extends Fragment {

    private View view;

    private MainActivity activity;

    private EditText receiver;
    private EditText title;
    private EditText content;

    private Button btnExit; // 나가기 버튼.
    private Button btnSent; // 보내기 버튼.

    private final MessageApi messageApi = RetrofitClient.getClient(MessageApi.class, RetrofitClient.getSessionId());

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.message_write,container,false);

        activity = (MainActivity) getActivity();

        receiver = (EditText) view.findViewById(R.id.messageReceiver);
        title = (EditText) view.findViewById(R.id.messageTitle);
        content = (EditText) view.findViewById(R.id.messageContent);
        btnExit = (Button) view.findViewById(R.id.exit);
        btnSent = (Button) view.findViewById(R.id.sent);

        // 버튼 이벤트.
        btnExit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) { activity.setMessage(0); }
        });

        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                writeMessage();
            }
        });

        return view;
    }

    private void writeMessage(){
        receiver.setError(null);
        title.setError(null);
        content.setError(null);

        String messageReceiver = receiver.getText().toString();
        String messageTitle = title.getText().toString();
        String messageContent = content.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (cancel) {
            focusView.requestFocus();
        } else {
            startMessageWrite(new MessageRequest(messageReceiver, messageTitle, messageContent));
        }
    }

    private void startMessageWrite(MessageRequest data){
        /*Log.i("밖", data.getReceiverNickname());
        Log.i("밖", data.getTitle());
        Log.i("밖", data.getContent());

        messageApi.messageWrite(data).enqueue(new Callback<MessageResponse>() {
            @Override
            public void onResponse(Call<MessageResponse> call, Response<MessageResponse> response) {
                Log.i("실행 결과", String.valueOf(response.code()));
                Log.i("실행 결과", String.valueOf(response.body()));
                try {
                    JSONObject jsonObject = new JSONObject(response.errorBody().string());
                    Log.i("실행 결과", jsonObject.getJSONObject("error").getString("message"));
                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<MessageResponse> call, Throwable t) {
                Log.i(" 에러 발생", t.getMessage());
            }
        });*/
    }

}
