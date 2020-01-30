package com.example.app_banhang.model;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class sanpham  {
    public String masp;
    public String tensp;
    public String hinhanh;
    public String maloaisp;
    public Integer gia;

    public sanpham(String masp, String tensp, String hinhanh, String maloaisp, Integer gia) {
        this.masp = masp;
        this.tensp = tensp;
        this.hinhanh = hinhanh;
        this.maloaisp = maloaisp;
        this.gia = gia;
    }

    public sanpham(String ten, String hinhanh, String gia)
    {
        this.tensp = ten;
        this.hinhanh = hinhanh;
        this.gia = Integer.parseInt(gia);
    }

    public String getMasp() {
        return masp;
    }

    public void setMalsp(String malsp) {
        this.masp = malsp;
    }

    public String getTensp() {
        return tensp;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public String getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(String maloaisp) {
        this.maloaisp = maloaisp;
    }

    public Integer getGia() {
        return gia;
    }

    public void setGia(Integer gia) {
        this.gia = gia;
    }
}
