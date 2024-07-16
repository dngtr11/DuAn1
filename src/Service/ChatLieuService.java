/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.ChatLieu;
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
public class ChatLieuService {


    public List<ChatLieu> selectbySQL(String Sql, Object... orgs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public ArrayList<ChatLieu> getALL() {
        ArrayList<ChatLieu> listKieuMu = new ArrayList<>();
        try {
            
            String sql = "select * from ChatLieu";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu cl = new ChatLieu();
                cl.setId(rs.getInt("IDCHATLIEU"));
                cl.setMaCl(rs.getString("MACHATLIEU"));
                cl.setTenCL(rs.getString("TENCHATLIEU"));
                cl.setTrangThai(rs.getBoolean("TRANGTHAI"));
                listKieuMu.add(cl);
            }
            } catch (SQLException e) {
                throw new RuntimeException(e);
        }
        return listKieuMu;
    }

 
    public List<ChatLieu> getALLresultPage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public List<ChatLieu> getbyEntityhidden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<ChatLieu> findEntity(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<ChatLieu> findEntityhidden(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public ChatLieu getbyID(int id) {
        List<ChatLieu> listById = new ArrayList<>();
        try {
            String GET_BY_ID = """
                                       select * from ChatLieu where IDCHATLIEU = ?
                                       """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID);
            ps.setObject(1, id);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu mu = new ChatLieu();
                mu.setId(rs.getInt(1));
                mu.setMaCl(rs.getString(2));
                mu.setTenCL(rs.getString(3));
                mu.setTrangThai(rs.getBoolean(4));
                listById.add(mu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listById.get(0);
    }
    
     public ChatLieu getbyName(String name) {
        List<ChatLieu> listById = new ArrayList<>();
        try {
            String GET_BY_ID = """
                                       select * from ChatLieu where TENCHATLIEU = ?
                                       """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID);
            ps.setObject(1, name);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu mu = new ChatLieu();
                mu.setId(rs.getInt(1));
                mu.setMaCl(rs.getString(2));
                mu.setTenCL(rs.getString(3));
                mu.setTrangThai(rs.getBoolean(4));
                listById.add(mu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return listById.get(0);
    }

  
    public String addEntity(ChatLieu ett) {
        List<ChatLieu> listS = new ArrayList<>();
        try {
            String sql = """
                                 INSERT INTO [dbo].[CHATLIEU]
                                            ([MACHATLIEU]
                                            ,[TENCHATLIEU]
                                            ,[TRANGTHAI])
                                      VALUES
                                            (?,?,?)
                                 """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ps.setObject(1, ett.getMaCl());
            ps.setObject(2, ett.getTenCL());
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

   
    public String updateEntity(ChatLieu ett, int id) {
        String sqlUpdate = """
                           UPDATE [dbo].[CHATLIEU]
                              SET [MACHATLIEU] = ?
                                 ,[TENCHATLIEU] = ?
                                 ,[TRANGTHAI] = ?
                            WHERE IDCHATLIEU = ?
                           """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
            ps.setObject(1, ett.getMaCl());
            ps.setObject(2, ett.getTenCL());
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

    public List<ChatLieu> resultPage(int page, int limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public ArrayList<ChatLieu> getCbbCl() {
        ArrayList<ChatLieu> list = new ArrayList<>();
        try {
            String sqlMs = "select TENCHATLIEU from ChatLieu "
                    + "order by IDCHATLIEU DESC";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlMs);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                ChatLieu ms = new ChatLieu();
                ms.setTenCL(rs.getString("TENCHATLIEU"));
                list.add(ms);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }
}
