/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.HoaDon;
import Model.KhachHang;
import Model.SanPhamChiTiet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import utils.JDBCHelper;

/**
 *
 * @author Dung Tran
 */
public class HoaDonService {
    String getALL = "select * from HOADON where ANTT = 1";
     String getbyIDHOADONCHITIET = "select * from HOADONCHITIET where IDHDCT = ?";
     String getbyIDSANPHAMCHITIET = "select * from SANPHAMCHITIET where IDSPCT = ?";
     String getALLDaTT = "select * from HOADON where TRANGTHAI = 1 order by NGAYTHANHTOAN desc";
     String getALLChuaTT = "select * from HOADON where TRANGTHAI = 0";
     String getbyIDHOADON = "select * from HOADON where IDHOADON = ?";
     String getbyIDKHACHHANG = "select * from KHACHHANG where IDKHACHHANG = ?";
     String getcoutSP = "select COUNT(*) as 'id' from SANPHAM";
    String getcoutNV = "select COUNT(*) as 'id' from NHANVIEN";
    String getcoutKH = "select COUNT(*) as 'id' from KHACHHANG";
    String getsumDT = "select SUM(TONGTIEN) as 'id' from HOADON where TRANGTHAI = 1";
    String getday = "select DAY(NGAYTHANHTOAN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ?\n"
            + "group by DAY(NGAYTHANHTOAN)";
    String getmonth = "select MONTH(NGAYTHANHTOAN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and YEAR(NGAYTHANHTOAN) = ?\n"
            + "group by MONTH(NGAYTHANHTOAN)";
    String getyear = "select YEAR(NGAYTHANHTOAN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1\n"
            + "group by YEAR(NGAYTHANHTOAN)";
    String getSLandDTNgay = "select SUM(SOLUONG),SUM(TONGTIEN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ? and DAY(NGAYTHANHTOAN) = ?";
    String getSLandDTThang = "select SUM(SOLUONG),SUM(TONGTIEN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ?";
    String getSLandDTNam = "select SUM(SOLUONG),SUM(TONGTIEN)\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and YEAR(NGAYTHANHTOAN) = ?";
    String getSLSPNgay = "select HOADON.IDHOADON\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ? and DAY(NGAYTHANHTOAN) = ?\n"
            + "group by HOADON.IDHOADON";
    String getSLSPThang = "select HOADON.IDHOADON\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and MONTH(NGAYTHANHTOAN) = ? and YEAR(NGAYTHANHTOAN) = ?\n"
            + "group by HOADON.IDHOADON";
    String getSLSPNam = "select HOADON.IDHOADON\n"
            + "from HOADON join HOADONCHITIET on HOADON.IDHOADON = HOADONCHITIET.IDHOADON \n"
            + "where TRANGTHAI = 1 and YEAR(NGAYTHANHTOAN) = ?\n"
            + "group by HOADON.IDHOADON";
    public SanPhamChiTiet getbyIDSANPHAMCHITIET(int id) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getbyIDSANPHAMCHITIET, id);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setIdSp(rs.getInt("IDSANPHAM"));
                spct.setMoTa(rs.getString("MOTA"));
                spct.setGia(rs.getFloat("GIA"));
                spct.setSoLuong(rs.getInt("SOLUONG"));
//                spct.setEmail(rs.getString("EMAIL"));
//                spct.setGioiTinh(rs.getBoolean("GIOITNH"));
                listSanPhamChiTiet.add(spct);
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
    public KhachHang getbyIDKHACHHANG(int id) {
        try {
            List<KhachHang> listKhachHang = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getbyIDKHACHHANG, id);
            while (rs.next()) {
                KhachHang kh = new KhachHang();
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
            return listKhachHang.get(0);
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
    public List<HoaDon> selectbySQL(String Sql, Object... orgs) {
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
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public HoaDon getbyID(int id) {
        List<HoaDon> list = selectbySQL(getbyIDHOADON, id);
        if (list.isEmpty()) {
            return null;
        }
        return list.get(0);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<HoaDon> getALL() {
        return selectbySQL(getALL);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<HoaDon> getALLDaTT() {
        return selectbySQL(getALLDaTT);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<HoaDon> getALLChuaTT() {
        return selectbySQL(getALLChuaTT);
        //throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    public List<SanPhamChiTiet> getday(int month, int year) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getday, month, year);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt(1));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<SanPhamChiTiet> getmonth(int year) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getmonth, year);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt(1));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public List<SanPhamChiTiet> getyear() {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getyear);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt(1));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public SanPhamChiTiet getSLandDTNgay(int month, int year, int day) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLandDTNgay, month, year, day);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt(1));
                spct.setGia(rs.getFloat(2));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet.get(0);
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public SanPhamChiTiet getSLandDTThang(int month, int year) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLandDTThang, month, year);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt(1));
                spct.setGia(rs.getFloat(2));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet.get(0);
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public SanPhamChiTiet getSLandDTNam(int year) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLandDTNam, year);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setId(rs.getInt(1));
                spct.setGia(rs.getFloat(2));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet.get(0);
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public int getSLSPNgay(int month, int year, int day) {
        try {
            int index = 0;
//            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLSPNgay, month, year, day);
            while (rs.next()) {
//                SanPhamChiTiet spct = new SanPhamChiTiet();
//                spct.setId(rs.getInt(1));
////                spct.setGia(rs.getFloat(2));
//                listSanPhamChiTiet.add(spct);
                index++;
            }
            return index;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public int getSLSPThang(int month, int year) {
        try {
            int index = 0;
//            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLSPThang, month, year);
            while (rs.next()) {
//                SanPhamChiTiet spct = new SanPhamChiTiet();
//                spct.setId(rs.getInt(1));
////                spct.setGia(rs.getFloat(2));
//                listSanPhamChiTiet.add(spct);
                index++;
            }
            return index;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public int getSLSPNam(int year) {
        try {
            int index = 0;
//            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(getSLSPNam, year);
            while (rs.next()) {
//                SanPhamChiTiet spct = new SanPhamChiTiet();
//                spct.setId(rs.getInt(1));
////                spct.setGia(rs.getFloat(2));
//                listSanPhamChiTiet.add(spct);
                index++;
            }
            return index;
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public float getcout(String sql) {
        try {
            List<SanPhamChiTiet> listSanPhamChiTiet = new ArrayList<>();
            ResultSet rs = JDBCHelper.executeQuery(sql);
            while (rs.next()) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct.setGia(rs.getFloat(1));
                listSanPhamChiTiet.add(spct);
            }
            return listSanPhamChiTiet.get(0).getGia();
//            return listHoaDon;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    public int getcoutSP() {
        return (int) getcout(getcoutSP);
    }

    public int getcoutNV() {
        return (int) getcout(getcoutNV);
    }

    public int getcoutKH() {
        return (int) getcout(getcoutKH);
    }

    public float getsumDT() {
        return getcout(getsumDT);
    }
}
