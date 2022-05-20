package com.hoanhtrang.mobileproject;

import java.util.Date;

public class NhapChi {
    private String ngay;
    private String ghichu;
    private String sotien;
    private String danhmuc;

    public NhapChi(String ngay, String ghichu, String sotien, String danhmuc) {
        this.ngay = ngay;
        this.ghichu = ghichu;
        this.sotien = sotien;
        this.danhmuc = danhmuc;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGhichu() {
        return ghichu;
    }

    public void setGhichu(String ghichu) {
        this.ghichu = ghichu;
    }

    public String getSotien() {
        return sotien;
    }

    public void setSotien(String sotien) {
        this.sotien = sotien;
    }

    public String getDanhmuc() {
        return danhmuc;
    }

    public void setDanhmuc(String danhmuc) {
        this.danhmuc = danhmuc;
    }
}
