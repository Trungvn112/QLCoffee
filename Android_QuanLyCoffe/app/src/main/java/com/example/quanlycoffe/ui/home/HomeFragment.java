package com.example.quanlycoffe.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quanlycoffe.R;
import com.example.quanlycoffe.ui.gallery.DanhSachBan;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class HomeFragment extends Fragment {

    private  SimpleAdapter simpleAdapter;
    private HomeViewModel homeViewModel;
    private ArrayAdapter arrayAdapterdsban,arrayAdapterdsthucdon;
    private ArrayList<String> dsban=new ArrayList<String>();
    private ArrayList<String> dsthucdon=new ArrayList<String>();
    private String[] MaBan=null,MaMon=null,SoLuong=null;
    private  String aMaBan=null,aMaMon=null,aSoLuong=null;
    private ThucDon thucDon=new ThucDon();


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final ListView lv=root.findViewById(R.id.list_danhsachban);
        final ListView lv1=root.findViewById(R.id.list_danhsachthucdon);
        final EditText edit_soluong=root.findViewById(R.id.edit_soluong);
        final Button btnthucdon=root.findViewById(R.id.btn_suamon);
        Intent intent=getActivity().getIntent();
        final String IP=intent.getStringExtra("IP");
        homeViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                dsban=thucDon.getDanhSachBan(IP);
                arrayAdapterdsban=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,dsban);
                lv.setAdapter(arrayAdapterdsban);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        MaBan=arrayAdapterdsban.getItem(position).toString().split(" ");
                        aMaBan=MaBan[0].toString();
                        /*=thucDon.getDanhSachThucDon(aMaBan);
                        arrayAdapterdsthucdon=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,dsthucdon);
                        lv1.setAdapter(arrayAdapterdsthucdon);*/

                        List<Map<String, String>> Mydatalist = null;
                        ThucDon thucDon=new ThucDon();
                        Mydatalist = thucDon.getDanhSachThucDon(aMaBan,IP);
                        final String[] DanhSachMonAn ={"MaMon","TenMon","SoLuong","DonGia","ThanhTien"};
                        final int[] aDanhSachMonAn ={R.id.txtmamon,R.id.txttenmon,R.id.txtsoluong,R.id.txtdongia,R.id.txtthanhtien};
                        simpleAdapter = new SimpleAdapter(getActivity(), Mydatalist, R.layout.danhsachthucdon, DanhSachMonAn,aDanhSachMonAn);
                        lv1.setAdapter(simpleAdapter);
                    }
                });
                lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                        MaMon=simpleAdapter.getItem(position).toString().split(",");
                        String[] aaMaMon=MaMon[0].toString().split("=");
                        aMaMon=aaMaMon[1].toString();

                        SoLuong=simpleAdapter.getItem(position).toString().split("SoLuong=\\| ");
                        String[] aaSoLuong=SoLuong[1].toString().split("\\}");
                        aSoLuong=aaSoLuong[0].toString();
                        edit_soluong.setText(aSoLuong);

                    }
                });

                btnthucdon.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getActivity(),aMaBan+aMaMon,Toast.LENGTH_SHORT).show();
                        if (aMaBan!=null)
                        {
                            if (MaMon!=null)
                            {
                                if (edit_soluong.getText().toString().isEmpty() || edit_soluong.getText().toString().length()==0 || edit_soluong.getText().toString().equals("") || edit_soluong.getText().toString()==null)
                                {
                                    Toast.makeText(getActivity(),"Vui lòng kiểm tra lại số lượng đã nhập!",Toast.LENGTH_SHORT).show();
                                }
                                else
                                {
                                    if (edit_soluong.getText().toString().equals("0"))
                                    {
                                        thucDon.XoaMon(aMaBan,aMaMon,IP);
                                        List<Map<String, String>> Mydatalist = null;
                                        ThucDon thucDon=new ThucDon();
                                        Mydatalist = thucDon.getDanhSachThucDon(aMaBan,IP);
                                        final String[] DanhSachMonAn ={"MaMon","TenMon","SoLuong","DonGia","ThanhTien"};
                                        final int[] aDanhSachMonAn ={R.id.txtmamon,R.id.txttenmon,R.id.txtsoluong,R.id.txtdongia ,R.id.txtthanhtien};
                                        simpleAdapter = new SimpleAdapter(getActivity(), Mydatalist, R.layout.danhsachthucdon, DanhSachMonAn,aDanhSachMonAn);
                                        lv1.setAdapter(simpleAdapter);
                                        edit_soluong.getText().clear();
                                        Toast.makeText(getActivity(),"Bạn đã cập nhật thành công!",Toast.LENGTH_SHORT).show();
                                    }
                                    else
                                    {
                                        thucDon.ThayDoiSoLuong(aMaBan,aMaMon,edit_soluong.getText().toString(),IP);
                                        List<Map<String, String>> Mydatalist = null;
                                        ThucDon thucDon=new ThucDon();
                                        Mydatalist = thucDon.getDanhSachThucDon(aMaBan,IP);
                                        final String[] DanhSachMonAn ={"MaMon","TenMon","SoLuong","DonGia","ThanhTien"};
                                        final int[] aDanhSachMonAn ={R.id.txtmamon,R.id.txttenmon,R.id.txtsoluong,R.id.txtdongia,R.id.txtthanhtien};
                                        simpleAdapter = new SimpleAdapter(getActivity(), Mydatalist, R.layout.danhsachthucdon, DanhSachMonAn,aDanhSachMonAn);
                                        lv1.setAdapter(simpleAdapter);
                                        edit_soluong.getText().clear();
                                        Toast.makeText(getActivity(),"Bạn đã cập nhật thành công!",Toast.LENGTH_SHORT).show();

                                    }

                                }
                            }
                            else
                            {
                                Toast.makeText(getActivity(),"Vui lòng lựa chọn món!",Toast.LENGTH_SHORT).show();
                            }
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"Vui lòng lựa chọn bàn!",Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });
        return root;
    }
}