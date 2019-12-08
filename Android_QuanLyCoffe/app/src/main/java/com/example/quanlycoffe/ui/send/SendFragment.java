package com.example.quanlycoffe.ui.send;

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

public class SendFragment extends Fragment {

    private SendViewModel sendViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        sendViewModel =
                ViewModelProviders.of(this).get(SendViewModel.class);
        View root = inflater.inflate(R.layout.fragment_send, container, false);
        final TextView textView=root.findViewById(R.id.minhluong);
        final TextView textView1=root.findViewById(R.id.trungpham);
        sendViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText("Sinh viên thực hiện: Vũ Minh Lượng\nMã số sinh viên:1624801030129\nLớp D16PM02\n ");
                textView1.setText("Sinh viên thực hiện: Phạm Văn Trung\nMã số sinh viên:1624801030114\nLớp D16PM02\n ");
            }
        });
        return root;
    }
}