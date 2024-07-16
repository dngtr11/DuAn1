/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.SanPhamChiTiet;
import Service.BanHangService;
import Service.ChatLieuService;
import Service.HoaDonChiTietService;
import Service.HoaDonService;
import Service.MauSacService;
import Service.SanPhamService;
import Service.SizeService;
import Service.ThuongHieuService;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import utils.DateHelper;

/**
 *
 * @author Dung Tran
 */
public class HoaDonJPanel extends javax.swing.JPanel {

    /**
     * Creates new form HoaDonJPanel
     */
    private HoaDonService serviceHoaDon = new HoaDonService();
    private HoaDonChiTietService serviceHoaDonChitiet = new HoaDonChiTietService();
    BanHangService serviceBH = new BanHangService();
//    HoaDonService serviceHoaDon = new HoaDonService();
    SanPhamService serviceSp = new SanPhamService();
    MauSacService serviceMs = new MauSacService();
    ChatLieuService serviceCl = new ChatLieuService();
    SizeService serviceSize = new SizeService();
    ThuongHieuService serviceTH = new ThuongHieuService();
    public HoaDonJPanel() {
        initComponents();
        this.AnID();
       this.setdataHoaDon(serviceHoaDon.getALL());
    }
    void AnID() {
        TBLHoaDon.getColumnModel().getColumn(0).setMinWidth(0);
        TBLHoaDon.getColumnModel().getColumn(0).setMaxWidth(0);
        TBLHoaDon.getColumnModel().getColumn(0).setPreferredWidth(0);
        TBLHDCT.getColumnModel().getColumn(0).setMinWidth(0);
        TBLHDCT.getColumnModel().getColumn(0).setMaxWidth(0);
        TBLHDCT.getColumnModel().getColumn(0).setPreferredWidth(0);
        TBLHoaDon.getColumnModel().getColumn(2).setMinWidth(200);
        TBLHoaDon.getColumnModel().getColumn(2).setMaxWidth(200);
        TBLHoaDon.getColumnModel().getColumn(2).setPreferredWidth(200);
//        TBLHoaDon.getColumnModel().getColumn(2).setMinWidth(100);
//        TBLHoaDon.getColumnModel().getColumn(2).setMaxWidth(100);
//        TBLHoaDon.getColumnModel().getColumn(2).setPreferredWidth(100);
        TBLHDCT.getColumnModel().getColumn(1).setMinWidth(150);
        TBLHDCT.getColumnModel().getColumn(1).setMaxWidth(150);
        TBLHDCT.getColumnModel().getColumn(1).setPreferredWidth(150);
        TBLHDCT.getColumnModel().getColumn(2).setMinWidth(100);
        TBLHDCT.getColumnModel().getColumn(2).setMaxWidth(100);
        TBLHDCT.getColumnModel().getColumn(2).setPreferredWidth(100);
        TBLHDCT.getColumnModel().getColumn(3).setMinWidth(120);
        TBLHDCT.getColumnModel().getColumn(3).setMaxWidth(120);
        TBLHDCT.getColumnModel().getColumn(3).setPreferredWidth(120);
        TBLHDCT.getColumnModel().getColumn(4).setMinWidth(200);
        TBLHDCT.getColumnModel().getColumn(4).setMaxWidth(200);
        TBLHDCT.getColumnModel().getColumn(4).setPreferredWidth(200);
        cboTT.removeAllItems();
        cboTT.addItem("Đã Thanh Toán");
        cboTT.addItem("Chưa Thanh Toán");
        cboTT.addItem("Tất Cả");
    }

