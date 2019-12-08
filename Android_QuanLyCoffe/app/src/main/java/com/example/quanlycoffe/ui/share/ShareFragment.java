package com.example.quanlycoffe.ui.share;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlycoffe.R;

public class ShareFragment extends Fragment {

    private ShareViewModel shareViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        shareViewModel =
                ViewModelProviders.of(this).get(ShareViewModel.class);
        View root = inflater.inflate(R.layout.fragment_share, container, false);
        final TextView textView=root.findViewById(R.id.texthelp);
        shareViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Ứng dụng quản lý Coffe được tạo bởi sinh viên Vũ Minh Lượng và Phạm Văn Trung. Đây là một chương trình thực hiện công việc quản lý thanh toán hóa đơn và phục vụ món cho các quản coffe.Nếu bạn có thắc mắc hãy liên hệ chúng tôi để được giúp đỡ.");
            }
        });
        return root;
    }
}