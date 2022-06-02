package com.cookandroid.capstone_front_android.message.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.message.model.MessageApi;
import com.cookandroid.capstone_front_android.message.model.MessageResponse;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MyViewHolder> {

    private final MessageApi messageApi = RetrofitClient.getClient(MessageApi.class, RetrofitClient.getSessionId());

    private final Context context;
    private final List<MessageResponse> dataList;
    LayoutInflater layoutInflater;

    public MessageAdapter(Context context, List<MessageResponse> dataList){
        this.context = context;
        this.dataList= dataList;
    }

    @NonNull
    @Override
    public MessageAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(context).inflate(R.layout.item_message_list, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MessageAdapter.MyViewHolder holder, int position) {
        holder.title.setText("메시지 제목: " + dataList.get(position).getTitle());
        holder.writer.setText("보낸 사람: " + dataList.get(position).getSender());
        holder.content.setText("메시지 내용: " + dataList.get(position).getContent());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView writer;
        TextView content;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            title = (TextView)itemView.findViewById(R.id.message_title);
            writer = (TextView)itemView.findViewById(R.id.message_nickname);
            content = (TextView)itemView.findViewById(R.id.message_content);
        }

    }

}
