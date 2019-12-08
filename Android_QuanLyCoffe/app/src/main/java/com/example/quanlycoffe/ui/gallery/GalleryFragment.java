package com.example.quanlycoffe.ui.gallery;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import com.example.quanlycoffe.R;
import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.example.quanlycoffe.Database.ConnectionDB;
import com.example.quanlycoffe.ui.slideshow.DanhSachMonAn;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GalleryFragment extends Fragment{

    private  SimpleAdapter simpleAdapter;
    private GalleryViewModel galleryViewModel;
    private ConnectionDB connectionDB;
    private  Connection connection;
    ResultSet resultSet;
    private ArrayList<String> list=new ArrayList<String>();
    private  DanhSachBan danhSachBan=new DanhSachBan();
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        galleryViewModel =
                ViewModelProviders.of(this).get(GalleryViewModel.class);
        View root = inflater.inflate(R.layout.fragment_gallery, container, false);


        setHasOptionsMenu(true);
        Intent intent=getActivity().getIntent();
        final String IP=intent.getStringExtra("IP");

        /*final  ListView lv=root.findViewById(R.id.list_item1);*/
        final GridView gridView=root.findViewById(R.id.grid_danhsachban);
        galleryViewModel.getText().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                /*final ArrayAdapter arrayAdapter=new ArrayAdapter(getActivity(),android.R.layout.simple_list_item_1,list);
                lv.setAdapter(arrayAdapter);*/
                /*ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1,list);
                gridView.setAdapter(arrayAdapter);*/

                List<Map<String, String>> Mydatalist = null;
                final DanhSachBan myData = new DanhSachBan();
                Mydatalist = myData.getList(IP);
                final String[] DanhSachMonAn ={"TenBan", "TrangThai"};
                final int[] aDanhSachMonAn ={R.id.textView_countryName, R.id.textView_population};
                simpleAdapter = new SimpleAdapter(getActivity(), Mydatalist, R.layout.danhsachban, DanhSachMonAn,aDanhSachMonAn);
                gridView.setAdapter(simpleAdapter);


                registerForContextMenu(gridView);
            }
        });



        if (Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }
        return root;
    }

    @Override
    public void onCreateContextMenu(@NonNull ContextMenu menu, @NonNull View v, @Nullable ContextMenu.ContextMenuInfo menuInfo) {
        getActivity().getMenuInflater().inflate(R.menu.danhsachban,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {

        AdapterView.AdapterContextMenuInfo info=(AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        int i=info.position;
        simpleAdapter.getItem(i);
        switch (item.getItemId())
        {
            case R.id.add:
                Toast.makeText(getActivity(),"a",Toast.LENGTH_SHORT).show();
                Toast.makeText(getActivity(),simpleAdapter.getItem(i).toString(),Toast.LENGTH_SHORT).show();
                break;
            case R.id.exit:
                Toast.makeText(getActivity(),"b",Toast.LENGTH_SHORT).show();
                break;
        }
        return super.onContextItemSelected(item);
    }
}