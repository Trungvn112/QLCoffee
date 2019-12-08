package com.example.quanlycoffe;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class nav_header_main extends AppCompatActivity {


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nav_header_main);
        TextView textView=findViewById(R.id.tendn);

        Intent intent=getIntent();
        String HoTenNV=intent.getStringExtra("HoTenNV");
        textView.setText(HoTenNV);
        textView.setText("aaaaaaaaaaaaa");
    }
}
