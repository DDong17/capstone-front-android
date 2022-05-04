package category_menu;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.cookandroid.capstone_front_android.MainActivity;
import com.cookandroid.capstone_front_android.R;

public class category_all extends Fragment {
    private View view;

    private MainActivity activity;

    private Button btn_exit; // 나가기 버튼.

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.category_all, container, false);

        activity = (MainActivity) getActivity();

        btn_exit = view.findViewById(R.id.exit);

        // 버튼이벤트.
        btn_exit.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                activity.setCategory(0);
            }
        });

        return view;
    }
}
