package com.cookandroid.capstone_front_android.categorymenu;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.location.model.LocationResponse;

import java.util.List;

public class LocationAdapter extends RecyclerView.Adapter<LocationAdapter.LocationViewHolder> {

    private List<LocationResponse> list;
    private LocationList locationList;

    public LocationAdapter(List<LocationResponse> list, LocationList locationList) {
        this.list = list;
        this.locationList = locationList;
    }

    @NonNull
    @Override
    public LocationViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new LocationViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.location_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull LocationViewHolder holder, int position) {
        LocationResponse location = list.get(position);
        Log.e("리스트에 추가", location.getTitle());
        holder.title.setText(location.getTitle());
        holder.content.setText(location.getAddress());
        if(location.getFirstImage() != null) Glide.with(locationList).load(location.getFirstImage()).into(holder.image);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LocationViewHolder extends RecyclerView.ViewHolder {

        private TextView title;
        private TextView content;
        private ImageView image;

        public LocationViewHolder(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.title);
            content = itemView.findViewById(R.id.content);
            image = itemView.findViewById(R.id.image);

            itemView.setOnClickListener(v -> {
                ((MainActivity) locationList.getActivity()).setLocation(list.get(getAdapterPosition()), locationList.getCategoryType(), locationList.getCategoryCode());
            });
        }
    }

}
