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
public class HoaDonChiTiet {
     private int IDHoaDonChiTiet;
    private int IDHoaDon;
    private int IDHSanPhamChiTiet;
    private int SoLuong;
    private double Gia;
    private double ThanhTien;
    private String NguoiTao;
    private Date NgayTao;
    private Date NgaySua;

    public HoaDonChiTiet() {
    }

    public HoaDonChiTiet(int IDHoaDonChiTiet, int IDHoaDon, int IDHSanPhamChiTiet, int SoLuong, double Gia, double ThanhTien, String NguoiTao, Date NgayTao, Date NgaySua) {
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
        this.IDHoaDon = IDHoaDon;
        this.IDHSanPhamChiTiet = IDHSanPhamChiTiet;
        this.SoLuong = SoLuong;
        this.Gia = Gia;
        this.ThanhTien = ThanhTien;
        this.NguoiTao = NguoiTao;
        this.NgayTao = NgayTao;
        this.NgaySua = NgaySua;
    }

    public int getIDHoaDonChiTiet() {
        return IDHoaDonChiTiet;
    }

    public void setIDHoaDonChiTiet(int IDHoaDonChiTiet) {
        this.IDHoaDonChiTiet = IDHoaDonChiTiet;
    }

    public int getIDHoaDon() {
        return IDHoaDon;
    }

    public void setIDHoaDon(int IDHoaDon) {
        this.IDHoaDon = IDHoaDon;
    }

    public int getIDHSanPhamChiTiet() {
        return IDHSanPhamChiTiet;
    }

    public void setIDHSanPhamChiTiet(int IDHSanPhamChiTiet) {
        this.IDHSanPhamChiTiet = IDHSanPhamChiTiet;
    }

    public int getSoLuong() {
        return SoLuong;
    }

    public void setSoLuong(int SoLuong) {
        this.SoLuong = SoLuong;
    }

    public double getGia() {
        return Gia;
    }

    public void setGia(double Gia) {
        this.Gia = Gia;
    }

    public double getThanhTien() {
        return ThanhTien;
    }

    public void setThanhTien(double ThanhTien) {
        this.ThanhTien = ThanhTien;
    }

    public String getNguoiTao() {
        return NguoiTao;
    }

    public void setNguoiTao(String NguoiTao) {
        this.NguoiTao = NguoiTao;
    }

    public Date getNgayTao() {
        return NgayTao;
    }

    public void setNgayTao(Date NgayTao) {
        this.NgayTao = NgayTao;
    }

    public Date getNgaySua() {
        return NgaySua;
    }

    public void setNgaySua(Date NgaySua) {
        this.NgaySua = NgaySua;
    }

    @Override
    public String toString() {
        return "HoaDonChiTiet{" + "IDHoaDonChiTiet=" + IDHoaDonChiTiet + ", IDHoaDon=" + IDHoaDon + ", IDHSanPhamChiTiet=" + IDHSanPhamChiTiet + ", SoLuong=" + SoLuong + ", Gia=" + Gia + ", ThanhTien=" + ThanhTien + ", NguoiTao=" + NguoiTao + ", NgayTao=" + NgayTao + ", NgaySua=" + NgaySua + '}';
    }
    
}
