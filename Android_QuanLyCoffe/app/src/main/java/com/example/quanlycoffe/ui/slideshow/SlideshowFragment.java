package com.example.quanlycoffe.ui.slideshow;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import java.io.File;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Base64;
import com.example.quanlycoffe.Database.ConnectionDB;
import com.example.quanlycoffe.R;
import com.example.quanlycoffe.ui.tools.GoiMon;

import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static java.util.Base64.*;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;

    private ArrayList<String> dstenmon=new ArrayList<String>();
    private ArrayList<String> dsdongia=new ArrayList<String>();
    private ArrayList<String> dshinhanh=new ArrayList<String>();
    private String[] TenMon=null,DonGia=null,HinhAnh=null,HinhAnh3=null,HinhAnh4=null;
    private String HinhAnh2=null,HinhAnh1=null;
    private ConnectionDB connectionDB;
    private Connection connection;
    private DanhSachMonAn danhSachMonAn=new DanhSachMonAn();
    SimpleAdapter simpleAdapter;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        View root1=inflater.inflate(R.layout.danhsachmonan,container,false);
        final  ListView lv=root.findViewById(R.id.list_item1);
        final TextView textView1=root1.findViewById(R.id.textView_countryName);


        Intent intent=getActivity().getIntent();
        final String IP=intent.getStringExtra("IP");
        slideshowViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {

                List<MonAn> list=new ArrayList<MonAn>();
                dstenmon=danhSachMonAn.getTenMon(IP);
                dsdongia=danhSachMonAn.getdongia(IP);
                dshinhanh=danhSachMonAn.getHinhAnh(IP);
                for ( int i=0;i<dstenmon.size();i++)
                {
                    TenMon=dstenmon.toString().split("\\[");
                    TenMon=TenMon[1].toString().split("\\]");
                    TenMon=TenMon[0].toString().split(",");


                    HinhAnh=dshinhanh.toString().split("\\[");
                    HinhAnh=HinhAnh[1].toString().split("\\]");

                    HinhAnh1=(HinhAnh[0]+", ").toString();
                    HinhAnh2=HinhAnh1.replace(",","");
                    HinhAnh3=HinhAnh2.toString().split("\\.jpg ");

                    DonGia=dsdongia.toString().split("\\[");
                    DonGia=DonGia[1].toString().split("\\]");
                    DonGia=DonGia[0].toString().split(",");

                    MonAn monAn=new MonAn(TenMon[i],HinhAnh3[i],TienTe(DonGia[i])+" VNĐ");

                    list.add(monAn);
                    lv.setAdapter(new CustomListAdapter(getActivity(),list));

                }

                /*List<MonAn> list=getMonAn();
                lv.setAdapter(new CustomListAdapter(getActivity(),list));
                List<Map<String, String>> Mydatalist = null;
                final DanhSachMonAn myData = new DanhSachMonAn();
                Mydatalist = myData.getList();
                final String[] DanhSachMonAn ={"a","TenMon", "DonGia"};
                final int[] aDanhSachMonAn ={R.id.imageView_flag,R.id.textView_countryName, R.id.textView_population};
                simpleAdapter = new SimpleAdapter(getActivity(), Mydatalist, R.layout.danhsachmonan, DanhSachMonAn,aDanhSachMonAn);
                lv.setAdapter(simpleAdapter);
                lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        textView.setText(simpleAdapter.getItem(position).toString());
                    }
                });*/

            }
        });

        return root;
    }
    private static String TienTe(String Tien)
    {
        DecimalFormat format=new DecimalFormat("###,###,##0");
        return format.format(Double.parseDouble(Tien));
    }


/*

    private List<MonAn> getMonAn()
    {
        dstenmon=danhSachMonAn.getTenMon();

        List<MonAn> list=new ArrayList<MonAn>();
        for(int i=0;i<4;i++)
        {
            MonAn monAn=new MonAn("a","bap_xao","15000");
            list.add(monAn.get(i));
        }

        MonAn monAn=new MonAn("a","bap_xao","15000");
        MonAn monAn1=new MonAn("a","bap_xao","15000");
        MonAn monAn2=new MonAn("a","bap_xao","15000");
        list.add(monAn);
        list.add(monAn1);
        list.add(monAn2);
        return  list;
    }*/
}