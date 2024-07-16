/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

import java.util.Date;

/**
 *
 * @author Dung Tran
 */
public class Voucher {
    private int id;
    private String MaVoucher;
    private String TenVoucher;
    private double DonToiThieu;
    private double MucGiamGia;
    private String LoaiGG;
    private int soLuong;
    private Date NgayBatDau;
    private Date NgayKetThuc;
    private Date NgayTao;
    private boolean TrangThai;

    public Voucher() {
    }

    public Voucher(int id, String MaVoucher, String TenVoucher, double DonToiThieu, double MucGiamGia, String LoaiGG, int soLuong, Date NgayBatDau, Date NgayKetThuc, Date NgayTao, boolean TrangThai) {
        this.id = id;
        this.MaVoucher = MaVoucher;
        this.TenVoucher = TenVoucher;
        this.DonToiThieu = DonToiThieu;
        this.MucGiamGia = MucGiamGia;
        this.LoaiGG = LoaiGG;
        this.soLuong = soLuong;
        this.NgayBatDau = NgayBatDau;
        this.NgayKetThuc = NgayKetThuc;
        this.NgayTao = NgayTao;
        this.TrangThai = TrangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaVoucher() {
        return MaVoucher;
    }

    public void setMaVoucher(String MaVoucher) {
        this.MaVoucher = MaVoucher;
    }

    public String getTenVoucher() {
        return TenVoucher;
    }

    public void setTenVoucher(String TenVoucher) {
        this.TenVoucher = TenVoucher;
    }

    public double getDonToiThieu() {
        return DonToiThieu;
    }

    public void setDonToiThieu(double DonToiThieu) {
        this.DonToiThieu = DonToiThieu;
    }

    public double getMucGiamGia() {
        return MucGiamGia;
    }

    public void setMucGiamGia(double MucGiamGia) {
        this.MucGiamGia = MucGiamGia;
    }

    public String getLoaiGG() {
        return LoaiGG;
    }

    public void setLoaiGG(String LoaiGG) {
        this.LoaiGG = LoaiGG;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public Date getNgayBatDau() {
        return NgayBatDau;
    }

    public void setNgayBatDau(Date NgayBatDau) {
        this.NgayBatDau = NgayBatDau;
    }

    public Date getNgayKetThuc() {
        return NgayKetThuc;
    }

    public void setNgayKetThuc(Date NgayKetThuc) {
        this.NgayKetThuc = NgayKetThuc;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public boolean isTrangThai() {
        return TrangThai;
    }

    public void setTrangThai(boolean TrangThai) {
        this.TrangThai = TrangThai;
    }

    
}
