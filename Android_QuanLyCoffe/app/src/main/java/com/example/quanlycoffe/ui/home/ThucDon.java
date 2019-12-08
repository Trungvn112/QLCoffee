package com.example.quanlycoffe.ui.home;

import com.example.quanlycoffe.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ThucDon {
    ConnectionDB connectionDB=new ConnectionDB();
    String z="";
    ResultSet resultSet;
    Connection connection;
    final ArrayList dsthucdon=new ArrayList();
    final ArrayList dsban=new ArrayList();

   /* public  ArrayList getDanhSachThucDon(String MaBan)
    {
        try {
            PreparedStatement statement=connection.prepareStatement("select * from GOIMON,MONAN where GOIMON.MaMon=MONAN.MaMon and GOIMON.MaBan='"+MaBan+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsthucdon.add(resultSet.getString("MaMon")+"      ||      "+resultSet.getString("TenMon")+"      ||      "+resultSet.getString("SoLuong")+"      ||      "+resultSet.getString("DonGia")+"      ||      "+resultSet.getString("ThanhTien") );
            }
        }
        catch (Exception ex)
        {

        }
        return dsthucdon;
    }*/

    public  ArrayList getDanhSachBan(String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("select * from DANHSACHBAN");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsban.add(resultSet.getString(1)+"      ||      "+resultSet.getString(2));
            }
        }
        catch (Exception ex)
        {

        }
        return dsban;
    }

    private static String TienTe(String Tien)
    {
        DecimalFormat format=new DecimalFormat("###,###,##0");
        return format.format(Double.parseDouble(Tien));
    }

    public List<Map<String,String>> getDanhSachThucDon(String MaBan,String IP)
    {
        List<Map<String,String>>list=null;
        list =new ArrayList<Map<String, String>>();
        try
        {
            connection=connectionDB.connections(IP);
            String query=("select * from GOIMON,MONAN where GOIMON.MaMon=MONAN.MaMon and GOIMON.MaBan='"+MaBan+"'");
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next())
            {
                Map<String,String> datanum =new HashMap<String, String>();
                datanum.put("MaMon",resultSet.getString("MaMon"));
                datanum.put("TenMon","| "+resultSet.getString("TenMon"));
                datanum.put("SoLuong","| "+resultSet.getString("SoLuong"));
                datanum.put("DonGia","| "+TienTe(resultSet.getString("DonGia"))+" VNĐ");
                datanum.put("ThanhTien","| "+TienTe(resultSet.getString("ThanhTien")) +" VNĐ");
                list.add(datanum);
            }
            connection.close();
        }
        catch (Exception ex)
        {

        }
        return list;
    }

    public void ThayDoiSoLuong(String MaBan,String MaMon,String SoLuong,String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement1=connection.prepareStatement("update GOIMON set SoLuong=N'"+SoLuong+"' where MaBan='"+MaBan+"' and MaMon='"+MaMon+"'");
            resultSet=statement1.executeQuery();
            statement1.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }

    public void XoaMon(String MaBan,String MaMon,String IP)
    {
        try
        {
            connection=connectionDB.connections(IP);
            PreparedStatement statement1=connection.prepareStatement("delete from GOIMON where MaBan='"+MaBan+"' and MaMon='"+MaMon+"'");
            resultSet=statement1.executeQuery();
            statement1.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }
}
