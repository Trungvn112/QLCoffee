package com.example.quanlycoffe.ui.slideshow;

import androidx.fragment.app.FragmentActivity;

import java.util.ArrayList;
import java.util.Objects;

public class MonAn {
    private String TenMon;
    private String HinhAnh;
    private String DonGia;

    public MonAn(String tenMon, String hinhAnh, String donGia) {
        TenMon = tenMon;
        HinhAnh = hinhAnh;
        DonGia = donGia;
    }

    public String getTenMon() {
        return TenMon;
    }

    public void setTenMon(String tenMon) {
        TenMon = tenMon;
    }

    public String getHinhAnh() {
        return HinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        HinhAnh = hinhAnh;
    }

    public String getDonGia() {
        return DonGia;
    }

    public void setDonGia(String donGia) {
        DonGia = donGia;
    }

    @Override
    public String toString() {
        return "MonAn{" +
                "TenMon='" + TenMon + '\'' +
                ", HinhAnh='" + HinhAnh + '\'' +
                ", DonGia='" + DonGia + '\'' +
                '}';
    }
}
