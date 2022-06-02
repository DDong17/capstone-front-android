package com.cookandroid.capstone_front_android.message.view;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.message.model.MessageApi;
import com.cookandroid.capstone_front_android.message.model.MessageListResponse;
import com.cookandroid.capstone_front_android.message.model.MessageResponse;
import com.cookandroid.capstone_front_android.message.presenter.MessageAdapter;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MessageReadId extends Fragment {

    private final MessageApi messageApi = RetrofitClient.getClient(MessageApi.class, RetrofitClient.getSessionId());

    private View view;
    private MainActivity activity;

    private Button btnReceived;
    private Button btnSent;
    private Button btnWrite;
    private Button btnDelete;

    MessageListResponse dataList;
    List<MessageResponse> dataInfo;
    RecyclerView recyclerView;
    MessageAdapter messageAdapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.message_read_id,container,false);
        activity = (MainActivity) getActivity();

        dataInfo = new ArrayList<>();
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        messageAdapter = new MessageAdapter(getContext(), dataInfo);
        recyclerView.setAdapter(messageAdapter);

        // 버튼 설정.
        btnReceived = view.findViewById(R.id.messageReceived);
        btnSent = view.findViewById(R.id.messageSent);
        btnWrite = view.findViewById(R.id.write);
        btnDelete = view.findViewById(R.id.deleteMessage);

        // 버튼 이벤트.
        btnReceived.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setMessage(2);
            }
        });
        btnSent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                activity.setMessage(3);
            }
        });
        btnWrite.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setMessage(1);
            }
        });
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext())
                        .setTitle("메시지삭제")
                        .setMessage("메시지를 삭제하시겠습니까?")
                        .setNegativeButton("예", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                deleteMessage();
                            }
                        })
                        .setPositiveButton("아니요", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {

                            }
                        });

                AlertDialog alertDialog = builder.create();
                alertDialog.show();
            }
        });

        start(0L);

        return view;
    }

    private void start(Long messageId) {
        /*Call<MessageListResponse> call = messageApi.getMessageId(messageId);
        call.enqueue(new Callback<MessageListResponse>() {
            @Override
            public void onResponse(Call<MessageListResponse> call, Response<MessageListResponse> response) {
                Log.i("결과", String.valueOf(response.code()));
                /*dataList = response.body();
                dataInfo = dataList.messages;
                start2(dataInfo);
                messageAdapter = new MessageAdapter(getContext(), dataInfo);
                recyclerView.setAdapter(messageAdapter);
                messageAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<MessageListResponse> call, Throwable t) {

            }

        });*/
    }

    /*private void start2(List<MessageResponse> dataInfo) {
        recyclerView = view.findViewById(R.id.recyclerView);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(layoutManager);
        messageAdapter = new MessageAdapter(getContext(), dataInfo);
        recyclerView.setAdapter(messageAdapter);
    }*/

    private void deleteMessage(){
        /*messageApi.deleteMessageId(0L).enqueue(new Callback<Void>() {
            @Override
            public void onResponse(Call<Void> call, Response<Void> response) {
                activity.setMessage(0);
            }

            @Override
            public void onFailure(Call<Void> call, Throwable t) {

            }
        });*/
    }
}
