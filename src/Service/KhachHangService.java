/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.KhachHang;
import Repo.DBConnect;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

/**
 *
 * @author Dung Tran
 */
public class KhachHangService {
    Connection con = null;
    PreparedStatement ps = null;
    ResultSet rs = null;
    String sql = null;
    public List<KhachHang> getAll(){
        List<KhachHang> List = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = """
                  SELECT 
                        [MAKHACHHANG]
                        ,[HOTEN]
                        ,[GIOITINH]
                        ,[SDT]
                        ,[NGAYSINH]
                        ,[EMAIL]
                    FROM [dbo].[KhachHang] order by idkhachhang desc
                  """;
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {                
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setTenKH(rs.getString(2));
                kh.setGioiTinh(rs.getBoolean(3));
                kh.setSdt(rs.getString(4));
                kh.setNgaySinh(rs.getDate(5));
                kh.setEmail(rs.getString(6));
                List.add(kh);
            }
            return List;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public KhachHang getOne(String maKH) {

        KhachHang kh = null;
        try {
            con = DBConnect.getConnection();
            sql = "select [MAKHACHHANG],[HOTEN], [SDT], [NGAYSINH], [EMAIL],[GIOITNH] from KHACHHANG where [MAKHACHHANG]=?";
            ps = con.prepareStatement(sql);
            ps.setObject(1, maKH);
            rs = ps.executeQuery();
            while (rs.next()) {
                kh = new KhachHang(rs.getString(1),
                         rs.getString(2),
                         rs.getString(3),
                         rs.getDate(4),
                         rs.getString(5),
                         rs.getBoolean(6));
            }
            return kh;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public int add(KhachHang kh){
        
        try {
            con = DBConnect.getConnection();
            String sql = """
                     INSERT INTO [dbo].[KhachHang]
                                ([MAKHACHHANG]
                                ,[HOTEN]
                                ,[GIOITINH]
                                ,[SDT]
                                ,[NGAYSINH]
                                ,[EMAIL])
                          VALUES (?,?,?,?,?,?)
                     """;
            ps = con.prepareStatement(sql);
            ps.setObject(1, kh.getMaKH());
            ps.setObject(2, kh.getTenKH());
            ps.setObject(3, kh.isGioiTinh());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getNgaySinh());
            ps.setObject(6, kh.getEmail());
            return ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        
    }
    public int update(KhachHang kh, int idKH){
        try {
            con = DBConnect.getConnection();
//            sql = "update KHACHHANG set MAKHACHHANG =?, HOTEN = ? , GIOITNH =?,SDT =?,"
//                    + "NGAYSINH = CAST (? AS Date),EMAIL=? where IDKHACHHANG = ?";
            sql = """
                  UPDATE [dbo].[KhachHang]
                     SET [MAKHACHHANG] = ?
                        ,[HOTEN] = ?
                        ,[GIOITINH] = ?
                        ,[SDT] = ?
                        ,[NGAYSINH] = CAST (? AS Date)
                        ,[EMAIL] = ?
                  where IDKHACHHANG = ?
                  """;
            ps = con.prepareStatement(sql);
            ps.setObject(7, idKH);
            ps.setObject(1, kh.getMaKH());
            ps.setObject(2, kh.getTenKH());
            ps.setObject(3, kh.isGioiTinh());
            ps.setObject(4, kh.getSdt());
            ps.setObject(5, kh.getNgaySinh());
            ps.setObject(6, kh.getEmail());
            ps.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            
        }
        return 0;
    }
    public List<KhachHang> search(String keyword) {
        List<KhachHang> listKhachHang = new ArrayList<>();
        try {
            con = DBConnect.getConnection();
            sql = "SELECT \n" +
"                        [MAKHACHHANG]\n" +
"                        ,[HOTEN]\n" +
"                        ,[GIOITINH]\n" +
"                        ,[SDT]\n" +
"                        ,[NGAYSINH]\n" +
"                        ,[EMAIL]\n" +
"                    FROM [dbo].[KhachHang] "
                    + "where [HOTEN] like ? or [SDT] like ?  ";
            ps = con.prepareStatement(sql);
            ps.setObject(1, '%' + keyword + '%');
            ps.setObject(2, '%' + keyword + '%');
            rs = ps.executeQuery();
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setMaKH(rs.getString(1));
                kh.setTenKH(rs.getString(2));
                kh.setGioiTinh(rs.getBoolean(3));
                kh.setSdt(rs.getString(4));
                kh.setNgaySinh(rs.getDate(5));
                kh.setEmail(rs.getString(6));

                listKhachHang.add(kh);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return listKhachHang;

    }
//    public ArrayList<KhachHang> getAll() {
//    ArrayList<KhachHang> list = new ArrayList<>();
//    String sql = "select IDKHACHHANG,MAKHACHHANG,SDT,HOTEN from KhachHang";
//    try {
//        java.sql.Connection cn = DBConnect.getConnection();
//        PreparedStatement ps = cn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            KhachHang kh = new KhachHang();
//            // Gán giá trị từ cơ sở dữ liệu cho các thuộc tính của đối tượng Voucher
//            kh.setTenKH(rs.getString("HOTEN"));
//            kh.setSdt(rs.getString("SDT"));
//            // Thêm đối tượng Voucher đã được gán giá trị vào danh sách
//            list.add(kh);
//        }
//    } catch (Exception e) {
//        e.printStackTrace(); // In ra thông tin lỗi nếu có
//    }
//    return list;
//}
//     public Integer addKhachHang(KhachHang KH) {
//        Integer row = null;
//        try {
//            String sql = "insert into KhachHang(MAKHACHHANG,HOTEN,SDT,GIOITINH) values (?,?,?,?)";
//            Connection con = DBConnect.getConnection();
//            PreparedStatement pstm = con.prepareStatement(sql);
//            pstm.setString(1, KH.getMaKH());
//            pstm.setString(2, KH.getTenKH());
//            pstm.setString(3, KH.getSdt());
//            pstm.setString(4, KH.getTenKH());
//            row = pstm.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("loi add khach hang");
//        }
//        return row;
//    }
//
//    public Integer updateKhachHang(KhachHang KH, int IdKH) {
//        Integer row = null;
//        try {
//            String sql = "update KhachHang set MAKHACHHANG = ?,HOTEN=?,SDT=?,GIOITINH=? where IDKHACHHANG=?";
//            Connection con = DBConnect.getConnection();
//            PreparedStatement pstm = con.prepareStatement(sql);
//
//            pstm.setInt(1, IdKH);
//            pstm.setString(2, KH.getMaKH());
//            pstm.setString(3, KH.getTenKH());
//            pstm.setString(4, KH.getSdt());
//            pstm.setString(5, KH.getTenKH());
//
//            row = pstm.executeUpdate();
//        } catch (Exception e) {
//            e.printStackTrace();
//            System.out.println("loi sua Khach hang");
//        }
//        return row;
//    }
//        
   

}
