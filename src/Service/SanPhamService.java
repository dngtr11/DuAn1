/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.SanPham;
import Repo.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;



/**
 *
 * @author anh
 */
public class SanPhamService  {

    
    public List<SanPham> selectbySQL(String Sql, Object... orgs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public ArrayList<SanPham> getALL() {
        ArrayList<SanPham> list = new ArrayList<>();
        try {
            
            String sql = "select * from SanPham";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham cl = new SanPham();
                cl.setId(rs.getInt("IDSANPHAM"));
                cl.setMaSp(rs.getString("MASANPHAM"));
                cl.setTenSp(rs.getString("TENSP"));
                cl.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(cl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

   
    public List<SanPham> getALLresultPage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
    public List<SanPham> getbyEntityhidden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

 
    public List<SanPham> findEntity(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public List<SanPham> findEntityhidden(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
    public SanPham getbyID(int id) {
        List<SanPham> listById = new ArrayList<>();
        try {
            String GET_BY_ID = """
                                       select * from SanPham where IDSANPHAM = ?
                                       """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setMaSp(rs.getString(2));
                sp.setTenSp(rs.getString(3));
                sp.setTrangThai(rs.getBoolean(4));
                listById.add(sp);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (!listById.isEmpty()) {
            return listById.get(0);
        }
        return null;
    }

 
   public String addEntity(SanPham ett) {
    try {
        String sql = """
                        INSERT INTO [dbo].[SanPham]
                                              ([MASANPHAM]
                                               ,[TENSP]
                                               ,[TRANGTHAI])                                                
                                   VALUES
                                         (?,?,?)
                     """;
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, ett.getMaSp());
        ps.setObject(2, ett.getTenSp());
        ps.setObject(3, ett.isTrangThai());

        if (ps.executeUpdate() < 1) {
            return "Thêm thất bại";
        }
    } catch (SQLException e) {
        throw new RuntimeException("Lỗi khi thêm sản phẩm: " + e.getMessage());
    }
    return "Thêm thành công";
}

 
    public String deleteEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public String updateEntity(SanPham ett, int id) {
        String sqlUpdate = """
                           UPDATE [dbo].[SANPHAM]
                                    SET [MASANPHAM] = ?
                                       ,[TENSP] = ?
                                       ,[TRANGTHAI] = ?
                            WHERE IDSANPHAM = ?
                           """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
            ps.setObject(1, ett.getMaSp());
            ps.setObject(2, ett.getTenSp());
            ps.setObject(3, ett.isTrangThai());
            ps.setObject(4, id);
            if (ps.executeUpdate() < 1) {
                return "sua that bai";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "chuong trinh co loi";
        }
        return "sua thanh cong";
    }

    
    public void hiddenEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public void unhiddenEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public List<SanPham> resultPage(int page, int limit) {
        String sql = "SELECT * FROM [dbo].[SanPham] ORDER BY idSanPham desc "
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * limit);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            List<SanPham> list = new ArrayList<>();
            while (rs.next()) {
                SanPham sp = new SanPham();
                sp.setId(rs.getInt(1));
                sp.setMaSp(rs.getString(2));
                sp.setTenSp(rs.getString(3));
                sp.setTrangThai(rs.getBoolean(4));
                list.add(sp);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getSoLuongByID(int id) {

        try {
            String database = "DUAN01";

            String sql = """
                                  SELECT  sp.IDSANPHAM, MASANPHAM,TenSP,sum(SoLuong) as'SoLuong'
                                                             FROM SANPHAMCHITIET spct join SANPHAM sp on spct.IDSANPHAM = sp.IDSANPHAM
                                                             where sp.IDSANPHAM = ?
                                                             Group by sp.IDSANPHAM, MASANPHAM,TenSP
                                 """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, id);
            int sl = 0;
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                sl = rs.getInt("SoLuong");
                System.out.println(sl);
            }

            return sl;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<SanPham> getTenSp() {
        List<SanPham> list = new ArrayList<>();
        try {
            String sqlSize = "select TenSP from SanPham "
                    + "order by IDSanPHAM DESC";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlSize);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                SanPham s = new SanPham();
                s.setTenSp(rs.getString("TenSP"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<SanPham> search(String keyword) {
     List<SanPham> list = new ArrayList<>();
    try {
        String sqlSearch = "SELECT * FROM SanPham WHERE MASANPHAM LIKE ? OR TENSP LIKE ?";
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(sqlSearch);
        ps.setString(1, "%" + keyword + "%");
        ps.setString(2, "%" + keyword + "%");
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SanPham sp = new SanPham();
            sp.setId(rs.getInt("IDSANPHAM")); // Giả sử cột ID là cột ID của sản phẩm
            sp.setMaSp(rs.getString("MASANPHAM"));
            sp.setTenSp(rs.getString("TENSP"));
            sp.setTrangThai(rs.getBoolean("TRANGTHAI"));
            list.add(sp);
        }
        con.close(); // Đóng kết nối sau khi hoàn thành
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
    }
}
