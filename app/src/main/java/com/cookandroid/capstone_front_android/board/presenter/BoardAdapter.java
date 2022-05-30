package com.cookandroid.capstone_front_android.board.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.board.model.BoardResponse;

import java.util.List;

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.MyViewHolder> {

    private final Context context;
    private final List<BoardResponse> dataList;
    LayoutInflater layoutInflater;

    public BoardAdapter(Context context, List<BoardResponse> dataList){
        this.context = context;
        this.dataList= dataList;
    }

    @NonNull
    @Override
    public BoardAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = LayoutInflater.from(context).inflate(R.layout.item_board_list , parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BoardAdapter.MyViewHolder holder, int position) {
        holder.title.setText("제목: " + dataList.get(position).getTitle());
        holder.writer.setText("작성자: " + dataList.get(position).getWriterNickName());
        holder.viewCount.setText("조회수: " + dataList.get(position).getViewCount());
    }

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    // 게시글 상세 페이지 버튼
    public OnItemClickListener onItemClickListener;

    public interface OnItemClickListener {
        void onClick(View v, int position);
    }
    public void setItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }


    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView writer;
        TextView viewCount;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title = (TextView)itemView.findViewById(R.id.tv_item_board_list_title);
            writer = (TextView)itemView.findViewById(R.id.tv_item_board_list_writerNickname);
            viewCount = (TextView)itemView.findViewById(R.id.tv_item_board_list_viewCount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos = getAdapterPosition();
                    if (pos != RecyclerView.NO_POSITION) {
                        onItemClickListener.onClick(v, pos);
                    }
                }
            });
        }

    }

}
