/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.Voucher;
import Repo.DBConnect;
import Service.VoucherService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ADMIN
 */
public class VoucherJPanel extends javax.swing.JPanel {

    /**
     * Creates new form VoucherJPanel
     */
     DefaultTableModel model = new DefaultTableModel();
     VoucherService vouSv = new VoucherService();
    List<Voucher> listVC = new ArrayList<>();
    int count, soTrang, trang = 1;
    public VoucherJPanel() {
        initComponents();
        model = (DefaultTableModel) tblVocher.getModel();

        cboLoaiGG.removeAllItems();
        cboLoaiGG.addItem("Tiền");
        cboLoaiGG.addItem("%");
        listVC  = vouSv.getAllVoucher();
        showData(listVC);
        countDbVouCher();
    }
    void countDbVouCher() {
        try {
            String sql = "select COUNT(*) from VOUCHER";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                count = rs.getInt(1);
            }
            rs.close();
            con.close();
            ps.close();
        } catch (Exception e) {
        }
    }
    public void showInfor(Voucher vc){
    txtMaVoucher.setText(vc.getMaVoucher());
    txtTenVouCher.setText(vc.getTenVoucher());
    txtDonToiThieu.setText(vc.getDonToiThieu()+"");
    txtMucGiamGia.setText(vc.getMucGiamGia()+"");
    txtSoluong.setText(vc.getSoLuong()+"");
    cboLoaiGG.setSelectedItem(vc.getLoaiGG());
    txtNgayBatDau.setDate(vc.getNgayBatDau());
    txtNgayKetThuc.setDate(vc.getNgayKetThuc());
    
}
    void showData(List<Voucher> list) {
        model = (DefaultTableModel) tblVocher.getModel();
        model.setRowCount(0);
         int stt = tblVocher.getSelectedRow();
         stt +=1;
        for (Voucher vc : list) {
            model.addRow(new Object[]{
                stt +=1,
//                vc.getId(),
                vc.getMaVoucher(),
                vc.getTenVoucher(),
                vc.getDonToiThieu(),
                vc.getMucGiamGia(),
                vc.getLoaiGG(),
                vc.getSoLuong(),
                vc.getNgayBatDau(),
                vc.getNgayKetThuc(),
                vc.isTrangThai() == true ? "Hoạt Động" : "Hết Hoạt Động"
            });
        }
    }
    
    Voucher getForm(){
        Voucher vc = new Voucher();
        if (txtMaVoucher.getText().isEmpty() || txtTenVouCher.getText().isEmpty() || txtDonToiThieu.getText().isEmpty()
                || txtMucGiamGia.getText().isEmpty() || txtSoluong.getText().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập đủ Thông Tin");
            return null;
        }
        vc.setMaVoucher(txtMaVoucher.getText());
        vc.setTenVoucher(txtTenVouCher.getText());
        try {
            vc.setDonToiThieu(Double.valueOf(txtDonToiThieu.getText()));
            vc.setMucGiamGia(Double.valueOf(txtMucGiamGia.getText()));
            vc.setSoLuong(Integer.valueOf(txtSoluong.getText()));
            if (Double.valueOf(txtDonToiThieu.getText()) < 0 || Double.valueOf(txtMucGiamGia.getText()) < 0 || Integer.valueOf(txtSoluong.getText()) < 0) {
                JOptionPane.showMessageDialog(this, "Vui LÒng Không Nhập Số Âm");
                return null;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Đúng Kiểu Số");
            return null;
        }
        vc.setLoaiGG(cboLoaiGG.getSelectedItem().toString());
        if (cboLoaiGG.getSelectedItem().toString().equalsIgnoreCase("%")) {
            if (Double.valueOf(txtMucGiamGia.getText()) > 50) {
                JOptionPane.showMessageDialog(this, "Không Nhập Phần Trăm Voucher quá 50");
                return null;
            }
        }
        try {
            vc.setNgayBatDau(txtNgayBatDau.getDate());
            vc.setNgayKetThuc(txtNgayKetThuc.getDate());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Vui Lòng Nhập Đúng Kiểu Ngày");
        }
        return vc;
    }
    public void reset(){
    txtMaVoucher.setText("");
    txtDonToiThieu.setText("");
    txtMucGiamGia.setText("");
    txtSoluong.setText("");
    txtTenVouCher.setText("");
    txtNgayBatDau.setDateFormatString("");
    txtNgayKetThuc.setDateFormatString("");
    cboLoaiGG.setSelectedItem(0);
    txtTimKiem.setText("");
}

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblVocher = new javax.swing.JTable();
        jButton3 = new javax.swing.JButton();
        txtNgayBatDau = new com.toedter.calendar.JDateChooser();
        txtMucGiamGia = new javax.swing.JTextField();
        txtTimKiem = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        txtTenVouCher = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        txtSoluong = new javax.swing.JTextField();
        txtMaVoucher = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        cboLoaiGG = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        txtNgayKetThuc = new com.toedter.calendar.JDateChooser();
        txtDonToiThieu = new javax.swing.JTextField();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông tin"));

        tblVocher.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Voucher", "Tên Voucher", "Đơn tối thiểu", "Mức giảm giá", "Loại giảm giá", "Số lượng", "Ngày bắt đầu", "Ngày Kết thức", "Trạng thái"
            }
        ));
        tblVocher.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblVocherMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblVocher);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 840, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(57, Short.MAX_VALUE))
        );

        jButton3.setText("làm mới");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        txtTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                txtTimKiemKeyTyped(evt);
            }
        });

        jLabel5.setText("Loại giảm giá");

        jLabel2.setText("Tên giảm giá");

        jLabel7.setText("Ngày bắt đầu");

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel9.setText("Tìm kiếm");

        jLabel6.setText("Số lượng");

        jLabel3.setText("Đơn tối thiểu");

        jButton2.setText("Sửa");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel8.setText("Ngày kết thúc");

        jLabel1.setText("Mã giảm giá");

        cboLoaiGG.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "%", "Tiền mặt" }));
        cboLoaiGG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cboLoaiGGActionPerformed(evt);
            }
        });

        jLabel4.setText("Mức giảm giá");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel1)
                                    .addComponent(jLabel2)
                                    .addComponent(txtMaVoucher)
                                    .addComponent(txtTenVouCher, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(56, 56, 56)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel4)
                                    .addComponent(jLabel3)
                                    .addComponent(txtMucGiamGia)
                                    .addComponent(txtDonToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel9)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 278, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jButton1)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jButton2)
                                        .addGap(9, 9, 9)))))
                        .addGap(47, 47, 47)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jButton3)
                            .addComponent(cboLoaiGG, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(50, 50, 50)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel8)
                            .addComponent(jLabel7)
                            .addComponent(txtNgayBatDau, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtMaVoucher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtMucGiamGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cboLoaiGG, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(txtNgayBatDau, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel6)
                    .addComponent(jLabel8))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(txtNgayKetThuc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(txtSoluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtDonToiThieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txtTenVouCher, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(42, 42, 42)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(44, 44, 44)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton1)
                    .addComponent(jButton2)
                    .addComponent(jButton3))
                .addGap(40, 40, 40)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void tblVocherMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblVocherMouseClicked
        // TODO add your handling code here:
        showInfor(vouSv.getAllVoucher().get(tblVocher.getSelectedRow()));
    }//GEN-LAST:event_tblVocherMouseClicked

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        reset();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txtTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtTimKiemKeyTyped
        // TODO add your handling code here:
        showData(vouSv.search(txtTimKiem.getText()));
    }//GEN-LAST:event_txtTimKiemKeyTyped

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?") == 0) {
            JOptionPane.showMessageDialog(this, vouSv.addVoucher(getForm()));
            listVC = vouSv.getAllVoucher();
            showData(listVC);
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?") == 0) {
                Voucher vc = new Voucher();
                vc = vouSv.getAllVoucher().get(tblVocher.getSelectedRow());
                JOptionPane.showMessageDialog(this, vouSv.updateVoucher(getForm(), vc.getId()));
                listVC = vouSv.getAllVoucher();
                showData(listVC);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng để sửa");
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void cboLoaiGGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cboLoaiGGActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cboLoaiGGActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cboLoaiGG;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblVocher;
    private javax.swing.JTextField txtDonToiThieu;
    private javax.swing.JTextField txtMaVoucher;
    private javax.swing.JTextField txtMucGiamGia;
    private com.toedter.calendar.JDateChooser txtNgayBatDau;
    private com.toedter.calendar.JDateChooser txtNgayKetThuc;
    private javax.swing.JTextField txtSoluong;
    private javax.swing.JTextField txtTenVouCher;
    private javax.swing.JTextField txtTimKiem;
    // End of variables declaration//GEN-END:variables
}
