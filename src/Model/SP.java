/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Model;

/**
 *
 * @author ADMIN
 */
public class SP {
    private Integer id;
    private String ten;
    private String mauS;
    private String kt;
    private String chatLieu;
    private String thuongHieu;
    private Double gia;
    private int soLuong;

    public SP() {
    }

    public SP(Integer id, String ten, String mauS, String kt, String chatLieu,  String thuongHieu, Double gia, int soLuong) {
        this.id = id;
        this.ten = ten;
        this.mauS = mauS;
        this.kt = kt;
        this.chatLieu = chatLieu;
        
        this.thuongHieu = thuongHieu;
        this.gia = gia;
        this.soLuong = soLuong;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getMauS() {
        return mauS;
    }

    public void setMauS(String mauS) {
        this.mauS = mauS;
    }

    public String getKt() {
        return kt;
    }

    public void setKt(String kt) {
        this.kt = kt;
    }

    public String getChatLieu() {
        return chatLieu;
    }

    public void setChatLieu(String chatLieu) {
        this.chatLieu = chatLieu;
    }

   

    public String getThuongHieu() {
        return thuongHieu;
    }

    public void setThuongHieu(String thuongHieu) {
        this.thuongHieu = thuongHieu;
    }

    public Double getGia() {
        return gia;
    }

    public void setGia(Double gia) {
        this.gia = gia;
    }

    public int getSoLuong() {
        return soLuong;
    }

    public void setSoLuong(int soLuong) {
        this.soLuong = soLuong;
    }
    
    
    
}
