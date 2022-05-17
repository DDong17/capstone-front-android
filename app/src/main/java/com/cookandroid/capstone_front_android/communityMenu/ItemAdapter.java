package com.cookandroid.capstone_front_android.communityMenu;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cookandroid.capstone_front_android.R;

import java.util.ArrayList;

public class ItemAdapter extends BaseAdapter {
    private TextView title;
    private TextView content;
    public ArrayList<ItemDTO> item_list = new ArrayList<>();


    public ItemAdapter() {
    }

    @Override
    public int getCount() { // 리스트 안 item 개수.
        return item_list.size();
    }

    @Override
    public Object getItem(int i) { // 위치에 있는 데이터.
        return item_list.get(i);
    }

    @Override
    public long getItemId(int i) { // id리턴.
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final int pos = i;
        final Context context = viewGroup.getContext();

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item, viewGroup, false);
        }

        title = (TextView) view.findViewById(R.id.title);
        content = (TextView) view.findViewById(R.id.content);

        ItemDTO item = item_list.get(i);

        title.setText(item.getTitle());
        content.setText(item.getContent());

        LinearLayout layout = (LinearLayout) view.findViewById(R.id.layout);
        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), item_list.get(pos).getContent(), Toast.LENGTH_SHORT).show();
            }
        });

        /*title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(), item_list.get(pos).getContent(), Toast.LENGTH_SHORT).show();
            }
        });*/


        return view;
    }

    public void addItem(String title, String content) {
        ItemDTO item = new ItemDTO();
        item.setTitle(title);
        item.setContent(content);

        item_list.add(item);
    }

}
