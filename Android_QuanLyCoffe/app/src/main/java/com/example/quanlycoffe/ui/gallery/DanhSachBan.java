package com.example.quanlycoffe.ui.gallery;

import com.example.quanlycoffe.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DanhSachBan {
    /*ConnectionDB connectionDB=new ConnectionDB();
    String z="";
    ResultSet resultSet;
    Connection connection=connectionDB.connections();
    final ArrayList list=new ArrayList();
    public  ArrayList getList()
    {


        try {
            PreparedStatement statement=connection.prepareStatement("select * from DANHSACHBAN");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                    list.add(resultSet.getString("TenBan")+"\n"+resultSet.getString("TrangThai"));
            }
        }
        catch (Exception ex)
        {

        }
        return list;
    }*/

    ResultSet resultSet;
    Connection connection;

    ConnectionDB connectionDB=new ConnectionDB();

    public List<Map<String,String>> getList(String IP)
    {
        List<Map<String,String>>list=null;
        list =new ArrayList<Map<String, String>>();
        try
        {
            connection=connectionDB.connections(IP);
            String query="select * from DANHSACHBAN";
            Statement statement=connection.createStatement();
            ResultSet resultSet=statement.executeQuery(query);
            while (resultSet.next())
            {
                Map<String,String> datanum =new HashMap<String, String>();
                datanum.put("TenBan",resultSet.getString("TenBan"));
                datanum.put("TrangThai",resultSet.getString("TrangThai"));
                list.add(datanum);
            }
            connection.close();
        }
        catch (Exception ex)
        {

        }
        return list;
    }


}
