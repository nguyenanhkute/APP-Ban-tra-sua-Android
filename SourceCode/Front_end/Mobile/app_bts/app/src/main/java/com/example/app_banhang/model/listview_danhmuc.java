package com.example.app_banhang.model;

public class listview_danhmuc {
    String tendanhmuc;
    int hinhanh;

    public listview_danhmuc(String tendanhmuc, int hinhanh) {
        this.tendanhmuc = tendanhmuc;
        this.hinhanh = hinhanh;
    }

    public String getTendanhmuc() {
        return tendanhmuc;
    }

    public void setTendanhmuc(String tendanhmuc) {
        this.tendanhmuc = tendanhmuc;
    }

    public int getHinhanh() {
        return hinhanh;
    }

    public void setHinhanh(int hinhanh) {
        this.hinhanh = hinhanh;
    }
}
