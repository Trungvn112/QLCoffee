package com.example.quanlycoffe.ui.tools;

import com.example.quanlycoffe.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachBan {

    String z="";
    ResultSet resultSet;
    ConnectionDB connectionDB=new ConnectionDB();
    Connection connection;
    final ArrayList list=new ArrayList();
    final ArrayList list1=new ArrayList();
    public  ArrayList getList(String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("select * from DANHSACHBAN");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                list.add(resultSet.getString(1)+" "+resultSet.getString(2));
            }
        }
        catch (Exception ex)
        {

        }
        return list;
    }
    public  void  insert(String sql,String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("insert into DANHSACHBAN(TenBan) values('"+sql.toString()+"')");
            resultSet=statement.executeQuery();
            statement.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }
}
