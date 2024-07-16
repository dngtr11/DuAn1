/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author anh
 */
public class Size {
    private int id;
    private String maS;
    private String tenS;
    private boolean trangThai;

    public Size() {
    }

    public Size(String maS, String tenS, boolean trangThai) {
        this.maS = maS;
        this.tenS = tenS;
        this.trangThai = trangThai;
    }
    
    public Size(int id, String maS, String tenS, boolean trangThai) {
        this.id = id;
        this.maS = maS;
        this.tenS = tenS;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaS() {
        return maS;
    }

    public void setMaS(String maS) {
        this.maS = maS;
    }

    public String getTenS() {
        return tenS;
    }

    public void setTenS(String tenS) {
        this.tenS = tenS;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenS;
    }
    
}
