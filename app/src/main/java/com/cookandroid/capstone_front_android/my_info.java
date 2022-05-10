package com.cookandroid.capstone_front_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.member.view.PasswordModifyActivity;


public class my_info extends Fragment {

    private View view;
    private Button btnPasswordModify;
    private Button btnRegisterDelete;




    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.my_info,container,false);

        btnPasswordModify = (Button) view.findViewById(R.id.Passwordmodify_btn);
        btnRegisterDelete = (Button) view.findViewById(R.id.register_delete_btn);

        btnPasswordModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), PasswordModifyActivity.class);
                startActivity(intent);



            }
        });

        btnRegisterDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(getActivity(), Register_delete.class);
//                startActivity(intent);



            }
        });

        return view;
    }


}
