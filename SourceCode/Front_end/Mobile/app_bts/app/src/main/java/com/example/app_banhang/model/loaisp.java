package com.example.app_banhang.model;

public class loaisp {
    public String maloaisp;
    public String tenloaisp;
    public String hinhanh;

    public loaisp(String maloaisp, String tenloaisp, String hinhanh) {
        this.maloaisp = maloaisp;
        this.tenloaisp = tenloaisp;
        this.hinhanh= hinhanh;
    }

    public String getMaloaisp() {
        return maloaisp;
    }

    public void setMaloaisp(String maloaisp) {
        this.maloaisp = maloaisp;
    }

    public String getTenloaisp() {
        return tenloaisp;
    }

    public void setTenloaisp(String tenloaisp) {
        this.tenloaisp = tenloaisp;
    }


    public String getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }
}
