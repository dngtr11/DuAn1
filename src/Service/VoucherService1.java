/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Service;

import Model.Voucher;
import Repo.DBConnect;
import java.util.ArrayList;
import java.sql.*;

/**
 *
 * @author Dung Tran
 */
public class VoucherService1 {
//    public ArrayList<Voucher> getAll() {
//    ArrayList<Voucher> list = new ArrayList<>();
//    String sql = "select MAVOUCHER, TENVOUCHER, MUCGIAMGIA from Voucher";
//    try {
//        java.sql.Connection cn = DBConnect.getConnection();
//        PreparedStatement ps = cn.prepareStatement(sql);
//        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//            Voucher vc = new Voucher();
//            // Gán giá trị từ cơ sở dữ liệu cho các thuộc tính của đối tượng Voucher
//            vc.setMAVOUCHER(rs.getString("MAVOUCHER"));
//            vc.setTENVOUCHER(rs.getString("TENVOUCHER"));
//            vc.setMUCGIAMGIA(rs.getFloat("MUCGIAMGIA"));
//            // Thêm đối tượng Voucher đã được gán giá trị vào danh sách
//            list.add(vc);
//        }
//    } catch (Exception e) {
//        e.printStackTrace(); // In ra thông tin lỗi nếu có
//    }
//    return list;
//}
}
