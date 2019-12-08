package com.example.quanlycoffe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.quanlycoffe.Database.ConnectionDB;
import com.example.quanlycoffe.ui.gallery.GalleryFragment;

import java.sql.Connection;
import java.util.ArrayList;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private ArrayList<String> HoTenNV=new ArrayList<String>();
    private Login login=new Login();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        Button button=findViewById(R.id.btnlogin);
        final EditText editText=findViewById(R.id.edit_user);
        final EditText editText1=findViewById(R.id.edit_password);
        final EditText editText2=findViewById(R.id.edit_ip);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                HoTenNV=login.Login(editText.getText().toString(),editText1.getText().toString(),editText2.getText().toString());
                if (HoTenNV.size()>0)
                {
                    Intent intent=new Intent(LoginActivity.this,MainActivity.class);
                    String a=HoTenNV.toString();
                    intent.putExtra("IP",editText2.getText().toString());
                    startActivity(intent);
                }
                else
                {
                    Toast.makeText(getApplication(),"Sai tên đăng nhập hoặc mật khẩu!",Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void onClick(View view) {
    }
}
