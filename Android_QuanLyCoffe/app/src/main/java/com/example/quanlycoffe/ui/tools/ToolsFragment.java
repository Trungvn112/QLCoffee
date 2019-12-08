package com.example.quanlycoffe.ui.tools;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.InputType;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlycoffe.Database.ConnectionDB;
import com.example.quanlycoffe.R;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ToolsFragment extends Fragment {

    private ToolsViewModel toolsViewModel;
    private ArrayAdapter arrayAdapterdsban,arrayAdapterdsloaimon,arrayAdapterdsmon,arrayAdapterdsgoimon;
    private ArrayList<String> dsban=new ArrayList<String>();
    private ArrayList<String> dsloaimon=new ArrayList<String>();
    private ArrayList<String> dsmon=new ArrayList<String>();
    private ArrayList<String> dsgoimon=new ArrayList<String>();
    private  GoiMon goiMon=new GoiMon();
    private String[] MaBan=null,MaLoaiMon=null,MaMon=null;
    private  String aMaBan=null,aMaLoaiMon=null,aMaMon=null,aDonGia=null;


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        toolsViewModel =
                ViewModelProviders.of(this).get(ToolsViewModel.class);
        View root = inflater.inflate(R.layout.fragment_tools, container, false);
        final ListView lv=root.findViewById(R.id.list_danhsachban);
        final ListView lv1=root.findViewById(R.id.list_danhsachloaimonan);
        final  ListView lv2=root.findViewById(R.id.list_danhsachmonan);
        final Button btn_themmon=root.findViewById(R.id.btn_themmon);
        final  EditText edit_soluong=root.findViewById(R.id.edit_soluong);

        Intent intent=getActivity().getIntent();
        final String IP=intent.getStringExtra("IP");
        toolsViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                dsban=goiMon.getDanhSachBan(IP);
                arrayAdapterdsban=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,dsban);
                lv.setAdapter(arrayAdapterdsban);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MaBan=arrayAdapterdsban.getItem(position).toString().split(" ");
                        aMaBan=MaBan[0].toString();
                    }
                });


                dsloaimon=goiMon.getDanhSachLoaiMon(IP);
                arrayAdapterdsloaimon=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,dsloaimon);
                lv1.setAdapter(arrayAdapterdsloaimon);
                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MaLoaiMon=arrayAdapterdsloaimon.getItem(position).toString().split(" ");
                        aMaLoaiMon=MaLoaiMon[0].toString();
                        dsmon=goiMon.getDanhSachMon(aMaLoaiMon,IP);
                        arrayAdapterdsmon=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,dsmon);
                        lv2.setAdapter(arrayAdapterdsmon);
                    }
                });

                lv2.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MaMon=arrayAdapterdsmon.getItem(position).toString().split("       \\|\\|     ");
                        aMaMon=MaMon[0].toString();
                        String[] DonGia=MaMon[2].toString().split( " VNĐ");
                        aDonGia=DonGia[0].toString();

                    }
                });
                btn_themmon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (aMaBan!=null)
                        {
                            if (aMaLoaiMon!=null)
                            {
                                if (aMaMon!=null)
                                {
                                    if (edit_soluong.getText().toString().equals("0") || edit_soluong.getText().toString().isEmpty() || edit_soluong.getText().toString().length()==0 || edit_soluong.getText().toString().equals("") || edit_soluong.getText().toString()==null)
                                    {
                                        Toast.makeText(getActivity(),"Vui lòng kiểm tra lại số lượng đã nhập!",Toast.LENGTH_SHORT).show();

                                    }
                                    else
                                    {
                                        dsgoimon=goiMon.getDanhSachGoiMon(aMaBan,aMaMon,IP);
                                        arrayAdapterdsgoimon=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,dsgoimon);
                                        if (arrayAdapterdsgoimon.getCount()<=0)
                                        {
                                            goiMon.AddGoiMon(aMaBan, aMaMon, aDonGia, edit_soluong.getText().toString(),IP);
                                            goiMon.TrangThaiBan(aMaBan,IP);
                                            edit_soluong.getText().clear();
                                            Toast.makeText(getActivity(),"Bạn đã gọi món thành công!",Toast.LENGTH_SHORT).show();
                                        }
                                        else
                                        {
                                            goiMon.UpdateGoiMon(aMaBan,aMaMon,edit_soluong.getText().toString(),IP);
                                            edit_soluong.getText().clear();
                                            Toast.makeText(getActivity(),"Bạn đã gọi món thành công!",Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                }
                                else
                                {
                                    Toast.makeText(getActivity(),"Vui lòng lựa chọn món để thêm!",Toast.LENGTH_SHORT).show();
                                }
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Vui lòng lựa chọn loại món cần kêu!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"Vui lòng lựa chọn bàn cần gọi món!",Toast.LENGTH_SHORT).show();
                        }

                    }
                });

                if (Build.VERSION.SDK_INT>9)
                {
                    StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
                    StrictMode.setThreadPolicy(policy);
                }
            }
        });
        return root;

    }
}