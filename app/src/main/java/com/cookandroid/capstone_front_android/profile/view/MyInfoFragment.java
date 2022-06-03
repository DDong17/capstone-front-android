package com.cookandroid.capstone_front_android.profile.view;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.R;
import com.cookandroid.capstone_front_android.member.model.MemberApi;
import com.cookandroid.capstone_front_android.member.model.response.MemberResponse;
import com.cookandroid.capstone_front_android.member.view.ChangePasswordActivity;
import com.cookandroid.capstone_front_android.member.view.DeleteMemberActivity;
import com.cookandroid.capstone_front_android.member.view.UpdateNicknameActivity;
import com.cookandroid.capstone_front_android.util.network.RetrofitClient;

import org.w3c.dom.Text;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MyInfoFragment extends Fragment {

    private TextView tvUserName;
    private TextView tvUserNickname;
    private TextView tvUserPoints;
    private TextView tvUserId;

    private final MemberApi memberApi = RetrofitClient.getClient(MemberApi.class);

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        Button btnPasswordModify = (Button) view.findViewById(R.id.btn_profile_updatePassword);
        Button btnRegisterDelete = (Button) view.findViewById(R.id.btn_profile_deleteMember);
        ImageView btnNewNickname = (ImageView) view.findViewById(R.id.btn_profile_updateNickname);

        tvUserId = view.findViewById(R.id.tv_profile_userId);
        tvUserName = view.findViewById(R.id.tv_profile_userName);
        tvUserNickname = view.findViewById(R.id.tv_profile_userNickname);
        tvUserPoints = view.findViewById(R.id.tv_profile_point);

        // 로그인한 사용자 정보 세팅하기
        try {
            Long memberId = getContext().getSharedPreferences("pref", Context.MODE_PRIVATE).getLong("memberId", 0);
            setUserProfileData(memberId);
        } catch (NullPointerException e) {
            Log.e("오류","SharedPreference 에서 memberId를 가져올 수 없음");
        }

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

    private void setUserProfileData(Long memberId) {
        memberApi.getMember(memberId).enqueue(new Callback<MemberResponse>() {
            @Override
            public void onResponse(@NonNull Call<MemberResponse> call, @NonNull Response<MemberResponse> response) {
                try {
                    MemberResponse member = response.body();
                    tvUserId.setText("계졍 : " + member.getUserId());
                    tvUserName.setText(member.getName() + "님");
                    tvUserNickname.setText("활동명 : " + member.getNickname());
                    tvUserPoints.setText("기여도 : " + member.getPoint());
                } catch (Exception e) {
                    Log.e("프로필 통신 오류", "세팅 실패");
                }
            }

            @Override
            public void onFailure(@NonNull Call<MemberResponse> call, @NonNull Throwable t) {
                Log.e("프로필 통신 오류", "세팅 실패");
            }
        });
    }

}
