/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;


import Model.Voucher;
import Repo.DBConnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;
import java.sql.ResultSet;
import utils.JDBCHelper;

/**
 *
 * @author Dung Tran
 */
public class VoucherService {
    String getAll = "select * from Voucher where TrangThai = 1 order by IDVOUCHER desc";
    String insertVC = "INSERT INTO [dbo].[VOUCHER]\n"
                    + "           ([MAVOUCHER]\n"
                    + "           ,[TENVOUCHER]\n"
                    + "           ,[DONTOITHIEU]\n"
                    + "           ,[MUCGIAMGIA]\n"
                    + "           ,[LOAIVC]\n"
                    + "           ,[SOLUONG]\n"
                    + "           ,[NGAYBATDAU]\n"
                    + "           ,[NGAYKETTHUC]\n"
                    + "           ,[NGAYTAO]\n"
                    + "           ,[TRANGTHAI])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,getdate(),1)";
    String updateSL = "UPDATE [dbo].[VOUCHER]\n"
            + "   SET [SOLUONG] = ?\n"
            + " WHERE MAVOUCHER = ?";
    String updateVC = "UPDATE [dbo].[VOUCHER]\n"
                    + "   SET [MAVOUCHER] = ?\n"
                    + "      ,[TENVOUCHER] = ?\n"
                    + "      ,[DONTOITHIEU] = ?\n"
                    + "      ,[MUCGIAMGIA] = ?\n"
                    + "      ,[LOAIVC] = ?\n"
                    + "      ,[SOLUONG] = ?"
                    + "      ,[NGAYBATDAU] = ?\n"
                    + "      ,[NGAYKETTHUC] = ?\n"
                    + "      ,[NGAYSUA] = getdate()\n"
                    + " WHERE IDVoucher = ?";
    String searchVC = "select * from VOUCHER where (TENVOUCHER like ? "
                    + "or DONTOITHIEU like ? or MUCGIAMGIA like ? "
                    + "or LOAIVC like ? or SOLUONG like ? or MAVOUCHER like ?) and TrangThai = 1";
    
