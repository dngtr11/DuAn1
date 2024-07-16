/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.SanPhamChiTiet;
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
public class SanPhamChiTietService  {

    
    public List<SanPhamChiTiet> selectbySQL(String Sql, Object... orgs) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
    public List<SanPhamChiTiet> getALLByIdSP(int idsp) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            String GET_BY_ID = "SELECT [IDSPCT]\n" +
"                                                                       ,[IDSANPHAM]\n" +
"                                                                       ,[IDCHATLIEU]\n" +
"                                                                       ,[IDTHUONGHIEU]\n" +
"                                                                       ,[IDMAUSAC]\n" +
"                                                                       ,[IDSIZE]\n" +
"                                                                       ,[SOLUONG]\n" +
"                                                                       ,[GIA]\n" +
"                                                                       ,[MOTA]\n" +
"                                                                       ,[NGUOITAO]\n" +
"                                                                       ,[NGUOISUA]\n" +
"                                                                       ,[NGAYTAO]\n" +
"                                                                       ,[NGAYSUA]\n" +
"                                                                       ,[TRANGTHAI]\n" +
"                                                                   FROM [dbo].[SanPhamChiTiet] where IDSANPHAM = ?" +
"                                                                order by idspct desc";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(GET_BY_ID);
            ps.setInt(1, idsp);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
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
                
