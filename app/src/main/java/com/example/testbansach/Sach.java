package com.example.testbansach;

public class Sach {
    private String tenSach;
    private int soLuong;
    private String tuoi;
    private boolean isVip;
    private final int GIA = 10000;
    private final double phanTramGiam = 10;

    public Sach(String tenSach, int soLuong, String tuoi, boolean isVip) {
        this.tenSach = tenSach;
        this.soLuong = soLuong;
        this.tuoi = tuoi;
        this.isVip = isVip;
    }

    public Sach() {}

    public String getTenSach() {
        return tenSach;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public void setTuoi(String tuoi) {
        this.tuoi = tuoi;
    }

    public String getTuoi(int soLuong) {
        return this.tuoi;
    }

    public double thanhTien() {
        if (this.isVip) {
            return (this.GIA * this.soLuong) / 100 * (100 - this.phanTramGiam);
        }
        return this.GIA * this.soLuong;
    }

    public String showAlert() {
        return this.tenSach + " - " + this.soLuong + " - " + this.tuoi + " - "+ (this.isVip ? "VIP" : "Thường");
    }

    public String toString() {
        return this.tenSach + " - " + this.soLuong + " - " + this.tuoi + " - "+ (this.isVip ? "VIP" : "Thường") + "  - " + this.thanhTien() ;
    }
}
