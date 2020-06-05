package com.example.docsach.objects;

public class truyen {
    private String anhTruyen, tenTruyen, tenChap;

    public  truyen(){
    }
    public truyen(String anhTruyen, String tenTruyen, String tenChap) {
        this.anhTruyen = anhTruyen;
        this.tenTruyen = tenTruyen;
        this.tenChap = tenChap;
    }

    public String getAnhTruyen() {
        return anhTruyen;
    }

    public void setAnhTruyen(String anhTruyen) {
        this.anhTruyen = anhTruyen;
    }

    public String getTenTruyen() {
        return tenTruyen;
    }

    public void setTenTruyen(String tenTruyen) {
        this.tenTruyen = tenTruyen;
    }

    public String getTenChap() {
        return tenChap;
    }

    public void setTenChap(String tenChap) {
        this.tenChap = tenChap;
    }
}

