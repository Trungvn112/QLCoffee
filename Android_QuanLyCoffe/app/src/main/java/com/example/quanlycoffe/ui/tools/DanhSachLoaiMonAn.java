package com.example.quanlycoffe.ui.tools;

import com.example.quanlycoffe.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DanhSachLoaiMonAn {

    String z="";
    ResultSet resultSet;
    Connection connection;
    final ArrayList list=new ArrayList();

    public  ArrayList getList()
    {
        try {
            PreparedStatement statement=connection.prepareStatement("select * from LOAIMON");
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
}
