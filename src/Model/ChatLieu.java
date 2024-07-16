/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author anh
 */
public class ChatLieu {

    private int id;
    private String maCl;
    private String tenCL;
    private boolean trangThai;

    public ChatLieu() {
    }

    public ChatLieu(String maCl, String tenCL, boolean trangThai) {
        this.maCl = maCl;
        this.tenCL = tenCL;
        this.trangThai = trangThai;
    }
    
    public ChatLieu(int id, String maCl, String tenCL, boolean trangThai) {
        this.id = id;
        this.maCl = maCl;
        this.tenCL = tenCL;
        this.trangThai = trangThai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMaCl() {
        return maCl;
    }

    public void setMaCl(String maCl) {
        this.maCl = maCl;
    }

    public String getTenCL() {
        return tenCL;
    }

    public void setTenCL(String tenCL) {
        this.tenCL = tenCL;
    }

    public boolean isTrangThai() {
        return trangThai;
    }

    public void setTrangThai(boolean trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public String toString() {
        return tenCL;
    }
    
}
