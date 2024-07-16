/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.NhanVien;
import Repo.DBConnect;
import com.sun.jdi.connect.spi.Connection;
import java.util.ArrayList;
import java.sql.*;
import javax.swing.JOptionPane;

public class NhanVienService {

    public ArrayList<NhanVien> getAll() {
        ArrayList<NhanVien> list = new ArrayList<>();
        try {
            String sql = "SELECT MANHANVIEN, HOTEN, SDT, GIOITINH, MATKHAU, DIACHI, EMAIL, NGAYSINH, TRANGTHAI FROM NhanVien";
            java.sql.Connection cn = DBConnect.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMANHANVIEN(rs.getString("MANHANVIEN"));
                nv.setHOTEN(rs.getString("HOTEN"));
                nv.setSDT(rs.getInt("SDT"));
                nv.setGIOITINH(rs.getBoolean("GIOITINH"));
                nv.setMATKHAU(rs.getString("MATKHAU"));
                nv.setDIACHI(rs.getString("DIACHI"));
                nv.setEMAIL(rs.getString("EMAIL"));
                nv.setNGAYSINH(rs.getDate("NGAYSINH"));
                nv.setTRANGTHAI(rs.getBoolean("TRANGTHAI"));
                list.add(nv);
            }
            rs.close();
            ps.close();
            cn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return list;
    }

