package com.example.app_banhang.model;

public class phanhoi {
    String tensp;
    String tenkh;
    String hinhanh;
    String mota;

    public phanhoi(String tensp, String tenkh, String hinhanh, String mota) {
        this.tensp = tensp;
        this.tenkh = tenkh;
        this.hinhanh = hinhanh;
        this.mota = mota;
    }

    public String getTensp() {
        return tensp;
    }

    public String getTenkh() {
        return tenkh;
    }

    public String getHinhanh() {
        return hinhanh;
    }

    public String getMota() {
        return mota;
    }

    public void setTensp(String tensp) {
        this.tensp = tensp;
    }

    public void setTenkh(String tenkh) {
        this.tenkh = tenkh;
    }

    public void setHinhanh(String hinhanh) {
        this.hinhanh = hinhanh;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }
}
