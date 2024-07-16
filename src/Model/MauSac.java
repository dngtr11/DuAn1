/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author anh
 */
public class MauSac {

    private int id;
    private String maMs;
    private String tenMs;
    private boolean trangThai;

    public MauSac() {
    }

    public MauSac(String maMs, String tenMs, boolean trangThai) {
        this.maMs = maMs;
        this.tenMs = tenMs;
        this.trangThai = trangThai;
    }
    
    public MauSac(int id, String maMs, String tenMs, boolean trangThai) {
        this.id = id;
        this.maMs = maMs;
        this.tenMs = tenMs;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaMs() {
        return maMs;
    }

    public void setMaMs(String maMs) {
        this.maMs = maMs;
    }

    public String getTenMs() {
        return tenMs;
    }

    public void setTenMs(String tenMs) {
        this.tenMs = tenMs;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenMs;
    }
    
}
