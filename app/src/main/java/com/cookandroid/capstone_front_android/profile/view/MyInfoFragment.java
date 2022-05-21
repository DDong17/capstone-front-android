package com.cookandroid.capstone_front_android.profile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.view.ChangePasswordActivity;
import com.cookandroid.capstone_front_android.member.view.DeleteMemberActivity;
import com.cookandroid.capstone_front_android.member.view.UpdateNicknameActivity;


public class MyInfoFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_info, container, false);

        Button btnPasswordModify = (Button) view.findViewById(R.id.PasswordmodifyBtn);
        Button btnRegisterDelete = (Button) view.findViewById(R.id.registerDeleteBtn);
        Button btnNewNickname = (Button) view.findViewById(R.id.newNickNameBtn);

        btnPasswordModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
                startActivity(intent);
            }
        });

        btnRegisterDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), DeleteMemberActivity.class);
                startActivity(intent);

            }
        });

        btnNewNickname.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), UpdateNicknameActivity.class);
                startActivity(intent);

            }
        });

        return view;
    }


}
