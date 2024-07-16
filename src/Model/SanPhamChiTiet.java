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
public class SanPhamChiTiet {
    private int id;
    private int idSp;
    private int idMs;
    private int idCl;
    private int idSize;
    private int idThuongHieu;
    private String moTa;
    private int soLuong;
    private float gia;
    private String nguoiTao;
    private String nguoiSua;
    private Date ngayTao;
    private Date ngaySua;
    private boolean trangThai;
    

    public SanPhamChiTiet() {
    }

    public SanPhamChiTiet(int idSp, int idMs, int idCl, int idSize, int idThuongHieu, String moTa, int soLuong, float gia, boolean trangThai) {
        this.idSp = idSp;
        this.idMs = idMs;
        this.idCl = idCl;
        this.idSize = idSize;
        this.idThuongHieu = idThuongHieu;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.gia = gia;
        this.trangThai = trangThai;
    }

    public SanPhamChiTiet(int id, int idSp, int idMs, int idCl, int idSize, int idThuongHieu, String moTa, int soLuong, float gia, String nguoiTao, String nguoiSua, Date ngayTao, Date ngaySua, boolean trangThai) {
        this.id = id;
        this.idSp = idSp;
        this.idMs = idMs;
        this.idCl = idCl;
        this.idSize = idSize;
        this.idThuongHieu = idThuongHieu;
        this.moTa = moTa;
        this.soLuong = soLuong;
        this.gia = gia;
        this.nguoiTao = nguoiTao;
        this.nguoiSua = nguoiSua;
        this.ngayTao = ngayTao;
        this.ngaySua = ngaySua;
        this.trangThai = trangThai;
        
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdSp() {
        return idSp;
    }

    public void setIdSp(int idSp) {
        this.idSp = idSp;
    }

    public int getIdMs() {
        return idMs;
    }

    public void setIdMs(int idMs) {
        this.idMs = idMs;
    }

    public int getIdCl() {
        return idCl;
    }

    public void setIdCl(int idCl) {
        this.idCl = idCl;
    }

    public int getIdSize() {
        return idSize;
    }

    public void setIdSize(int idSize) {
        this.idSize = idSize;
    }

    public int getIdThuongHieu() {
        return idThuongHieu;
    }

    public void setIdThuongHieu(int idThuongHieu) {
        this.idThuongHieu = idThuongHieu;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }

    public float getGia() {
        return gia;
    }

    public void setGia(float gia) {
        this.gia = gia;
    }

    public String getNguoiTao() {
        return nguoiTao;
    }

    public void setNguoiTao(String nguoiTao) {
        this.nguoiTao = nguoiTao;
    }

    public String getNguoiSua() {
        return nguoiSua;
    }

    public void setNguoiSua(String nguoiSua) {
        this.nguoiSua = nguoiSua;
    }

    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
    }

    public Date getNgaySua() {
        return ngaySua;
    }

    public void setNgaySua(Date ngaySua) {
        this.ngaySua = ngaySua;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }



   

  

    @Override
    public String toString() {
        return "SanPhamChiTiet{" + "id=" + id + ", idSp=" + idSp + ", idMs=" + idMs + ", idCl=" + idCl + ", idSize=" + idSize + ", idThuongHieu=" + idThuongHieu + ", moTa=" + moTa + ", soLuong=" + soLuong + ", gia=" + gia + ", nguoiTao=" + nguoiTao + ", nguoiSua=" + nguoiSua + ", ngayTao=" + ngayTao + ", ngaySua=" + ngaySua + ", trangThai=" + trangThai + '}';
    }

    
}
