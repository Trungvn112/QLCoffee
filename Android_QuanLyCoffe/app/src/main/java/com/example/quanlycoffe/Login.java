package com.example.quanlycoffe;

import com.example.quanlycoffe.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Login {

    final ArrayList HoTenNV=new ArrayList();
    ResultSet resultSet;
    ConnectionDB connectionDB=new ConnectionDB();
    Connection connection;


/*
    public String Login(String TenDN,String MatKhau)
    {
        try {
            PreparedStatement statement1=connection.prepareStatement("select HoTenNV from NHANVIEN where TenDN='"+TenDN+"' and MatKhau='"+MatKhau+"'");
            resultSet=statement1.executeQuery();
            statement1.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }*/

    public ArrayList Login(String TenDN,String MatKhau,String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement1=connection.prepareStatement("select HoTenNV from NHANVIEN where TenDN='"+TenDN+"' and MatKhau='"+MatKhau+"'");
            resultSet=statement1.executeQuery();
            while (resultSet.next())
            {
                HoTenNV.add(resultSet.getString("HoTenNV"));
            }
        }
        catch (Exception ex)
        {

        }
        return HoTenNV;
    }

}
