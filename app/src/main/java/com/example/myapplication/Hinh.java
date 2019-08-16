package com.example.myapplication;

public class Hinh {
    public String ten, mota, link;

    public Hinh(String ten, String mota, String link) {
        this.ten = ten;
        this.mota = mota;
        this.link = link;
    }

    public Hinh(){
    }

    public String getTen() {
        return ten;
    }

    public String getMota() {
        return mota;
    }

    public String getLink() {
        return link;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public void setMota(String mota) {
        this.mota = mota;
    }

    public void setLink(String link) {
        this.link = link;
    }
}