                list.add(cl);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return list;
    }

   
    public List<SanPhamChiTiet> getALL() {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            String sql = """
                                 SELECT [IDSPCT]
                                               ,[IDSANPHAM]
                                               ,[IDCHATLIEU]
                                               ,[IDTHUONGHIEU]
                                               ,[IDMAUSAC]
                                               ,[IDSIZE]
                                               ,[SOLUONG]
                                               ,[GIA]
                                               ,[MOTA]
                                               ,[NGUOITAO]
                                               ,[NGUOISUA]
                                               ,[NGAYTAO]
                                               ,[NGAYSUA]
                                               ,[TRANGTHAI]
                                           FROM [dbo].[SanPhamChiTiet]
                                        order by idspct desc
                                 """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
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
                
                list.add(cl);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    
    public List<SanPhamChiTiet> getALLresultPage() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<SanPhamChiTiet> getbyEntityhidden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

  
    public List<SanPhamChiTiet> findEntity(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
    public List<SanPhamChiTiet> findEntityhidden(String data) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    
   public SanPhamChiTiet getbyID(int id) {
    SanPhamChiTiet spct = null;
    try {
        String GET_BY_ID = "SELECT * FROM SanPhamChiTiet WHERE IDSPCT = ?";
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(GET_BY_ID);
        ps.setInt(1, id);
        ResultSet rs = ps.executeQuery();
        
        // Kiểm tra xem kết quả truy vấn có dữ liệu hay không
        if (rs.next()) {
            // Tạo một đối tượng SanPhamChiTiet từ dữ liệu truy vấn
            spct = new SanPhamChiTiet();
            spct.setId(rs.getInt(1));
            spct.setIdSp(rs.getInt(2));
            spct.setIdMs(rs.getInt(3));
            spct.setIdCl(rs.getInt(4));
            spct.setIdSize(rs.getInt(5));
            spct.setIdThuongHieu(rs.getInt(6));
            spct.setMoTa(rs.getString(9));
            spct.setSoLuong(rs.getInt(7));
            spct.setGia(rs.getFloat(8));
            spct.setNguoiTao(rs.getString(10));
            spct.setNguoiSua(rs.getString(11));
            spct.setNgayTao(rs.getDate(12));
            spct.setNgaySua(rs.getDate(13));
            spct.setTrangThai(rs.getBoolean(14));
        }
        
        // Đóng tất cả các tài nguyên
        rs.close();
        ps.close();
        con.close();
    } catch (SQLException e) {
        throw new RuntimeException(e);
    }
    return spct;
}
    
   public String addEntity(SanPhamChiTiet ett) {
    try {
     
        String sql = """
                        INSERT INTO [dbo].[SanPhamChiTiet]
                                            ([IDSANPHAM]
                                             ,[IDMAUSAC]
                                             ,[IDSIZE]
                                             ,[IDCHATLIEU]
                                             ,[IDTHUONGHIEU]
                                             ,[GIA]
                                             ,[SOLUONG],[TRANGTHAI],[MOTA],[NGUOITAO],[NGUOISUA])  
                                     VALUES
                                           (?,?,?,?,?,?,?,?,?,?,?)
                     """;
        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(sql);
        ps.setObject(1, ett.getIdSp());
        ps.setObject(2, ett.getIdMs());
        ps.setObject(3, ett.getIdSize());
        ps.setObject(4, ett.getIdCl());
        ps.setObject(5, ett.getIdThuongHieu());
         ps.setObject(6, ett.getGia());
        ps.setObject(7, ett.getSoLuong());
        ps.setObject(8, true);
        ps.setObject(9, "HAHH");
        ps.setObject(10, "JHAHHA");
        ps.setObject(11, "HAHAH");
       
        

        if (ps.executeUpdate() < 1) {
            return "Thêm thất bại";
        }
    } catch (SQLException e) {
        e.printStackTrace();
        return "Lỗi khi thêm đối tượng";
    }
    return "Thêm thành công";
}
    public String deleteEntity(int id) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

   
    public String updateEntity(SanPhamChiTiet ett, int id) {
        String sqlUpdate = """
                           UPDATE [dbo].[SanPhamChiTiet]
                                     SET [IDSANPHAM] = ?
                                        ,[IDCHATLIEU] =?
                                        ,[IDTHUONGHIEU] = ?
                                        ,[IDMAUSAC] = ?
                                        ,[IDSIZE] = ?
                                        ,[SOLUONG] = ?
                                        ,[GIA] = ?
                                        ,[MOTA] = ?
                                        ,[NGUOISUA] = ?
                                        ,[NGAYSUA] = ?
                             WHERE IDSPCT = ?
                           """;
        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sqlUpdate)) {
            ps.setObject(1, ett.getIdSp());
            ps.setObject(4, ett.getIdMs());
            ps.setObject(2, ett.getIdCl());
            ps.setObject(3, ett.getIdThuongHieu());
            ps.setObject(8, ett.getMoTa());
            ps.setObject(6, ett.getSoLuong());
            ps.setObject(7, ett.getGia());
//            ps.setObject(8, ett.isTrangThai());
            ps.setObject(5, ett.getIdSize());
            ps.setObject(9, ett.getNguoiSua());
                        ps.setObject(10, ett.getNgaySua());

            ps.setObject(11, id);
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

    
    public List<SanPhamChiTiet> resultPage(int page, int limit) {
        String sql = "SELECT * FROM [dbo].[SanPhamChiTiet] ORDER BY idSPCT desc "
                + " OFFSET ? ROWS FETCH NEXT ? ROWS ONLY";

        try ( Connection con = DBConnect.getConnection();  PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setInt(1, (page - 1) * limit);
            ps.setInt(2, limit);
            ResultSet rs = ps.executeQuery();
            List<SanPhamChiTiet> list = new ArrayList<>();
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
                
                list.add(cl);
            }
            return list;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public int idMs(String ten) {
        String sql = """
                        select IDMauSac from MauSac where TenMau =?
            """;
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the value for the parameter
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Return the ID if a record is found
                return rs.getInt("IDMauSac");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int idCl(String ten) {
        String sql = """
                        select IDChatLieu from ChatLieu  where tenchatlieu =?
            """;
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the value for the parameter
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Return the ID if a record is found
                return rs.getInt("IDChatLieu");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int idTH(String ten) {
        String sql = """
                        select IDThuongHieu from ThuongHieu  where TenThuongHieu =?
            """;
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the value for the parameter
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Return the ID if a record is found
                return rs.getInt("IDThuongHieu");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int idSP(String ten) {
        String sql = """
                        select IDSanPham from SanPham  where TenSP =?
            """;
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the value for the parameter
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Return the ID if a record is found
                return rs.getInt("IDSanPham");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public int idSize(String ten) {
        String sql = """
                        select IDSIZE from Size  where TENSIZE =?
            """;
        try ( Connection conn = DBConnect.getConnection();  PreparedStatement ps = conn.prepareStatement(sql)) {

            // Set the value for the parameter
            ps.setString(1, ten);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                // Return the ID if a record is found
                return rs.getInt("IDSIZE");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    public List<SanPhamChiTiet> getAllByMaSp(String maSp) {
        List<SanPhamChiTiet> list = new ArrayList<>();
        try {
            String sqlSearch = """
                            select *
                                        from SANPHAMCHITIET join SANPHAM on SANPHAMCHITIET.IDSANPHAM = SANPHAM.IDSANPHAM
                                        where SanPham.MASANPHAM = ?
                                       """;
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sqlSearch);
            ps.setObject(1, maSp);
            ResultSet rs = ps.executeQuery();
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
                
                list.add(cl);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

   public List<SanPhamChiTiet> searchSpct(String ett) {
    List<SanPhamChiTiet> list = new ArrayList<>();
    try {
        String sqlSearch = """
            SELECT spct.IDSPCT, spct.IDSANPHAM, spct.IDMAUSAC, spct.IDCHATLIEU, spct.SOLUONG, spct.GIA, spct.IDSIZE, spct.IDTHUONGHIEU
            FROM SANPHAM
            JOIN SANPHAMCHITIET spct ON SANPHAM.IDSANPHAM = spct.IDSANPHAM
            JOIN MAUSAC ON MAUSAC.IDMAUSAC = spct.IDMAUSAC
            JOIN CHATLIEU ON CHATLIEU.IDCHATLIEU = spct.IDCHATLIEU
            JOIN SIZE ON SIZE.IDSIZE = spct.IDSIZE
            WHERE SANPHAM.MASANPHAM LIKE ? OR SANPHAM.TENSP LIKE ? OR SIZE.TENSIZE LIKE ? OR MAUSAC.TENMAU LIKE ? OR CHATLIEU.TENCHATLIEU LIKE ?
        """;

        Connection con = DBConnect.getConnection();
        PreparedStatement ps = con.prepareStatement(sqlSearch);
        ps.setObject(1, "%" + ett + "%");
        ps.setObject(2, "%" + ett + "%");
        ps.setObject(3, "%" + ett + "%");
        ps.setObject(4, "%" + ett + "%");
        ps.setObject(5, "%" + ett + "%");

        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            SanPhamChiTiet spct = new SanPhamChiTiet();
            spct.setId(rs.getInt("IDSPCT"));
            spct.setIdSp(rs.getInt("IDSANPHAM"));
            spct.setIdMs(rs.getInt("IDMAUSAC"));
            spct.setIdSize(rs.getInt("IDSIZE"));
            spct.setIdCl(rs.getInt("IDCHATLIEU"));
            spct.setSoLuong(rs.getInt("SOLUONG"));
            spct.setGia(rs.getFloat("GIA"));
            
            spct.setIdThuongHieu(rs.getInt("IDTHUONGHIEU"));

            list.add(spct);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}

}
