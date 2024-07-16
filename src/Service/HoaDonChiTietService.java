package Service;

import Model.HoaDonChiTiet;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import utils.JDBCHelper;

/**
 *
 * @author Admin
 */
public class HoaDonChiTietService {
    String getALL = "select * from HOADONCHITIET where IDHOADON = ?";

    
    public List<HoaDonChiTiet> selectbySQL(String Sql, Object... orgs) {
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

  
    public List<HoaDonChiTiet> getALL() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<HoaDonChiTiet> getHDCTinHD(int id) {
        return selectbySQL(getALL, id);
    }

    public List<HoaDonChiTiet> getALLresultPage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HoaDonChiTiet> getbyEntityhidden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HoaDonChiTiet> findEntity(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public List<HoaDonChiTiet> findEntityhidden(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public HoaDonChiTiet getbyID(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void addEntityChien(HoaDonChiTiet ett) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void deleteEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void updateEntity(HoaDonChiTiet ett, int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void hiddenEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public void unhiddenEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public int resultPage(int page, int limit) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    public String addEntityNgocAnh(HoaDonChiTiet ett) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
