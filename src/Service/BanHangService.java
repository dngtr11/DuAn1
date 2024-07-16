/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.SP;
import Model.SanPhamChiTiet;
import Repo.DBConnect;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.sql.Date;

import utils.JDBCHelper;

/**
 *
 * @author Dung Tran
 */
public class BanHangService {
    String getAllHD = "select * from HoaDon where TrangThai = 0 and ANTT = 1 order by IDHOADON  desc";
    String getbyHDCT = "select * from HOADONCHITIET where IDHOADON = ? and IDSPCT = ?";
    String getbyIDHOADONCHITIET = "select * from HOADONCHITIET where IDHDCT = ?";
    String getALLHDCT = "select * from HOADONCHITIET where IDHOADON = ? order by IDHDCT desc";
    String getbyIDSANPHAMCHITIET = "select * from SANPHAMCHITIET where IDSPCT = ?";
    String getbyIDHOADON = "select * from HOADON where IDHOADON = ? and ANTT = 1";
    String getbySDTKHACHHANG = "select * from KHACHHANG where SDT = ?";
    String insertHD = """
                      INSERT INTO [dbo].[HoaDon]
                                 ([MAHOADON]
                                 ,[IDKHACHHANG]
                                 ,[IDNHANVIEN]
                                 ,[IDVOUCHER]
                                 ,[IDHDCT]
                                 ,[TONGTIEN]
                                 ,[NGAYTHANHTOAN]
                                 ,[NGAYTAO]
                                 ,[NGUOITAO]
                                 ,[TRANGTHAI]
                                 ,[ANTT])
                           VALUES(?,null,null,null,null,0,null,getdate(),null,0,1)
                      """;
     String InsertHDCT = "INSERT INTO [dbo].[HOADONCHITIET]\n"
            + "           ([IDHOADON]\n"
            + "           ,[IDSPCT]\n"
            + "           ,[SOLUONG]\n"
            + "           ,[GIA]\n"
            + "           ,[THANHTIEN]\n"
            + "           ,[NGUOITAO]\n"
            + "           ,[NGUOISUA]\n"
            + "           ,[NGAYTAO]\n"
            + "           ,[NGAYSUA])\n"
            + "     VALUES(?,?,?,?,NULL,NULL,NULL,getdate(),NULL)";
     String InsertKH = "INSERT INTO [dbo].[KHACHHANG]\n"
            + "           ([MAKHACHHANG]\n"
            + "           ,[HOTEN]\n"
            + "           ,[GIOITNH]\n"
            + "           ,[SDT]\n"
            + "           ,[NGAYSINH]\n"
            + "           ,[EMAIL]\n"
            + "           ,[NGAYTAO]\n"
            + "           ,[NGAYSUA]\n"
            + "           ,[NGUOITAO]\n"
            + "           ,[NGUOISUA]\n"
            + "           ,[TRANGTHAI])\n"
            + "     VALUES(?,?,NULL,?,NULL,NULL,getdate(),NULL,NULL,NULL,1)";
     String UpdateKHvaoHD = "Update [dbo].[HOADON]\n"
            + "SET [IDKHACHHANG] = ? where IDHOADON = ?";
      String UpdateSPCT = "Update [dbo].[SANPHAMCHITIET]\n"
            + "SET [SOLUONG] = ? where IDSPCT = ?";
       String UpdateTongTien = "UPDATE [dbo].[HOADON]\n"
            + "   SET [TONGTIEN] = ?\n"
            + " WHERE IDHOADON = ?";
       String UpdateHDCT = "Update [dbo].[HOADONCHITIET]\n"
            + "SET [SOLUONG] = ? where IDHDCT = ?";
        String UpdateThanhToan = "UPDATE [dbo].[HOADON]\n"
            + "   SET [TRANGTHAI] = 1\n"
            + "      ,[TONGTIEN] = ?\n"
            + "      ,[NGAYTHANHTOAN] = getdate()\n"
            + " WHERE IDHOADON = ?";
       String DeleteHDCT = "DELETE [dbo].[HOADONCHITIET]\n"
            + "where IDHDCT = ?";
       String HiddenHD = "Update [dbo].[HOADON]\n"
            + "SET [ANTT] = 0 where IDHOADON = ?";
    public List<HoaDon> selectbySQLHD(String Sql, Object... orgs){
        try {
            List<HoaDon> listHoaDon = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(Sql, orgs);
            while (rs.next()) {                
                HoaDon hd = new HoaDon();
                hd.setIdHoaDon(rs.getInt("IDHOADON"));
                hd.setIdKhachHang(rs.getInt("IDKHACHHANG"));
                hd.setIdNhanVien(rs.getInt("IDNHANVIEN"));
                hd.setIdVoucher(rs.getInt("IDVOUCHER"));
                hd.setMaHoaDon(rs.getString("MAHOADON"));
                hd.setTongTien(rs.getDouble("TONGTIEN"));
                hd.setNgaytao(rs.getDate("NGAYTAO"));
                hd.setNgayThanhToan(rs.getDate("NGAYTHANHTOAN"));
                hd.setNguoiTao(rs.getString("NGUOITAO"));
                hd.setTrangThai(rs.getBoolean("TRANGTHAI"));
                listHoaDon.add(hd);
            }
            return listHoaDon;
        } catch (Exception e) {
                throw new RuntimeException(e);
        }
    }
    public List<HoaDon> getAllHD(){
        return selectbySQLHD(getAllHD);
    }
    
