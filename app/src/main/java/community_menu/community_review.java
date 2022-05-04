package community_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;

public class community_review extends Fragment {
    private View view;

    private MainActivity activity;

    // 커뮤니티 버튼.
    private Button btn_all; // 전체보기.
    private Button btn_together; // 같이 갈 사람.
    private Button btn_review; // 리뷰.
    private Button btn_write; // 글쓰기.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.item_community_review,container,false);

        activity = (MainActivity) getActivity();

        // 버튼 설정.
        btn_all = view.findViewById(R.id.all);
        btn_together = view.findViewById(R.id.together);
        btn_review = view.findViewById(R.id.review);
        btn_write = view.findViewById(R.id.write);

        // 버튼이벤트.
        btn_all.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(0);
            }
        });

        btn_together.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(1);
            }
        });

        btn_review.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(2);
            }
        });

        btn_write.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCommunity(3);
            }
        });

        return view;
    }
}
