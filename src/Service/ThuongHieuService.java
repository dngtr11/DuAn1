/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.MauSac;
import Model.ThuongHieu;
import Repo.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dung Tran
 */
public class ThuongHieuService {
    public List<ThuongHieu> selectbySQL(String Sql, Object... orgs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public ArrayList<ThuongHieu> getALL() {
        ArrayList<ThuongHieu> listThuongHieu = new ArrayList<>();
        try {
           
            String sql = "select * from ThuongHieu";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setId(rs.getInt("IDTHUONGHIEU"));
                th.setMaTH(rs.getString("MATHUONGHIEU"));
                th.setTenTH(rs.getString("TENTHUONGHIEU"));
                th.setTrangThai(rs.getBoolean("TRANGTHAI"));
                listThuongHieu.add(th);
            }
            
        } catch (SQLException ex) {
            
        }
        return listThuongHieu;
    }


    public List<MauSac> getALLresultPage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<MauSac> getbyEntityhidden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public List<MauSac> findEntity(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<MauSac> findEntityhidden(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public ThuongHieu getbyID(int id) {
        List<ThuongHieu> listbyid = new ArrayList<>();
        try {
            String GET_BY_ID = """
                                       select * from ThuongHieu where IDTHUONGHIEU = ?
                                       """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setId(rs.getInt(1));
                th.setMaTH(rs.getString(2));
                th.setTenTH(rs.getString(3));
                th.setTrangThai(rs.getBoolean(4));
                listbyid.add(th);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listbyid.get(0);
    }
    
    public ThuongHieu getbyName(String name) {
        List<ThuongHieu> listbyid = new ArrayList<>();
        try {
            String GET_BY_ID = """
                                       select * from ThuongHieu where TENTHUONGHIEU = ?
                                       """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID);
            ps.setObject(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setId(rs.getInt(1));
                th.setMaTH(rs.getString(2));
                th.setTenTH(rs.getString(3));
                th.setTrangThai(rs.getBoolean(4));
                listbyid.add(th);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listbyid.get(0);
    }


    
    public String addEntity(ThuongHieu ett) {
       List<ThuongHieu> listThuongHieu = new ArrayList<>();
        try {
            String sql = """
                                 INSERT INTO [dbo].[ThuongHieu]
                                                       ([MATHUONGHIEU]
                                                       ,[TENTHUONGHIEU]
                                                       ,[TRANGTHAI])
                                      VALUES
                                            (?,?,?)
                                 """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ett.getMaTH());
            ps.setObject(2,ett.getTenTH());
            ps.setObject(3, ett.isTrangThai());
            if (ps.executeUpdate() < 1) {
                return "add that bai";
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return "Add Thành Công";
    }

    
    public String deleteEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public String updateEntity(ThuongHieu ett, int id) {
        String sqlUpdate = """
                           UPDATE [dbo].[ThuongHieu]
                                    SET [MATHUONGHIEU] = ?
                                       ,[TENTHUONGHIEU] = ?
                                       ,[TRANGTHAI] = ?
                            WHERE IDTHUONGHIEU = ?
                           """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
            ps.setObject(1, ett.getMaTH());
            ps.setObject(2,ett.getTenTH());
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

   
    public List<MauSac> resultPage(int page, int limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     public ArrayList<ThuongHieu> getCbbTh() {
        ArrayList<ThuongHieu> list = new ArrayList<>();
        try {
            String sqlMs = "select TENTHUONGHIEU from ThuongHieu order by IDTHUONGHIEU DESC";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlMs);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ThuongHieu th = new ThuongHieu();
                th.setTenTH(rs.getString("TENTHUONGHIEU"));
                list.add(th);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
