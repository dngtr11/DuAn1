/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author anh
 */
public class SanPham {
    private int id;
    private String maSp;
    private String tenSp;
    private boolean trangThai; 

    public SanPham() {
    }

    public SanPham(String maSp, String tenSp, boolean trangThai) {
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.trangThai = trangThai;
    }
    
    public SanPham(int id, String maSp, String tenSp, boolean trangThai) {
        this.id = id;
        this.maSp = maSp;
        this.tenSp = tenSp;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaSp() {
        return maSp;
    }

    public void setMaSp(String maSp) {
        this.maSp = maSp;
    }

    public String getTenSp() {
        return tenSp;
    }

    public void setTenSp(String tenSp) {
        this.tenSp = tenSp;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenSp;
    }
    
}
