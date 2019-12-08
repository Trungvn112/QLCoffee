package com.example.quanlycoffe.ui.tools;

import com.example.quanlycoffe.Database.ConnectionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.DecimalFormat;
import java.util.ArrayList;

public class GoiMon {

    ResultSet resultSet;
    ConnectionDB connectionDB=new ConnectionDB();
    Connection connection;
    final ArrayList dsban=new ArrayList();
    final ArrayList dsloaimon=new ArrayList();
    final ArrayList dsmon=new ArrayList();
    final ArrayList dsgoimon=new ArrayList();


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

    public  ArrayList getDanhSachLoaiMon(String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("select * from LOAIMON");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsloaimon.add(resultSet.getString(1)+"      ||      "+resultSet.getString(2));
            }
        }
        catch (Exception ex)
        {

        }
        return dsloaimon;
    }

    public  ArrayList getDanhSachMon(String sql,String IP)
    {
        dsmon.clear();
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("select * from MONAN where MaLoaiMon="+sql);
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                String a=String.format("{0:N0}",resultSet.getString("DonGia"));
                dsmon.add(resultSet.getString("MaMon")+"        ||      "+resultSet.getString("TenMon")+"        ||      "+ (TienTe(resultSet.getString("DonGia")))+" VNĐ");
            }
        }
        catch (Exception ex)
        {

        }
        return dsmon;
    }

    public  void  AddGoiMon(String MaBan,String MaMon,String DonGia,String SoLuong,String IP)
    {
        String TrangThai="Đã ngồi";
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("insert into GOIMON(MaBan,MaMon,DonGia,SoLuong) values('"+MaBan.toString()+"','"+MaMon.toString()+"','"+DonGia+"','"+SoLuong.toString()+"')");
            resultSet=statement.executeQuery();
            statement.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }

    public void TrangThaiBan(String MaBan,String IP)
    {
        String TrangThai="Đã ngồi";
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement1=connection.prepareStatement("update DANHSACHBAN set TrangThai=N'"+TrangThai+"' where MaBan='"+MaBan+"'");
            resultSet=statement1.executeQuery();
            statement1.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }

    public  void  UpdateGoiMon(String MaBan,String MaMon,String SoLuong,String IP)
    {
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("update GOIMON set SoLuong+='"+SoLuong+"' where MaBan='"+MaBan+"' and MaMon='"+MaMon+"'");
            resultSet=statement.executeQuery();
            statement.executeUpdate();
        }
        catch (Exception ex)
        {

        }
    }

    public  ArrayList getDanhSachGoiMon(String MaBan,String MaMon,String IP)
    {
        dsgoimon.clear();
        try {
            connection=connectionDB.connections(IP);
            PreparedStatement statement=connection.prepareStatement("select * from GOIMON where MaBan='"+MaBan+"' and MaMon='"+MaMon+"'");
            resultSet=statement.executeQuery();
            while (resultSet.next())
            {
                dsgoimon.add(resultSet.getString("MaMon"));
            }
        }
        catch (Exception ex)
        {

        }
        return dsgoimon;
    }

    private static String TienTe(String Tien)
    {
        DecimalFormat format=new DecimalFormat("###,###,##0");
        return format.format(Double.parseDouble(Tien));
    }
}
