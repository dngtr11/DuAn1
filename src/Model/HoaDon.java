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
public class HoaDon {
    private int idHoaDon;
    private int idKhachHang;
    private int idNhanVien;
    private int idVoucher;
    private String maHoaDon;
    private double tongTien;
    private Date ngayThanhToan;
    private Date ngaytao;
    private String nguoiTao;
    private boolean trangThai;

    public HoaDon() {
    }

    public HoaDon(int idHoaDon, int idKhachHang, int idNhanVien, int idVoucher, String maHoaDon, double tongTien, Date ngayThanhToan, Date ngaytao, String nguoiTao, boolean trangThai) {
        this.idHoaDon = idHoaDon;
        this.idKhachHang = idKhachHang;
        this.idNhanVien = idNhanVien;
        this.idVoucher = idVoucher;
        this.maHoaDon = maHoaDon;
        this.tongTien = tongTien;
        this.ngayThanhToan = ngayThanhToan;
        this.ngaytao = ngaytao;
        this.nguoiTao = nguoiTao;
        this.trangThai = trangThai;
    }

    public int getIdHoaDon() {
        return idHoaDon;
    }

    public void setIdHoaDon(int idHoaDon) {
        this.idHoaDon = idHoaDon;
    }

    public int getIdKhachHang() {
        return idKhachHang;
    }

    public void setIdKhachHang(int idKhachHang) {
        this.idKhachHang = idKhachHang;
    }

    public int getIdNhanVien() {
        return idNhanVien;
    }

    public void setIdNhanVien(int idNhanVien) {
        this.idNhanVien = idNhanVien;
    }

    public int getIdVoucher() {
        return idVoucher;
    }

    public void setIdVoucher(int idVoucher) {
        this.idVoucher = idVoucher;
    }

    public String getMaHoaDon() {
        return maHoaDon;
    }

    public void setMaHoaDon(String maHoaDon) {
        this.maHoaDon = maHoaDon;
    }

    public double getTongTien() {
        return tongTien;
    }

    public void setTongTien(double tongTien) {
        this.tongTien = tongTien;
    }

    public Date getNgayThanhToan() {
        return ngayThanhToan;
    }

    public void setNgayThanhToan(Date ngayThanhToan) {
        this.ngayThanhToan = ngayThanhToan;
    }

    public Date getNgaytao() {
        return ngaytao;
    }

    public void setNgaytao(Date ngaytao) {
        this.ngaytao = ngaytao;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return "HoaDon{" + "idHoaDon=" + idHoaDon + ", idKhachHang=" + idKhachHang + ", idNhanVien=" + idNhanVien + ", idVoucher=" + idVoucher + ", maHoaDon=" + maHoaDon + ", tongTien=" + tongTien + ", ngayThanhToan=" + ngayThanhToan + ", ngaytao=" + ngaytao + ", nguoiTao=" + nguoiTao + ", trangThai=" + trangThai + '}';
    }

    
    
    
}