    public NhanVien getById(String MANHANVIEN) {
        try {
            java.sql.Connection cn = DBConnect.getConnection();
            PreparedStatement ps = cn.prepareStatement("SQL_SELECT_BY_MANHANVIEN");
            ps.setString(1, MANHANVIEN);
            ResultSet rs = ps.executeQuery();
            NhanVien nv = null;
            while (rs.next()) {
                String hoTen = rs.getString(1);
                Date ngSinh = rs.getDate(2);
                Boolean gtinh = rs.getBoolean(3);
                String dchi = rs.getString(4);
                String sdt = rs.getString(5);
                String email = rs.getString(6);
                String pass = rs.getString(7);
                Boolean trangThai = rs.getBoolean(8);
                nv = new NhanVien(0, MANHANVIEN, 0, hoTen, ngSinh, gtinh, dchi, 0, email, pass, 0, 0, hoTen, pass, trangThai);
                return nv;

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public void addNhanVien(NhanVien nv) {
        try {
            String sql = "INSERT INTO NhanVien (MANHANVIEN, HOTEN, SDT, GIOITINH, MATKHAU, DIACHI, EMAIL, NGAYSINH, TRANGTHAI) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
            java.sql.Connection cn = DBConnect.getConnection();
            PreparedStatement ps = cn.prepareStatement(sql);

            ps.setString(1, nv.getMANHANVIEN());
            ps.setString(2, nv.getHOTEN());
            ps.setInt(3, nv.getSDT());
            ps.setBoolean(4, nv.getGIOITINH());
            ps.setString(5, nv.getMATKHAU());
            ps.setString(6, nv.getDIACHI());
            ps.setString(7, nv.getEMAIL());
            ps.setDate(8, nv.getNGAYSINH());
            ps.setBoolean(9, nv.getTRANGTHAI());
            int rowsInserted = ps.executeUpdate();
            if (rowsInserted > 0) {
                System.out.println("Thêm nhân viên thành công.");
            } else {
                System.out.println("Thêm nhân viên thất bại.");
            }

            // Đóng tài nguyên
            ps.close();
            cn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Integer UpdateNhanVien(NhanVien nv) {
        Integer row = null;
        String sql = "UPDATE NhanVien SET HOTEN = ?, SDT = ?, GIOITINH = ?, MATKHAU = ?, DIACHI = ?, EMAIL = ?, NGAYSINH = ?, TRANGTHAI = ? WHERE MANHANVIEN = ?";
        try (java.sql.Connection cn = DBConnect.getConnection(); PreparedStatement ps = cn.prepareStatement(sql)) {

            ps.setString(1, nv.getHOTEN());
            ps.setInt(2, nv.getSDT());
            ps.setBoolean(3, nv.getGIOITINH());
            ps.setString(4, nv.getMATKHAU());
            ps.setString(5, nv.getDIACHI());
            ps.setString(6, nv.getEMAIL());
            ps.setDate(7, nv.getNGAYSINH());
            ps.setBoolean(8, nv.getTRANGTHAI());
            ps.setString(9, nv.getMANHANVIEN());

            row = ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("lỗi");
        }
        return row;
    }

    public ArrayList<NhanVien> timKiemNhanVien(String keyword) {
        ArrayList<NhanVien> ketQuaTimKiem = new ArrayList<>();
        String sql = "SELECT  MANHANVIEN,HOTEN, NGAYSINH, GIOITINH, DIACHI, SDT, EMAIL, MATKHAU, TRANGTHAI FROM NhanVien WHERE MANHANVIEN LIKE ?";
        try (java.sql.Connection cn = DBConnect.getConnection(); PreparedStatement pst = cn.prepareStatement(sql)) {

            pst.setString(1, "%" + keyword + "%");

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMANHANVIEN(rs.getString("MANHANVIEN"));
                nv.setHOTEN(rs.getString("HOTEN"));
                nv.setNGAYSINH(rs.getDate("NGAYSINH"));
                nv.setGIOITINH(rs.getBoolean("GIOITINH"));
                nv.setDIACHI(rs.getString("DIACHI"));
                nv.setSDT(rs.getInt("SDT"));
                nv.setEMAIL(rs.getString("EMAIL"));
                nv.setMATKHAU(rs.getString("MATKHAU"));
                nv.setTRANGTHAI(rs.getBoolean("TRANGTHAI"));
                ketQuaTimKiem.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
        }
        return ketQuaTimKiem;
    }

    public ArrayList<NhanVien> timKiemNhanVienTheoTrangThai(boolean trangThai) {
        ArrayList<NhanVien> ketQuaTimKiem = new ArrayList<>();
        try {
            String sql = "SELECT MANHANVIEN, HOTEN, NGAYSINH, GIOITINH, DIACHI, SDT, EMAIL, MATKHAU, TRANGTHAI FROM NhanVien WHERE TRANGTHAI = ?";
            java.sql.Connection cn = DBConnect.getConnection();
            PreparedStatement pst = cn.prepareStatement(sql);

            // Đặt giá trị cho tham số trạng thái
            pst.setBoolean(1, trangThai);

            ResultSet rs = pst.executeQuery();
            while (rs.next()) {
                NhanVien nv = new NhanVien();
                nv.setMANHANVIEN(rs.getString("MANHANVIEN"));
                nv.setHOTEN(rs.getString("HOTEN"));
                nv.setNGAYSINH(rs.getDate("NGAYSINH"));
                nv.setGIOITINH(rs.getBoolean("GIOITINH"));
                nv.setDIACHI(rs.getString("DIACHI"));
                nv.setSDT(rs.getInt("SDT"));
                nv.setEMAIL(rs.getString("EMAIL"));
                nv.setMATKHAU(rs.getString("MATKHAU"));
                nv.setTRANGTHAI(rs.getBoolean("TRANGTHAI"));
                ketQuaTimKiem.add(nv);
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Lỗi: " + e.getMessage());
        }
        return ketQuaTimKiem;
    }
    public static String checkUser(String TenDangNhap, String MatKhau) {
        String ChucVu = "";

        try {
            String sql = "  select MANHANVIEN, c.TENCHUCVU,EMAIL,MATKHAU  from NhanVien n join ChucVu c on c.IDCHUCVU=n.IDCHUCVU where EMAIL like ? and MATKHAU like ? ";
            java.sql.Connection con = DBConnect.getConnection();
            PreparedStatement pstm = con.prepareStatement(sql);
            pstm.setString(1, TenDangNhap);
            pstm.setString(2, MatKhau);
            ResultSet rs = pstm.executeQuery();
            while (rs.next()) {
                ChucVu = rs.getString("TENCHUCVU");
            }

        } catch (Exception e) {
            System.out.println("Lỗi check : " + e.getMessage());
        }
        return ChucVu;
    }

}