    public List<Voucher> selectbySQLVoucher(String Sql, Object... orgs){
        try {
            List<Voucher> listVoucher = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(Sql, orgs);
            while (rs.next()) {                
                 Voucher vc = new Voucher();
                vc.setId(rs.getInt("IDVOUCHER"));
                vc.setMaVoucher(rs.getString("MAVOUCHER"));
                vc.setTenVoucher(rs.getString("TENVOUCHER"));
                vc.setDonToiThieu(rs.getDouble("DONTOITHIEU"));
                vc.setMucGiamGia(rs.getDouble("MUCGIAMGIA"));
                vc.setLoaiGG(rs.getString("LOAIVC"));
                vc.setSoLuong(rs.getInt("SOLUONG"));
                vc.setNgayBatDau(rs.getDate("NGAYBATDAU"));
                vc.setNgayKetThuc(rs.getDate("NGAYKETTHUC"));
                vc.setNgayTao(rs.getDate("NGAYTAO"));
                vc.setTrangThai(rs.getBoolean("TRANGTHAI"));
                listVoucher.add(vc);
            }
            return listVoucher;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
     public List<Voucher> selectbySQL(String Sql, Object... orgs) {
        try {
            List<Voucher> listVoucher = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(Sql, orgs);
            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setId(rs.getInt("IDVOUCHER"));
                vc.setMaVoucher(rs.getString("MAVOUCHER"));
                vc.setTenVoucher(rs.getString("TENVOUCHER"));
                vc.setDonToiThieu(rs.getDouble("DONTOITHIEU"));
                vc.setMucGiamGia(rs.getDouble("MUCGIAMGIA"));
                vc.setLoaiGG(rs.getString("LOAIVC"));
                vc.setSoLuong(rs.getInt("SOLUONG"));
                vc.setNgayBatDau(rs.getDate("NGAYBATDAU"));
                vc.setNgayKetThuc(rs.getDate("NGAYKETTHUC"));
                vc.setNgayTao(rs.getDate("NGAYTAO"));
                vc.setTrangThai(rs.getBoolean("TRANGTHAI"));
                listVoucher.add(vc);
            }
            return listVoucher;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public  List<Voucher> getAllVoucher(){
        return selectbySQLVoucher(getAll);
    }
   public String addVoucher(Voucher vc) {

        try {
            List<Voucher> listVoucher = new ArrayList<>();
            String database = "DUAN1";
            String Insert = "INSERT INTO [dbo].[VOUCHER]\n"
                    + "           ([MAVOUCHER]\n"
                    + "           ,[TENVOUCHER]\n"
                    + "           ,[DONTOITHIEU]\n"
                    + "           ,[MUCGIAMGIA]\n"
                    + "           ,[LOAIVC]\n"
                    + "           ,[SOLUONG]\n"
                    + "           ,[NGAYBATDAU]\n"
                    + "           ,[NGAYKETTHUC]\n"
                    + "           ,[NGAYTAO]\n"
                    + "           ,[TRANGTHAI])\n"
                    + "     VALUES\n"
                    + "           (?,?,?,?,?,?,?,?,getdate(),1)";

            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(Insert);
            ps.setObject(1, vc.getMaVoucher());
            ps.setObject(2, vc.getTenVoucher());
            ps.setObject(3, vc.getDonToiThieu());
            ps.setObject(4, vc.getMucGiamGia());
            ps.setObject(5, vc.getLoaiGG());
            ps.setObject(6, vc.getSoLuong());
            ps.setObject(7, vc.getNgayBatDau());
            ps.setObject(8, vc.getNgayKetThuc());
            if (ps.executeUpdate() < 1) {
                return "add that bai";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "add thành công";
    }
    public void updateSL(Voucher vc){
        JDBCHelper.excuteUpdate(updateSL,vc.getSoLuong()-1,vc.getMaVoucher());
    }
    public String updateVoucher(Voucher vc, int id) {
        try {
            String database = "DUAN1";
            String Update = "UPDATE [dbo].[VOUCHER]\n"
                    + "   SET [MAVOUCHER] = ?\n"
                    + "      ,[TENVOUCHER] = ?\n"
                    + "      ,[DONTOITHIEU] = ?\n"
                    + "      ,[MUCGIAMGIA] = ?\n"
                    + "      ,[LOAIVC] = ?\n"
                    + "      ,[SOLUONG] = ?"
                    + "      ,[NGAYBATDAU] = ?\n"
                    + "      ,[NGAYKETTHUC] = ?\n"
                    + "      ,[NGAYSUA] = getdate()\n"
                    + " WHERE IDVoucher = ?";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(Update);
            ps.setObject(1, vc.getMaVoucher());
            ps.setObject(2, vc.getTenVoucher());
            ps.setObject(3, vc.getDonToiThieu());
            ps.setObject(4, vc.getMucGiamGia());
            ps.setObject(5, vc.getLoaiGG());
            ps.setObject(6, vc.getSoLuong());
            ps.setObject(7, vc.getNgayBatDau());
            ps.setObject(8, vc.getNgayKetThuc());
            ps.setObject(9, id);
            if (ps.executeUpdate() < 1) {
                return "Sửa that bai";
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return "Sửa thành công";
    }
    public List<Voucher> search(String ma) {
        List<Voucher> list = new ArrayList<>();
        try {
            String Find = "select * from VOUCHER where (TENVOUCHER like ? "
                    + "or DONTOITHIEU like ? or MUCGIAMGIA like ? "
                    + "or LOAIVC like ? or SOLUONG like ? or MAVOUCHER like ?) and TrangThai = 1";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(Find);
            ps.setObject(1, "%" + ma + "%");
            ps.setObject(2, "%" + ma + "%");
            ps.setObject(3, "%" + ma + "%");
            ps.setObject(4, "%" + ma + "%");
            ps.setObject(5, "%" + ma + "%");
            ps.setObject(6, "%" + ma + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Voucher vc = new Voucher();
                vc.setId(rs.getInt("IDVOUCHER"));
                vc.setMaVoucher(rs.getString("MAVOUCHER"));
                vc.setTenVoucher(rs.getString("TENVOUCHER"));
                vc.setDonToiThieu(rs.getDouble("DONTOITHIEU"));
                vc.setMucGiamGia(rs.getDouble("MUCGIAMGIA"));
                vc.setLoaiGG(rs.getString("LOAIVC"));
                vc.setSoLuong(rs.getInt("SOLUONG"));
                vc.setNgayBatDau(rs.getDate("NGAYBATDAU"));
                vc.setNgayKetThuc(rs.getDate("NGAYKETTHUC"));
                vc.setNgayTao(rs.getDate("NGAYTAO"));
                vc.setTrangThai(rs.getBoolean("TRANGTHAI"));
                list.add(vc);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return list;
    }
    public Voucher getbyMaVC(String MaVC) {
        String getbyMaVC = "select * from Voucher where TrangThai = 1 and MAVOUCHER = ?";
        List<Voucher> list = selectbySQL(getbyMaVC, MaVC);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
    }
}
