/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Size;
import Repo.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 *
 * @author anh
 */
public class SizeService {

    public List<Size> selectbySQL(String Sql, Object... orgs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public ArrayList<Size> getALL() {
        ArrayList<Size> listSize = new ArrayList<>();
        try {
           
            String sql = "select * from Size";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size s = new Size();
                s.setId(rs.getInt("IDSIZE"));
                s.setMaS(rs.getString("MASIZE"));
                s.setTenS(rs.getString("TENSIZE"));
                s.setTrangThai(rs.getBoolean("TRANGTHAI"));
                listSize.add(s);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return listSize;
    }

   
    public List<Size> getALLresultPage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<Size> getbyEntityhidden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<Size> findEntity(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public List<Size> findEntityhidden(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public Size getbyID(int id) {
        List<Size> listById = new ArrayList<>();
        try {
            String GET_BY_ID = """
                                       select * from Size where IDSIZE = ?
                                       """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size s = new Size();
                s.setId(rs.getInt(1));
                s.setMaS(rs.getString(2));
                s.setTenS(rs.getString(3));
                s.setTrangThai(rs.getBoolean(4));
                listById.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listById.get(0);
    }
    
     public Size getbyName(String name) {
        List<Size> listById = new ArrayList<>();
        try {
            String GET_BY_ID = """
                                       select * from Size where TENSIZE = ?
                                       """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID);
            ps.setObject(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size s = new Size();
                s.setId(rs.getInt(1));
                s.setMaS(rs.getString(2));
                s.setTenS(rs.getString(3));
                s.setTrangThai(rs.getBoolean(4));
                listById.add(s);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listById.get(0);
    }

    
    public String addEntity(Size ett) {
        List<Size> listS = new ArrayList<>();
        try {
            String sql = """
                                 INSERT INTO [dbo].[SIZE]
                                            ([MASIZE]
                                            ,[TENSIZE]
                                            ,[TRANGTHAI])
                                      VALUES
                                            (?,?,?)
                                 """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ett.getMaS());
            ps.setObject(2, ett.getTenS());
            ps.setObject(3, ett.isTrangThai());
            if (ps.executeUpdate() < 1) {
                return "add that bai";
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Add Thành Công";
    }

    
    public String deleteEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public String updateEntity(Size ett, int id) {
        String sqlUpdate = """
                           UPDATE [dbo].[SIZE]
                              SET [MASIZE] = ?
                                 ,[TENSIZE] = ?
                                 ,[TRANGTHAI] = ?
                            WHERE IDSIZE = ?
                           """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
            ps.setObject(1, ett.getMaS());
            ps.setObject(2, ett.getTenS());
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

   
    public List<Size> resultPage(int page, int limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public ArrayList<Size> getCbbSize() {
        ArrayList<Size> list = new ArrayList<>();
        try {
            String sqlMs = "select TENSIZE from Size "
                    + "order by IDSIZE DESC";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlMs);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Size ms = new Size();
                ms.setTenS(rs.getString("TENSIZE"));
                list.add(ms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
