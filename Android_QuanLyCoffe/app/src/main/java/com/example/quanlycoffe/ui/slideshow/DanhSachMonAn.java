package com.example.quanlycoffe.ui.slideshow;

import android.media.Image;

import com.example.quanlycoffe.Database.ConnectionDB;
import com.example.quanlycoffe.R;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanhSachMonAn {

    ResultSet resultSet;
    ConnectionDB connectionDB=new ConnectionDB();
    Connection connection;
    final ArrayList dstenmon= new ArrayList();
    final ArrayList dsdongia=new ArrayList();
    final ArrayList dshinhanh=new ArrayList();

    public List<Map<String,String>> getList(String IP)
    {
        List<Map<String,String>>list=null;
        list =new ArrayList<Map<String, String>>();
        try
        {
            connection=connectionDB.connections(IP);
                String query="select * from MONAN";
                Statement statement=connection.createStatement();
                ResultSet resultSet=statement.executeQuery(query);
                while (resultSet.next())
                {
                    Map<String,String> datanum =new HashMap<String, String>();
                    datanum.put("TenMon","Tên món: "+resultSet.getString("TenMon"));
                    datanum.put("DonGia","Đơn giá"+resultSet.getString("DonGia")+"VNĐ");
                    datanum.put("HinhAnh",resultSet.getString("HinhAnh"));
                    list.add(datanum);
                }
                connection.close();
        }
        catch (Exception ex)
        {

        }
        return list;
    }


    public  ArrayList getTenMon(String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("select * from MONAN");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {

                dstenmon.add(resultSet.getString("TenMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dstenmon;
    }
    public  ArrayList getdongia(String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("select * from MONAN");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsdongia.add(resultSet.getString("DonGia"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsdongia;
    }
    public  ArrayList getHinhAnh(String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("select * from MONAN");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dshinhanh.add(resultSet.getString("HinhAnh"));
            }
        }
        catch (Exception ex)
        {

        }
        return dshinhanh;
    }

    private static String TienTe(String Tien)
    {
        DecimalFormat format=new DecimalFormat("###,###,##0");
        return format.format(Double.parseDouble(Tien));
    }
}
