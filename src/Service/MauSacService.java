/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.MauSac;
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
public class MauSacService {

    
    public List<MauSac> selectbySQL(String Sql, Object... orgs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public ArrayList<MauSac> getALL() {
        ArrayList<MauSac> listMauSac = new ArrayList<>();
        try {
            
            String sql = "select * from MauSac";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setId(rs.getInt("IDMAUSAC"));
                ms.setMaMs(rs.getString("MAMAU"));
                ms.setTenMs(rs.getString("TENMAU"));
                ms.setTrangThai(rs.getBoolean("TRANGTHAI"));
                listMauSac.add(ms);
            }
            
        } catch (SQLException ex) {
            
        }
        return listMauSac;
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

   
  public MauSac getbyID(int id) {
    MauSac ms = null;
    try {
        String GET_BY_ID = "SELECT * FROM MauSac WHERE IDMAUSAC = ?";
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(GET_BY_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            ms = new MauSac();
            ms.setId(rs.getInt("IDMAUSAC"));
            ms.setMaMs(rs.getString("MAMAU"));
            ms.setTenMs(rs.getString("TENMAU"));
            ms.setTrangThai(rs.getBoolean("TRANGTHAI"));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return ms;
}
  
    public MauSac getbyName(String name) {
    MauSac ms = null;
    try {
        String GET_BY_ID = "SELECT * FROM MauSac WHERE TENMAU = ?";
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(GET_BY_ID);
        ps.setString(1, name);
        ResultSet rs = ps.executeQuery();
        if (rs.next()) {
            ms = new MauSac();
            ms.setId(rs.getInt("IDMAUSAC"));
            ms.setMaMs(rs.getString("MAMAU"));
            ms.setTenMs(rs.getString("TENMAU"));
            ms.setTrangThai(rs.getBoolean("TRANGTHAI"));
        }
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return ms;
}
   

    
    public String addEntity(MauSac ett) {
        List<MauSac> listS = new ArrayList<>();
        try {
            String sql = """
                                 INSERT INTO [dbo].[MAUSAC]
                                            ([MAMAU]
                                            ,[TENMAU]
                                            ,[TRANGTHAI])
                                      VALUES
                                            (?,?,?)
                                 """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ett.getMaMs());
            ps.setObject(2,ett.getTenMs());
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

    
    public String updateEntity(MauSac ett, int id) {
        String sqlUpdate = """
                           UPDATE [dbo].[MAUSAC]
                              SET [MAMAU] = ?
                                 ,[TENMAU] = ?
                                 ,[TRANGTHAI] = ?
                            WHERE IDMAUSAC = ?
                           """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
            ps.setObject(1, ett.getMaMs());
            ps.setObject(2, ett.getTenMs());
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
    public ArrayList<MauSac> getCbbMs() {
        ArrayList<MauSac> list = new ArrayList<>();
        try {
            String sqlMs = "select TENMAU from MauSac order by IDMAUSAC DESC";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlMs);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                MauSac ms = new MauSac();
                ms.setTenMs(rs.getString("TENMAU"));
                list.add(ms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
