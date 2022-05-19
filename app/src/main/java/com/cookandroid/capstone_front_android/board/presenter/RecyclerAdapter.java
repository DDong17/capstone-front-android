package com.cookandroid.capstone_front_android.board.presenter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.board.model.BoardResponse;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ItemViewHolder> {
    private List<BoardResponse> item;
    private Context context;
    LayoutInflater layoutInflater;

    public RecyclerAdapter(Context context, List<BoardResponse> item){
        this.context = context;
        this.item = item;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.title.setText(item.get(position).getTitle());
        holder.content.setText(item.get(position).getContent());
    }


    @Override
    public int getItemCount() {
            return item.size();
    }

    class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView title;
        private TextView content;

        ItemViewHolder(View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int pos = getAdapterPosition();
                    Toast.makeText(view.getContext(), item.get(pos).getTitle(), Toast.LENGTH_SHORT).show();
                }
            });
        }

        /*void onBind(BoardResponse item) {
            title.setText(item.getTitle());
            content.setText(item.getContent());
        }*/

        public void setItem(BoardResponse board, ItemViewHolder holder) {
            title.setText(board.getTitle());
            content.setText(board.getContent());
        }
    }

}