    public void addHoaDon(String MaHD){
        JDBCHelper.excuteUpdate(insertHD, MaHD);
    }
    public List<HoaDonChiTiet> selectbySQLHDCT(String Sql, Object... orgs) {
        try {
            List<HoaDonChiTiet> listHoaDonChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(Sql, orgs);
            while (rs.next()) {
                HoaDonChiTiet hdct = new HoaDonChiTiet();
                hdct.setIDHoaDonChiTiet(rs.getInt("IDHDCT"));
                hdct.setIDHoaDon(rs.getInt("IDHOADON"));
                hdct.setIDHSanPhamChiTiet(rs.getInt("IDSPCT"));
                hdct.setSoLuong(rs.getInt("SOLUONG"));
                hdct.setGia(rs.getDouble("GIA"));
                hdct.setThanhTien(rs.getDouble("THANHTIEN"));
                hdct.setNguoiTao(rs.getString("NGUOITAO"));
                hdct.setNgayTao(rs.getDate("NGAYTAO"));
                hdct.setNgaySua(rs.getDate("NGAYSUA"));
                listHoaDonChiTiet.add(hdct);
            }
            return listHoaDonChiTiet;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     public SanPhamChiTiet getbyIDSANPHAMCHITIET(int id) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getbyIDSANPHAMCHITIET, id);
            while (rs.next()) {
                SanPhamChiTiet cl = new SanPhamChiTiet();
                cl.setId(rs.getInt(1));
                cl.setIdSp(rs.getInt(2));
                cl.setIdMs(rs.getInt(3));
                cl.setIdCl(rs.getInt(4));
                cl.setIdSize(rs.getInt(5));
                cl.setIdThuongHieu(rs.getInt(6));
                cl.setMoTa(rs.getString(9));
                cl.setSoLuong(rs.getInt(7));
                cl.setGia(rs.getFloat(8));
                cl.setNguoiTao(rs.getString(10));
                cl.setNguoiSua(rs.getString(11));
                cl.setNgayTao(rs.getDate(12));
                cl.setNgaySua(rs.getDate(13));
                cl.setTrangThai(rs.getBoolean(14));
                
                listSanPhamChiTiet.add(cl);
            }
            if (listSanPhamChiTiet.isEmpty()) {
                return null;
            }
            return listSanPhamChiTiet.get(0);
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
     public HoaDon getbyID(int id) {
        List<HoaDon> list = selectbySQLHD(getbyIDHOADON, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     
    public HoaDonChiTiet getbyIDHDCT(int IDHDCT) {
        List<HoaDonChiTiet> list = selectbySQLHDCT(getbyIDHOADONCHITIET, IDHDCT);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public HoaDonChiTiet getbyHDCT(int IDHD, int IDSPCT) {
        List<HoaDonChiTiet> list = selectbySQLHDCT(getbyHDCT, IDHD, IDSPCT);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
     public void addHoaDonChiTiet(int IDHD, int IDSPCT, int SL, double Gia) {
//        String InsertHDCT = "INSERT INTO [dbo].[HOADONCHITIET]\n"
//            + "           ([IDHOADON]\n"
//            + "           ,[IDSPCT]\n"
//            + "           ,[SOLUONG]\n"
//            + "           ,[GIA]\n"
//            + "           ,[THANHTIEN]\n"
//            + "           ,[NGUOITAO]\n"
//            + "           ,[NGUOISUA]\n"
//            + "           ,[NGAYTAO]\n"
//            + "           ,[NGAYSUA])\n"
//            + "     VALUES(?,?,?,?,NULL,NULL,NULL,getdate(),NULL)";
        JDBCHelper.excuteUpdate(InsertHDCT, IDHD, IDSPCT, SL, Gia);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
      public List<HoaDonChiTiet> getALLHDCT(int id) {
        return selectbySQLHDCT(getALLHDCT, id);
    }
      public void UPDATESPCT(int SL, int id) {
        JDBCHelper.excuteUpdate(UpdateSPCT, SL, id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
      public void UPDATETongTien(double TongTien, int id) {
        JDBCHelper.excuteUpdate(UpdateTongTien, TongTien, id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
      public ArrayList<SP> getAllSP(){
        ArrayList<SP> lst = new ArrayList<>();
        String sql = "select sct.IDSPCT,s.TENSP,m.TENMAU,z.TENSIZE,c.TENCHATLIEU,t.TENTHUONGHIEU,SOLUONG,GIA from SanPhamChiTiet sct \n" +
"join SanPham s on s.IDSANPHAM= sct.IDSANPHAM\n" +
"join MauSac m on m.IDMAUSAC = sct.IDMAUSAC\n" +
"join ChatLieu c on c.IDCHATLIEU = sct.IDCHATLIEU\n" +
"join Size z on z.IDSIZE = sct.IDSIZE\n" +
"join ThuongHieu t on t.IDTHUONGHIEU = sct.IDTHUONGHIEU";
        Connection c = DBConnect.getConnection();
        try {
            PreparedStatement pstm = c.prepareStatement(sql);
            ResultSet rs = pstm.executeQuery();
            while(rs.next()){
                SP bh = new SP();
                bh.setId(rs.getInt("IDSPCT"));
                bh.setTen(rs.getString("TENSP"));
                bh.setMauS(rs.getString("TENMAU"));
                bh.setKt(rs.getString("TENSIZE"));
                bh.setChatLieu(rs.getString("TENCHATLIEU"));
                bh.setThuongHieu(rs.getString("TENTHUONGHIEU"));
                bh.setSoLuong(rs.getInt("SOLUONG"));
                bh.setGia(rs.getDouble("GIA"));
                
                lst.add(bh);
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return lst;
    }
 public List<SP> find(String ten) {
        List<SP> lstTK = new ArrayList<>();
        
        String sql = "SELECT sct.IDSPCT, s.TENSP, m.TENMAU, c.TENCHATLIEU, z.TENSIZE, t.TENTHUONGHIEU, SOLUONG, GIA " +
                     "FROM SanPhamChiTiet sct " +
                     "JOIN SanPham s ON s.IDSANPHAM = sct.IDSANPHAM " +
                     "JOIN MauSac m ON m.IDMAUSAC = sct.IDMAUSAC " +
                     "JOIN ChatLieu c ON c.IDCHATLIEU = sct.IDCHATLIEU " +
                     "JOIN Size z ON z.IDSIZE = sct.IDSIZE " +
                     "JOIN ThuongHieu t ON t.IDTHUONGHIEU = sct.IDTHUONGHIEU " +
                     "WHERE s.TENSP LIKE ?";
        
        try (Connection cn = DBConnect.getConnection();
             PreparedStatement pstm = cn.prepareStatement(sql)) {
             
            pstm.setString(1, "%" + ten + "%"); // Sử dụng % để tìm kiếm tất cả các giá trị chứa mã ma
            try (ResultSet rs = pstm.executeQuery()) {
                while (rs.next()) {
                    SP bh = new SP();
                    bh.setId(rs.getInt("IDSPCT"));
                    bh.setTen(rs.getString("TENSP"));
                    bh.setMauS(rs.getString("TENMAU"));
                    bh.setKt(rs.getString("TENSIZE"));
                    bh.setChatLieu(rs.getString("TENCHATLIEU"));
                    bh.setThuongHieu(rs.getString("TENTHUONGHIEU"));
                    bh.setSoLuong(rs.getInt("SOLUONG"));
                    bh.setGia(rs.getDouble("GIA"));
                    
                    lstTK.add(bh); // Thêm đối tượng vào danh sách
                }
            }
        } catch (Exception e) {
e.printStackTrace();
        }
        
        return lstTK;
 }
 public void addKhachHang(String MaKH,String TenKH,String SDT) {
        JDBCHelper.excuteUpdate(InsertKH, MaKH,TenKH,SDT);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 public void UPDATEKhachHang(int IDKH, int IDHD) {
        JDBCHelper.excuteUpdate(UpdateKHvaoHD, IDKH, IDHD);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 public void deleteHDCT(int id) {
        JDBCHelper.excuteUpdate(DeleteHDCT, id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 public List<HoaDon> getALLHD() {
        return selectbySQLHD(getAllHD);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 public void deleteHD(int id) {
        JDBCHelper.excuteUpdate(HiddenHD, id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 public void UPDATEHDCT(int SL, int id) {
        JDBCHelper.excuteUpdate(UpdateHDCT, SL, id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 public void UPDATEThanhToan(double TongTien, int id) {
        JDBCHelper.excuteUpdate(UpdateThanhToan, TongTien, id);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
 public KhachHang getbySDTKHACHHANG(String SDT) {
        try {
            List<KhachHang> listKhachHang = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getbySDTKHACHHANG, SDT);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
                kh.setId(rs.getInt("IDKHACHHANG"));
                kh.setMaKH(rs.getString("MAKHACHHANG"));
                kh.setTenKH(rs.getString("HOTEN"));
                kh.setSdt(rs.getString("SDT"));
                kh.setNgaySinh(rs.getDate("NGAYSINH"));
                kh.setEmail(rs.getString("EMAIL"));
                kh.setGioiTinh(rs.getBoolean("GIOITNH"));
                listKhachHang.add(kh);
            }
            if (listKhachHang.isEmpty()) {
                return null;
            }
//            return listHoaDon;
            return listKhachHang.get(0);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
