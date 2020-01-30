package com.example.app_banhang.model;

public class GioHang {
    public String masp;
    public String tensp;
    public String hinhanh;
    public int gia;
    public int soluong;

    public GioHang(String masp, String tensp, String hinhanh, int gia, int soluong) {
        this.masp = masp;
        this.tensp = tensp;
        this.hinhanh = hinhanh;
        this.gia = gia;
        this.soluong = soluong;
    }

    public String getMasp() {
        return masp;
    }

    public void setMasp(String masp) {
        this.masp = masp;
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

    public int getGia() {
        return gia;
    }

    public void setGia(int gia) {
        this.gia = gia;
    }

    public int getSoluong() {
        return soluong;
    }

    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
}