    void Loadnew() {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) TBLHDCT.getModel();
        model.setRowCount(0);
        txtTenKH.setText("");
        txtSDT.setText("");
        txtNgaySinh.setText("");
        txtEmail.setText("");
        txtGioiTinh.setText("");
        txtTimKiem.setText("");
    }

    void setdataHoaDon(List<HoaDon> list) {
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) TBLHoaDon.getModel();
        model.setRowCount(0);
        for (HoaDon hd : list) {
            KhachHang kh = serviceHoaDon.getbyIDKHACHHANG(hd.getIdKhachHang());
            model.addRow(new Object[]{
                hd.getIdHoaDon(),
                hd.getMaHoaDon(),
                kh == null ? "Khách Lẻ" : kh.getTenKH(),
                
                hd.getNguoiTao(),
                hd.getTongTien(),
                hd.isTrangThai() == true ? "Đã Thanh Toán" : "Chưa Thanh Toán"
            });
        }
    }

    void setdataHoaDonChiTiet(List<HoaDonChiTiet> list) {
//        DefaultTableModel model = new DefaultTableModel();
//        model = (DefaultTableModel) TBLHDCT.getModel();
//        model.setRowCount(0);
//        for (HoaDonChiTiet hdct : list) {
//            SanPhamChiTiet spct = serviceHoaDon.getbyIDSANPHAMCHITIET(hdct.getIDHSanPhamChiTiet());
//            SanPham sp = serviceHoaDon.getbyIDSANPHAM(spct.getIdSp());
//            model.addRow(new Object[]{
//                hdct.getIDHSanPhamChiTiet(),
//                sp == null ? "" : sp.getTenSp(),
//                hdct.getSoLuong(),
//                hdct.getGia(),
//                spct == null ? "" : spct.getMoTa()
//            });
//        }
        DefaultTableModel modelHDCT = new DefaultTableModel();
        modelHDCT = (DefaultTableModel) TBLHDCT.getModel();
        modelHDCT.setRowCount(0);
//        int TongTien = 0;
        for (HoaDonChiTiet hdct : list) {
            SanPhamChiTiet spct = serviceBH.getbyIDSANPHAMCHITIET(hdct.getIDHSanPhamChiTiet());
            String tenSp = serviceSp.getbyID(spct.getIdSp()).getTenSp();
            String tenMs = serviceMs.getbyID(spct.getIdMs()).getTenMs();
            String tenCl = serviceCl.getbyID(spct.getIdCl()).getTenCL();
            String tenTH = serviceTH.getbyID(spct.getIdThuongHieu()).getTenTH();
            String tenSize = serviceSize.getbyID(spct.getIdSize()).getTenS();
            modelHDCT.addRow(new Object[]{
                hdct.getIDHoaDonChiTiet(),
//                hdct.getIDHoaDon(),
//                hdct.getIDHSanPhamChiTiet(),
                tenSp,
                tenMs,
                tenCl,
                tenTH,
                tenSize,
                hdct.getSoLuong(),
                spct.getGia()
            });
//            TongTien += hdct.getSoLuong() * spct.getGia();
        }
//        txtTongTien.setText(String.valueOf(TongTien));
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel6 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TBLHDCT = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        txtTimKiem = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        cboTT = new javax.swing.JComboBox<>();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBLHoaDon = new javax.swing.JTable();
        jPanel5 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTenKH = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtGioiTinh = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn Chi Tiết"));

        TBLHDCT.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "Tên Sản Phẩm", "Màu Sắc", "Chất Liệu", "Thương Hiệu", "Size", "Số Lượng", "Giá Tiền"
            }
        ));
        jScrollPane2.setViewportView(TBLHDCT);
        if (TBLHDCT.getColumnModel().getColumnCount() > 0) {
            TBLHDCT.getColumnModel().getColumn(0).setMinWidth(0);
            TBLHDCT.getColumnModel().getColumn(0).setPreferredWidth(0);
            TBLHDCT.getColumnModel().getColumn(0).setMaxWidth(0);
        }

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 905, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        txtTimKiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTimKiemActionPerformed(evt);
            }
        });

        jLabel1.setText("Tìm kiếm");

        jLabel2.setText("Trạng thái");

        cboTT.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Đã thanh toán", "Chưa thanh toán", "Tất cả" }));
        cboTT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboTTActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 212, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39)
                .addComponent(jLabel2)
                .addGap(18, 18, 18)
                .addComponent(cboTT, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(17, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2)
                    .addComponent(cboTT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder("Hóa Đơn"));

        TBLHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Mã hóa đơn", "tên người nhận", "Ngày tạo", "Người tạo", "Tổng tiền", "Trạng thái"
            }
        ));
        TBLHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBLHoaDon);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 436, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Chi Tiết"));

        jLabel3.setText("Họ vs Tên KH");

        jLabel4.setText("SĐT");

        jLabel5.setText("Ngày Sinh");

        jLabel6.setText("Email");

        jLabel7.setText("Giới tính");

        txtTenKH.setActionCommand("<Not Set>");
        txtTenKH.setEnabled(false);

        txtSDT.setEnabled(false);

        txtNgaySinh.setEnabled(false);

        txtEmail.setEnabled(false);

        txtGioiTinh.setEnabled(false);

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel6)
                    .addComponent(jLabel7))
                .addGap(33, 33, 33)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtTenKH)
                    .addComponent(txtSDT)
                    .addComponent(txtNgaySinh)
                    .addComponent(txtEmail)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(87, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3)
                    .addComponent(txtTenKH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7)
                    .addComponent(txtGioiTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(58, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void txtTimKiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTimKiemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtTimKiemActionPerformed

    private void cboTTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboTTActionPerformed
        // TODO add your handling code here:
        String selectedItem = (String) cboTT.getSelectedItem();
        try {
            if (selectedItem.equalsIgnoreCase("Đã Thanh Toán")) {
                //                setdataHoaDon(serviceHoaDon.getALLChuaTT());
                setdataHoaDon(serviceHoaDon.getALLDaTT());
            } else if (selectedItem.equalsIgnoreCase("Chưa Thanh Toán")) {
                //                setdataHoaDon(serviceHoaDon.getALLDaTT());
                setdataHoaDon(serviceHoaDon.getALLChuaTT());
            } else {
                setdataHoaDon(serviceHoaDon.getALL());
            }
        } catch (Exception e) {
            //            setdataHoaDon(serviceHoaDon.getALL());
            setdataHoaDon(serviceHoaDon.getALLDaTT());
        }
        this.Loadnew();
    }//GEN-LAST:event_cboTTActionPerformed

    private void TBLHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLHoaDonMouseClicked
        // TODO add your handling code here:
        this.Loadnew();
        int id = Integer.valueOf(TBLHoaDon.getValueAt(TBLHoaDon.getSelectedRow(), 0).toString());
        setdataHoaDonChiTiet(serviceHoaDonChitiet.getHDCTinHD(id));
        HoaDon hd = serviceHoaDon.getbyID(id);
        KhachHang kh = serviceHoaDon.getbyIDKHACHHANG(hd.getIdKhachHang());
        if (kh != null) {
            txtTenKH.setText(kh.getTenKH());
            txtSDT.setText(kh.getSdt());
            txtNgaySinh.setText(DateHelper.toString(kh.getNgaySinh()));
            txtEmail.setText(kh.getEmail());
            txtGioiTinh.setText(kh.isGioiTinh() == true ? "Nam" : "Nữ");

        }
    }//GEN-LAST:event_TBLHoaDonMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TBLHDCT;
    private javax.swing.JTable TBLHoaDon;
    private javax.swing.JComboBox<String> cboTT;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtGioiTinh;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenKH;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
