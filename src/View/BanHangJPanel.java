/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package View;

import Model.HoaDon;
import Model.HoaDonChiTiet;
import Model.KhachHang;
import Model.SP;
import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.Voucher;
import Repo.DBConnect;
import Service.BanHangService;
import Service.ChatLieuService;
import Service.HoaDonService;
import Service.KhachHangService;
import Service.MauSacService;
import Service.SanPhamChiTietService;
import Service.SanPhamService;
import Service.SizeService;
import Service.ThuongHieuService;
import Service.VoucherService;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import utils.DateHelper;
import utils.MSGHelper;
import utils.VaLiDate;

/**
 *
 * @author ADMIN
 */
public class BanHangJPanel extends javax.swing.JPanel {

    /**
     * Creates new form BanHangJPanel
     */
    BanHangService serviceBH = new BanHangService();
    HoaDonService serviceHD = new HoaDonService();
    SanPhamService serviceSp = new SanPhamService();
    MauSacService serviceMs = new MauSacService();
    ChatLieuService serviceCl = new ChatLieuService();
    SizeService serviceSize = new SizeService();
    SanPhamChiTietService serviceSpct = new SanPhamChiTietService();
    ThuongHieuService serviceTH = new ThuongHieuService();
    KhachHangService serviceKhachHang = new KhachHangService();
    VoucherService serviceVoucher = new VoucherService();
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    int IDHD = -1;
    int IDSPCT = -1;
    int IDHDCT = -1;
     int countSpct, soTrangSpct, trangSpct = 1;
    DefaultTableModel mol = new DefaultTableModel();
    DefaultTableModel molGH = new DefaultTableModel();
    DefaultTableModel modelSpct = new DefaultTableModel();
    List<SP> lstGH = new ArrayList<>();
    public BanHangJPanel() {
        initComponents();
        countDbSpct();
        modelSpct = (DefaultTableModel) TBLSanPhamChiTiet.getModel();
        if (countSpct % 5 == 0) {
            soTrangSpct = countSpct / 5;
        } else {
            soTrangSpct = countSpct / 5 + 1;
        }
        setDataHoaDon(serviceBH.getAllHD());
//        mol = (DefaultTableModel) TBLSanPhamChiTiet.getModel();
//        molGH = (DefaultTableModel) TBLHoaDonChiTiet.getModel();
////        load();
//        showDataSanPhamChiTiet(serviceSpct.resultPage(trangSpct, 5));
        showDataSanPhamChiTiet(serviceSpct.getALL());
        
    }
    void countDbSpct() {
        try {
            String sql = "select COUNT(*) from SANPHAMCHITIET";
            Connection con = DBConnect.getConnection();
            PreparedStatement ps = con.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                countSpct = rs.getInt(1);
            }
            rs.close();
            con.close();
            ps.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // random ma
     private static Random generator = new Random();

    public static int randomNumber(int min, int max) {
        return generator.nextInt((max - min) + 1) + min;
    }
    public String randomAlphaNumeric(int numberOfCharactor) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < numberOfCharactor; i++) {
            int number = randomNumber(0, ALPHA_NUMERIC.length() - 1);
            char ch = ALPHA_NUMERIC.charAt(number);
            sb.append(ch);
        }
        return sb.toString();
    }
    // show dư liệu hóa đơn
    void setDataHoaDon(List<HoaDon> list){
        DefaultTableModel model = new DefaultTableModel();
        model = (DefaultTableModel) TBLHoaDon.getModel();
        model.setRowCount(0);
        for(HoaDon hd : list){
            model.addRow(new Object[]{
                hd.getIdHoaDon(),
                hd.getMaHoaDon(),
                hd.getTongTien(),
                hd.getNgaytao(),
                hd.isTrangThai() == true ? "đã thanh toán": " chưa thanh toán"
            });
        }
    }
    void setdataHoaDonChiTiet(List<HoaDonChiTiet> list) {
        DefaultTableModel modelHDCT = new DefaultTableModel();
        modelHDCT = (DefaultTableModel) TBLHoaDonChiTiet.getModel();
        modelHDCT.setRowCount(0);
        int TongTien = 0;
        for (HoaDonChiTiet hdct : list) {
            SanPhamChiTiet spct = serviceBH.getbyIDSANPHAMCHITIET(hdct.getIDHSanPhamChiTiet());
            String tenSp = serviceSp.getbyID(spct.getIdSp()).getTenSp();
            String tenMs = serviceMs.getbyID(spct.getIdMs()).getTenMs();
            String tenCl = serviceCl.getbyID(spct.getIdCl()).getTenCL();
            String tenSize = serviceSize.getbyID(spct.getIdSize()).getTenS();
             String tenThuongHieu = serviceTH.getbyID(spct.getIdThuongHieu()).getTenTH();
            modelHDCT.addRow(new Object[]{
               hdct.getIDHoaDonChiTiet(),
                hdct.getIDHoaDon(),
                hdct.getIDHSanPhamChiTiet(),
                tenSp,
                tenMs,
                tenSize,
                tenCl,
                tenThuongHieu,
                hdct.getSoLuong(),
                spct.getGia(),
//                TongTien += hdct.getSoLuong() * spct.getGia(),
                 
            });
            TongTien += hdct.getSoLuong() * spct.getGia();
        }
        txtTongTienHD.setText(String.valueOf(TongTien));
    }
    private void load() {
        mol.setRowCount(0);
        ArrayList<SP> lst = serviceBH.getAllSP();
        for (SP bh : lst) {
            mol.addRow(new Object[]{
                bh.getId(),
                bh.getTen(),
                bh.getMauS(),
                bh.getKt(),
                bh.getChatLieu(),
                bh.getThuongHieu(),
                bh.getSoLuong(),
                bh.getGia(),});
        }
    }


    private void loadGH(List<SP> ls) {
        molGH.setRowCount(0);
        for (SP bh : ls) {
            molGH.addRow(new Object[]{
                bh.getTen(),
                bh.getMauS(),
                bh.getKt(),
                bh.getChatLieu(),
                bh.getThuongHieu(),
                bh.getSoLuong(),
                bh.getGia(),
                bh.getSoLuong() * bh.getGia()
            });
        }
    }

    private void showDataSanPhamChiTiet(List<SanPhamChiTiet> list) {
        modelSpct.setRowCount(0);
        for (SanPhamChiTiet spct : list) {
            String tenSp = serviceSp.getbyID(spct.getIdSp()).getTenSp();
            String tenMs = serviceMs.getbyID(spct.getIdMs()).getTenMs();
            String tenCl = serviceCl.getbyID(spct.getIdCl()).getTenCL();
            String tenSize = serviceSize.getbyID(spct.getIdSize()).getTenS();
            String tenThuongHieu = serviceTH.getbyID(spct.getIdThuongHieu()).getTenTH();
            System.out.println(list.size());
            modelSpct.addRow(new Object[]{
                spct.getId(),
                tenSp,
                tenMs,
                tenCl,
                tenSize,
                tenThuongHieu,
                spct.getSoLuong(),
                spct.getGia()
            });
        }
    }
     void loadnew() {
        DefaultTableModel modelHDCT = new DefaultTableModel();
        modelHDCT = (DefaultTableModel) TBLHoaDonChiTiet.getModel();
        modelHDCT.setRowCount(0);
        SoDienThoaiKhachHang.setText("");
        HoTenKhachHang.setText("");
        txtTongTienHD.setText("");
        txtTienGiam.setText("0");
        txtMaVC.setText("");
//        txtThanhTien.setText("");
        txtTienGiam.setText("");
//        txtLoaiGG.setText("");
//        txtTongTienKHtra.setText("");
//        txtTongTienTraLai.setText("");
    }
     void showKH(KhachHang kh) {
        if (kh == null) {
//            txtSDT.setText("Chưa Có SDT");
            HoTenKhachHang.setText("Khách Lẻ");
        } else {
            SoDienThoaiKhachHang.setText(kh.getSdt());
            HoTenKhachHang.setText(kh.getTenKH());
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        btnHD = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TBLHoaDon = new javax.swing.JTable();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        TBLSanPhamChiTiet = new javax.swing.JTable();
        jButton2 = new javax.swing.JButton();
        tfTimKiem = new utils.TextField();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        TBLHoaDonChiTiet = new javax.swing.JTable();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        HoTenKhachHang = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txtTongTienHD = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txtTienGiam = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtNhapMa = new utils.TextField();
        SoDienThoaiKhachHang = new utils.TextField();
        txtMaVC = new javax.swing.JTextField();
        jButton4 = new javax.swing.JButton();

        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Hóa Đơn đang chờ", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        btnHD.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        btnHD.setText("Tạo hóa đơn");
        btnHD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHDActionPerformed(evt);
            }
        });

        TBLHoaDon.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã HĐ", "Tổng tiền", "Ngày tạo", "Trạng thái"
            }
        ));
        TBLHoaDon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLHoaDonMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(TBLHoaDon);

        jButton6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton6.setText("Hủy hóa đơn");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton7.setText("Áp voucher");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(22, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 650, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnHD, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(27, 27, 27)
                        .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(41, 41, 41))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnHD, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Sản phẩm", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        TBLSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Tên SP", "Màu sắc", "Size", "Chất liệu vải", "Thương hiệu", "Số lượng", "Giá bán"
            }
        ));
        TBLSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLSanPhamChiTietMouseClicked(evt);
            }
        });
        jScrollPane3.setViewportView(TBLSanPhamChiTiet);

        jButton2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton2.setText("Tìm kiếm");

        tfTimKiem.setLabelText(" TenSP");
        tfTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimKiemKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(tfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 336, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(161, 161, 161)
                        .addComponent(jButton2))
                    .addGroup(jPanel4Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 812, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(9, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Giỏ hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        TBLHoaDonChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "id", "id1", "id2", "Tên SP", "Màu sắc", "Size", "Chất liệu vải", "Thương hiệu", "Số lượng", "Giá bán"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, true, true, true, true, true, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        TBLHoaDonChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TBLHoaDonChiTietMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(TBLHoaDonChiTiet);
        if (TBLHoaDonChiTiet.getColumnModel().getColumnCount() > 0) {
            TBLHoaDonChiTiet.getColumnModel().getColumn(0).setMinWidth(0);
            TBLHoaDonChiTiet.getColumnModel().getColumn(0).setPreferredWidth(0);
            TBLHoaDonChiTiet.getColumnModel().getColumn(0).setMaxWidth(0);
            TBLHoaDonChiTiet.getColumnModel().getColumn(1).setMinWidth(0);
            TBLHoaDonChiTiet.getColumnModel().getColumn(1).setPreferredWidth(0);
            TBLHoaDonChiTiet.getColumnModel().getColumn(1).setMaxWidth(0);
            TBLHoaDonChiTiet.getColumnModel().getColumn(2).setMinWidth(0);
            TBLHoaDonChiTiet.getColumnModel().getColumn(2).setPreferredWidth(0);
            TBLHoaDonChiTiet.getColumnModel().getColumn(2).setMaxWidth(0);
        }

        jButton8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton8.setText("Làm mới");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jButton9.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton9.setText("Xóa");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jButton3.setText("Sửa");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton3)
                .addGap(18, 18, 18)
                .addComponent(jButton9)
                .addGap(68, 68, 68))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane2)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton8)
                    .addComponent(jButton3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28))
        );

        jPanel5.setBackground(new java.awt.Color(201, 232, 248));
        jPanel5.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Đơn hàng", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Segoe UI", 1, 13))); // NOI18N

        jLabel1.setBackground(new java.awt.Color(228, 228, 228));
        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel1.setText("Mã NV");

        jLabel2.setBackground(new java.awt.Color(228, 228, 228));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel2.setText("Khách hàng:");

        jLabel3.setBackground(new java.awt.Color(228, 228, 228));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel3.setText("###");

        HoTenKhachHang.setText("Họ tên khách hàng");

        jLabel5.setBackground(new java.awt.Color(228, 228, 228));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel5.setText("Voucher:");

        jLabel6.setBackground(new java.awt.Color(228, 228, 228));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 0, 51));
        jLabel6.setText("Hủy voucher");

        jLabel7.setBackground(new java.awt.Color(228, 228, 228));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel7.setText("Tổng tiền:");

        jLabel8.setBackground(new java.awt.Color(228, 228, 228));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel8.setText("Số tiền giảm:");

        jButton1.setBackground(new java.awt.Color(204, 255, 204));
        jButton1.setText("Thanh toán");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jLabel10.setBackground(new java.awt.Color(228, 228, 228));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel10.setText("###");

        jLabel11.setBackground(new java.awt.Color(228, 228, 228));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 1, 13)); // NOI18N
        jLabel11.setText("Thời gian:");

        txtNhapMa.setLabelText("Voucher");
        txtNhapMa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtNhapMaActionPerformed(evt);
            }
        });

        SoDienThoaiKhachHang.setLabelText("SDT");
        SoDienThoaiKhachHang.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SoDienThoaiKhachHangActionPerformed(evt);
            }
        });

        jButton4.setText("jButton4");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(173, 173, 173))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(txtTongTienHD)
                                    .addComponent(txtTienGiam)
                                    .addComponent(txtMaVC, javax.swing.GroupLayout.PREFERRED_SIZE, 183, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(txtNhapMa, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(75, 75, 75)
                                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 167, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(54, 54, 54)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel5Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(HoTenKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 219, Short.MAX_VALUE)
                                    .addComponent(SoDienThoaiKhachHang, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                        .addGap(85, 85, 85)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton4))
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton4)
                .addGap(5, 5, 5)
                .addComponent(SoDienThoaiKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(HoTenKhachHang, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtNhapMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(53, 53, 53)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel5Layout.createSequentialGroup()
                        .addComponent(txtTongTienHD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(19, 19, 19)
                        .addComponent(txtMaVC, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtTienGiam, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(162, 162, 162)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnHDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHDActionPerformed
        // TODO add your handling code here:
        try {
            serviceBH.addHoaDon(randomAlphaNumeric(6));
            setDataHoaDon(serviceBH.getAllHD());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "lỗi");
            throw new RuntimeException(e);
        }
    }//GEN-LAST:event_btnHDActionPerformed

    private void TBLHoaDonMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLHoaDonMouseClicked
        // TODO add your handling code here:
        IDHD = Integer.valueOf(TBLHoaDon.getValueAt(TBLHoaDon.getSelectedRow(), 0).toString());
        this.setdataHoaDonChiTiet(serviceBH.getALLHDCT(IDHD));
        HoaDon hd = serviceBH.getbyID(IDHD);
        //        khachHang kh = serviceBH.getbyIDKHACHHANG(hd.getIdKhachHang());
        //        showKH(kh);
    }//GEN-LAST:event_TBLHoaDonMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        if (IDHD >= 0) {
            try {
                if (MSGHelper.confirm(this, "Bạn Có Chắc Chắn Muốn Xóa Hóa Đơn Này Không")) {
                    // Xóa hóa đơn
                    serviceBH.deleteHD(IDHD);

                    // Xác định và xóa tất cả các sản phẩm liên quan
                    List<HoaDonChiTiet> allHDCT = serviceBH.getALLHDCT(IDHD);
                    for (HoaDonChiTiet hdct : allHDCT) {
                        int idHDCT = hdct.getIDHoaDonChiTiet();
                        int idSPCT = hdct.getIDHSanPhamChiTiet();

                        // Xóa sản phẩm chi tiết
                        serviceBH.deleteHDCT(idHDCT);

                        // Cập nhật số lượng sản phẩm
                        int soLuongGH = hdct.getSoLuong();
                        int soLuongSPCT = serviceBH.getbyIDSANPHAMCHITIET(idSPCT).getSoLuong();
                        int soLuong = soLuongSPCT + soLuongGH;
                        serviceBH.UPDATESPCT(soLuong, idSPCT);
                    }

                    // Cập nhật giao diện
                    setDataHoaDon(serviceBH.getALLHD());
                    setdataHoaDonChiTiet(serviceBH.getALLHDCT(IDHD));
                    this.showDataSanPhamChiTiet(serviceSpct.resultPage(trangSpct, 5));

                    // Reset các ID
                    IDHD = -1;
                    IDHDCT = -1;
                    IDSPCT = -1;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            MSGHelper.alert(this, "Vui Lòng Chọn Hóa Đơn Muốn Xóa");
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton7ActionPerformed

    private void TBLSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLSanPhamChiTietMouseClicked
        // TODO add your handling code here:
        if (IDHD >= 0) {
            IDSPCT = Integer.valueOf(TBLSanPhamChiTiet.getValueAt(TBLSanPhamChiTiet.getSelectedRow(), 0).toString());
            if (serviceBH.getbyHDCT(IDHD, IDSPCT) == null) {
                try {
                    int soLuong = Integer.valueOf(MSGHelper.promot(this, "Nhập Số Lượng Sản Phẩm").toString());
                    if (soLuong <= 0) {
                        MSGHelper.alert(this, "Vui Lòng Không Nhập Số Âm");
                    } else {
                        SanPhamChiTiet spct = serviceHD.getbyIDSANPHAMCHITIET(IDSPCT);
                        int SL = spct.getSoLuong() - soLuong;
                        if (SL < 0) {
                            MSGHelper.alert(this, "Số Lượng Trong Kho Không Đủ");
                        } else {

                            serviceBH.addHoaDonChiTiet(IDHD, IDSPCT, soLuong, spct.getGia());
                            setdataHoaDonChiTiet(serviceBH.getALLHDCT(IDHD));
                            serviceBH.UPDATESPCT(SL, IDSPCT);
                            //                            this.showDataSanPhamChiTiet(serviceSpct.resultPage(trangSpct, 5));
                            this.showDataSanPhamChiTiet(serviceSpct.getALL());

                            serviceBH.UPDATETongTien(Double.valueOf(txtTongTienHD.getText()), IDHD);
                            setDataHoaDon(serviceBH.getAllHD());
                            //                            setDataHoaDon(serviceBH.getALLHD());
                        }
                    }
                } catch (Exception e) {
                    MSGHelper.alert(this, "Vui Lòng Nhập Số");
                    //                    throw new RuntimeException(e);
                }
            } else {
                MSGHelper.alert(this, "Sản Phẩm Đã Tồn Tại Trong Giỏ Hàng");
            }
        } else {
            MSGHelper.alert(this, "Vui Lòng Chọn Hóa Đơn Muốn Thêm Sản Phẩm");
        }
        //if (IDHD >= 0) {
            //    IDSPCT = Integer.valueOf(TBLSanPhamChiTiet.getValueAt(TBLSanPhamChiTiet.getSelectedRow(), 0).toString());
            //    if (serviceBH.getbyHDCT(IDHD, IDSPCT) == null) {
                //        try {
                    //            int soLuong = Integer.valueOf(MSGHelper.promot(this, "Nhập Số Lượng Sản Phẩm").toString());
                    //            if (soLuong <= 0) {
                        //                MSGHelper.alert(this, "Vui Lòng Không Nhập Số Âm");
                        //            } else {
                        //                SanPhamChiTiet spct = serviceHD.getbyIDSANPHAMCHITIET(IDSPCT);
                        //                int SL = spct.getSoLuong() - soLuong;
                        //                if (SL < 0) {
                            //                    MSGHelper.alert(this, "Số Lượng Trong Kho Không Đủ");
                            //                } else {
                            //                    double gia = spct.getGia();
                            //                    double thanhTien = gia * soLuong;
                            //                    double tongTien = Double.valueOf(txtTongTienHD.getText()) + thanhTien;
                            //
                            //                    txtThanhTien.setText("" + thanhTien);
                            //                    txtTongTienHD.setText("" + tongTien);
                            //
                            //                    serviceBH.addHoaDonChiTiet(IDHD, IDSPCT, soLuong, gia);
                            //                    setdataHoaDonChiTiet(serviceBH.getALLHDCT(IDHD));
                            //                    serviceBH.UPDATESPCT(SL, IDSPCT);
                            //                    this.showDataSanPhamChiTiet(serviceSpct.getALL());
                            //
                            //                    serviceBH.UPDATETongTien(tongTien, IDHD);
                            //                    setDataHoaDon(serviceBH.getAllHD());
                            //                }
                        //            }
                    //        } catch (Exception e) {
                    //            MSGHelper.alert(this, "Vui Lòng Nhập Số");
                    //        }
                //    } else {
                //        MSGHelper.alert(this, "Sản Phẩm Đã Tồn Tại Trong Giỏ Hàng");
                //    }
            //} else {
            //    MSGHelper.alert(this, "Vui Lòng Chọn Hóa Đơn Muốn Thêm Sản Phẩm");
            //}
    }//GEN-LAST:event_TBLSanPhamChiTietMouseClicked

    private void TBLHoaDonChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TBLHoaDonChiTietMouseClicked
        // TODO add your handling code here:
        if (IDHD >= 0) {
            IDHDCT = Integer.valueOf(TBLHoaDonChiTiet.getValueAt(TBLHoaDonChiTiet.getSelectedRow(), 0).toString());
            IDSPCT = Integer.valueOf(TBLHoaDonChiTiet.getValueAt(TBLHoaDonChiTiet.getSelectedRow(), 2).toString());
            //            try {
                //                int soLuong = Integer.valueOf(MSGHelper.promot(this, "Nhập Số Lượng Sản Phẩm").toString());
                //                if (soLuong <= 0) {
                    //                    MSGHelper.alert(this, "Vui Lòng Không Nhập Số Âm");
                    //                } else {
                    //                    HoaDonChiTiet hdct = serviceBH.getbyIDHDCT(IDHDCT);
                    //                    int SLSua = soLuong - hdct.getSoLuong();
                    //                    SanPhamChiTiet spct = serviceHoaDon.getbyIDSANPHAMCHITIET(IDSPCT);
                    //                    int SL = spct.getSoLuong() - SLSua;
                    //                    if (SL < 0) {
                        //                        MSGHelper.alert(this, "Số Lượng Trong Kho Không Đủ");
                        //                    } else {
                        //                        serviceBH.UPDATEHDCT(soLuong, IDHDCT);
                        //                        setdataHoaDonChiTiet(serviceBH.getALLHDCT(IDHD));
                        //                        serviceBH.UPDATESPCT(SL, IDSPCT);
                        //                        this.showDataSanPhamChiTiet(serviceSpct.resultPage(trangSpct, 5));
                        //                        serviceBH.UPDATETongTien(Double.valueOf(txtTongTienHD.getText()), IDHD);
                        //                        setdataHoaDon(serviceBH.getALLHD());
                        //                    }
                    //                }
                //            } catch (Exception e) {
                //                MSGHelper.alert(this, "Vui Lòng Nhập Số");
                //            }
        } else {
            //            MSGHelper.alert(this, "Vui Lòng Chọn Hóa Đơn Muốn Thêm Sản Phẩm");
            JOptionPane.showMessageDialog(this, "Vui Lòng Chọn Hóa Đơn Muốn Thêm Sản Phẩm");
        }
    }//GEN-LAST:event_TBLHoaDonChiTietMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
        DefaultTableModel modelHDCT = (DefaultTableModel) TBLHoaDonChiTiet.getModel();
        modelHDCT.setRowCount(0);
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (IDHD >= 0) {
            if (IDHDCT >= 0) {
                IDHDCT = Integer.valueOf(TBLHoaDonChiTiet.getValueAt(TBLHoaDonChiTiet.getSelectedRow(), 0).toString());
                IDSPCT = Integer.valueOf(TBLHoaDonChiTiet.getValueAt(TBLHoaDonChiTiet.getSelectedRow(), 2).toString());
                try {
                    if (MSGHelper.confirm(this, "Bạn Có Chắc Chắn Muốn Xóa Sản Phẩm Này Không ?")) {
                        int soLuongGH = serviceBH.getbyIDHDCT(IDHDCT).getSoLuong();
                        int soLuongSPCT = serviceBH.getbyIDSANPHAMCHITIET(IDSPCT).getSoLuong();
                        int soLuong = soLuongSPCT + soLuongGH;
                        serviceBH.UPDATESPCT(soLuong, IDSPCT);
                        serviceBH.deleteHDCT(IDHDCT);
                        setDataHoaDon(serviceBH.getALLHD());
                        setdataHoaDonChiTiet(serviceBH.getALLHDCT(IDHD));
                        this.showDataSanPhamChiTiet(serviceSpct.resultPage(trangSpct, 5));
                        IDHD = -1;
                        IDHDCT = -1;
                        IDSPCT = -1;
                    }
                } catch (Exception e) {
                    MSGHelper.alert(this, "Vui Lòng Chọn Sản Phẩm Cần Xóa Trong Giỏ Hàng");
                }
            } else {
                MSGHelper.alert(this, "Vui Lòng Chọn Sản Phẩm Muốn Sửa Số Lượng");
            }
        } else {
            MSGHelper.alert(this, "Vui Lòng Chọn Hóa Đơn Muốn Thêm Sản Phẩm");
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        if (IDHD >= 0) {
            if (IDHDCT >= 0) {
                IDHDCT = Integer.valueOf(TBLHoaDonChiTiet.getValueAt(TBLHoaDonChiTiet.getSelectedRow(), 0).toString());
                IDSPCT = Integer.valueOf(TBLHoaDonChiTiet.getValueAt(TBLHoaDonChiTiet.getSelectedRow(), 2).toString());
                try {
                    int soLuong = Integer.valueOf(MSGHelper.promot(this, "Nhập Số Lượng Sản Phẩm").toString());
                    if (soLuong <= 0) {
                        MSGHelper.alert(this, "Vui Lòng Không Nhập Số Âm");
                    } else {
                        HoaDonChiTiet hdct = serviceBH.getbyIDHDCT(IDHDCT);
                        int SLSua = soLuong - hdct.getSoLuong();
                        SanPhamChiTiet spct = serviceHD.getbyIDSANPHAMCHITIET(IDSPCT);
                        int SL = spct.getSoLuong() - SLSua;
                        if (SL < 0) {
                            MSGHelper.alert(this, "Số Lượng Trong Kho Không Đủ");
                        } else {
                            serviceBH.UPDATEHDCT(soLuong, IDHDCT);
                            setdataHoaDonChiTiet(serviceBH.getALLHDCT(IDHD));
                            serviceBH.UPDATESPCT(SL, IDSPCT);
                            this.showDataSanPhamChiTiet(serviceSpct.getALL());
                            serviceBH.UPDATETongTien(Double.valueOf(txtTongTienHD.getText()), IDHD);
                            setDataHoaDon(serviceBH.getALLHD());
                            IDHDCT = -1;
                            IDSPCT = -1;
                        }
                    }
                } catch (Exception e) {
                    MSGHelper.alert(this, "Vui Lòng Nhập Số");
                }
            } else {
                MSGHelper.alert(this, "Vui Lòng Chọn Sản Phẩm Muốn Sửa Số Lượng");
            }
        } else {
            MSGHelper.alert(this, "Vui Lòng Chọn Hóa Đơn Muốn Thêm Sản Phẩm");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (IDHD >= 0) {
            try {
                //                double TienKhachTra = Double.valueOf(MSGHelper.promot(this, "Nhập Vào Số Tiền Khách Đã Trả"));
                double TongTienCanTra = Double.valueOf(txtTongTienHD.getText());
                //                if (TienKhachTra <= 0) {
                    //                    MSGHelper.alert(this, "Vui Lòng Không Nhập Số Âm");
                    //                } else {
                    //                double soTienTraLai = Double.valueOf(txtTongTienTraLai.getText());
                    //                if (soTienTraLai >= 0) {
                        if (!txtMaVC.getText().isEmpty()) {
                            Voucher vc = serviceVoucher.getbyMaVC(txtMaVC.getText());
                            if (vc != null) {
                                serviceVoucher.updateSL(vc);
                            }
                        }
                        //                    if (soTienTraLai > 0) {
                            //                        MSGHelper.alert(this, "Thanh Toán Thành Công\n"
                                //                                + "Vui Lòng Trả Lại Khách : " + soTienTraLai + " VND");
                            this.loadnew();
                            //                    } else {
                            MSGHelper.alert(this, "Thanh Toán Thành Công");
                            this.loadnew();
                            //                    }
                        serviceBH.UPDATEThanhToan(TongTienCanTra, IDHD);
                        setDataHoaDon(serviceBH.getALLHD());
                        //                } else {
                        //                    MSGHelper.alert(this, "Khách Chưa Trả Đủ Tiền");
                        //                }
                    //                }
            } catch (Exception e) {
                MSGHelper.alert(this, "Vui Lòng Nhập Số Tiền");
            }
        } else {
            MSGHelper.alert(this, "Vui Lòng Chọn Hóa Đơn Cần Thanh Toán");
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txtNhapMaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtNhapMaActionPerformed
        // TODO add your handling code here:
        if (IDHD >= 0) {
            try {
                String MAVC = txtNhapMa.getText();
                if (MAVC.isEmpty()) {
                    MSGHelper.alert(this, "Không Nên Để Trống Nêu Thêm Voucher");
                } else {
                    Voucher vc = serviceVoucher.getbyMaVC(MAVC);
                    if (vc == null) {
                        MSGHelper.alert(this, "Voucher Này Không Tồn Tại");
                    } else {
                        if (vc.getSoLuong() > 0) {
                            double TongTien = Double.valueOf(txtTongTienHD.getText());

                            if (TongTien < vc.getDonToiThieu()) {
                                MSGHelper.alert(this, "Đơn Của Bạn Chưa Đạt Tới Giá Trị Tối Thiểu Để Được Giảm Giá\n"
                                    + "Bạn Cần Mua Thêm : " + (vc.getDonToiThieu() - TongTien) + " VND Để Được Áp Dụng");
                            } else {
                                Date NgayHienTai = DateHelper.now();
                                Date startDate = vc.getNgayBatDau();
                                Date endDate = vc.getNgayKetThuc();
                                //                            String ChuyenFormDate = DateHelper.toString(NgayHienTai);
                                ////                            Date ChuyenFormString = DateHelper.toDate(ChuyenFormDate,"yyyy-MM-dd");
                                //                            MSGHelper.alert(this,ChuyenFormDate);
                                if (NgayHienTai.after(startDate)) {
                                    if (NgayHienTai.before(endDate)) {
                                        //                                    txtTongTien.setText("" + (TongTien - vc.getMucGiamGia()));
                                        txtNhapMa.setText("");
                                        txtMaVC.setText(vc.getMaVoucher());
                                        if (vc.getLoaiGG().equalsIgnoreCase("%")) {
                                            //                                            txtLoaiGG.setText("" + vc.getMucGiamGia() + " %");
                                            txtTienGiam.setText("" + ((vc.getMucGiamGia() * TongTien) / 100));
                                        } else {
                                            //                                            txtLoaiGG.setText(vc.getLoaiGG());
                                            txtTienGiam.setText("" + vc.getMucGiamGia());
                                        }
                                        //                                    btnApDungVC.setEnabled(fl);
                                    } else {
                                        MSGHelper.alert(this, "Sự Kiện Này Đã Kết Thúc");
                                    }
                                } else {
                                    MSGHelper.alert(this, "Sự Kiện Này Chưa Bắt Đầu");
                                }
                            }
                        } else {
                            MSGHelper.alert(this, "Voucher Đã Hết Lượt Sử Dụng");
                        }
                    }
                }
            } catch (Exception e) {
                MSGHelper.alert(this, "Lỗi");
            }
        } else {
            MSGHelper.alert(this, "Vui Lòng Chọn Vào Hóa Đơn Muốn Thêm Khách Hàng");
        }
        //if (IDHD >= 0) {
            //            try {
                //                String MAVC = txtNhapMa.getText();
                //                if (MAVC.isEmpty()) {
                    //                    MSGHelper.alert(this, "Không Nên Để Trống Nêu Thêm Voucher");
                    //                } else {
                    //                    Voucher vc = serviceVoucher.getbyMaVC(MAVC);
                    //                    if (vc == null) {
                        //                        MSGHelper.alert(this, "Voucher Này Không Tồn Tại");
                        //                    } else {
                        //                        if (vc.getSoLuong() > 0) {
                            //                            double TongTien = Double.valueOf(txtTongTienHD.getText());
                            //                            double ThanhTien = Double.valueOf(txtThanhTien.getText());
                            //                            if (TongTien < vc.getDonToiThieu()) {
                                //                                MSGHelper.alert(this, "Đơn Của Bạn Chưa Đạt Tới Giá Trị Tối Thiểu Để Được Giảm Giá\n"
                                    //                                        + "Bạn Cần Mua Thêm : " + (vc.getDonToiThieu() - TongTien) + " VND Để Được Áp Dụng");
                                //                            } else {
                                //                                Date NgayHienTai = DateHelper.now();
                                //                                Date startDate = vc.getNgayBatDau();
                                //                                Date endDate = vc.getNgayKetThuc();
                                ////                            String ChuyenFormDate = DateHelper.toString(NgayHienTai);
                                //////                            Date ChuyenFormString = DateHelper.toDate(ChuyenFormDate,"yyyy-MM-dd");
                                ////                            MSGHelper.alert(this,ChuyenFormDate);
                                //                                if (NgayHienTai.after(startDate)) {
                                    //                                    if (NgayHienTai.before(endDate)) {
                                        ////                                    txtTongTien.setText("" + (TongTien - vc.getMucGiamGia()));
                                        //                                        txtNhapMa.setText("");
                                        //                                        txtMaVC.setText(vc.getMaVoucher());
                                        //
                                        //                                        if (vc.getLoaiGG().equalsIgnoreCase("%")) {
                                            ////                                            txtLoaiGG.setText("" + vc.getMucGiamGia() + " %");
                                            //                                            txtTienGiam.setText("" + ((vc.getMucGiamGia() * TongTien) / 100));
                                            //
                                            //                                        } else {
                                            ////                                            txtLoaiGG.setText(vc.getLoaiGG());
                                            //                                            txtTienGiam.setText("" + vc.getMucGiamGia());
                                            //
                                            //                                        }
                                        //
                                        ////                                    btnApDungVC.setEnabled(fl);
                                        //                                    } else {
                                        //                                        MSGHelper.alert(this, "Sự Kiện Này Đã Kết Thúc");
                                        //                                    }
                                    //                                } else {
                                    //                                    MSGHelper.alert(this, "Sự Kiện Này Chưa Bắt Đầu");
                                    //                                }
                                //                            }
                            //                        } else {
                            //                            MSGHelper.alert(this, "Voucher Đã Hết Lượt Sử Dụng");
                            //                        }
                        //                    }
                    //                }
                //            } catch (Exception e) {
                //                MSGHelper.alert(this, "Lỗi");
                //            }
            //        } else {
            //            MSGHelper.alert(this, "Vui Lòng Chọn Vào Hóa Đơn Muốn Thêm Khách Hàng");
            //        }
        //if (IDHD >= 0) {
            //    try {
                //        String MAVC = txtNhapMa.getText();
                //        if (MAVC.isEmpty()) {
                    //            MSGHelper.alert(this, "Không Nên Để Trống Nêu Thêm Voucher");
                    //        } else {
                    //            Voucher vc = serviceVoucher.getbyMaVC(MAVC);
                    //            if (vc == null) {
                        //                MSGHelper.alert(this, "Voucher Này Không Tồn Tại");
                        //            } else {
                        //                if (vc.getSoLuong() > 0) {
                            //                    double TongTien = Double.valueOf(txtTongTienHD.getText());
                            //                    if (TongTien < vc.getDonToiThieu()) {
                                //                        MSGHelper.alert(this, "Đơn Của Bạn Chưa Đạt Tới Giá Trị Tối Thiểu Để Được Giảm Giá\n"
                                    //                                + "Bạn Cần Mua Thêm : " + (vc.getDonToiThieu() - TongTien) + " VND Để Được Áp Dụng");
                                //                    } else {
                                //                        Date NgayHienTai = DateHelper.now();
                                //                        Date startDate = vc.getNgayBatDau();
                                //                        Date endDate = vc.getNgayKetThuc();
                                //                        if (NgayHienTai.after(startDate) && NgayHienTai.before(endDate)) {
                                    //                            txtNhapMa.setText("");
                                    //                            txtMaVC.setText(vc.getMaVoucher());
                                    //                            double tienGiam = 0;
                                    //                            if (vc.getLoaiGG().equalsIgnoreCase("%")) {
                                        //                                tienGiam = (vc.getMucGiamGia() * TongTien) / 100;
                                        //                            } else {
                                        //                                tienGiam = vc.getMucGiamGia();
                                        //                            }
                                    //                            txtTienGiam.setText("" + tienGiam);
                                    //                            txtThanhTien.setText("" + (TongTien - tienGiam));
                                    //                        } else if (NgayHienTai.before(startDate)) {
                                    //                            MSGHelper.alert(this, "Sự Kiện Này Chưa Bắt Đầu");
                                    //                        } else {
                                    //                            MSGHelper.alert(this, "Sự Kiện Này Đã Kết Thúc");
                                    //                        }
                                //                    }
                            //                } else {
                            //                    MSGHelper.alert(this, "Voucher Đã Hết Lượt Sử Dụng");
                            //                }
                        //            }
                    //        }
                //    } catch (Exception e) {
                //        e.printStackTrace();
                //        MSGHelper.alert(this, "Lỗi");
                //    }
            //} else {
            //    MSGHelper.alert(this, "Vui Lòng Chọn Vào Hóa Đơn Muốn Thêm Khách Hàng");
            //}
    }//GEN-LAST:event_txtNhapMaActionPerformed

    private void SoDienThoaiKhachHangActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SoDienThoaiKhachHangActionPerformed
        // TODO add your handling code here:
//        ArrayList<KhachHang> list = serviceKhachHang.getAll();
//        KhachHang kh = new KhachHang();
//        String SDT = SoDienThoaiKhachHang.getText().trim();
//        for (KhachHang khachhang : list) {
//            if (SDT.equalsIgnoreCase(khachhang.getSdt())) {
//                HoTenKhachHang.setText(String.valueOf(khachhang.getTenKH()));
//            } else if (SDT.equalsIgnoreCase("")) {
//                HoTenKhachHang.setText("Khách vãng lai");
//            }
//        }
// if (IDHD >= 0) {
//            try {
//                String SDT = SoDienThoaiKhachHang.getText();
////            MSGHelper.alert(this, SDT);
//                VaLiDate.CheckValueInput("^(0)(3[0-9]|5[0-9]|7[0-9]|8[0-9]|9[0-9])\\d{7}$", SDT);
//                KhachHang kh = serviceBH.getbySDTKHACHHANG(SDT);
//                if (kh == null) {
//                    if (MSGHelper.confirm(this, "Khách Hàng Không Tồn Tại\n Bạn Có Muốn Thêm Mới Không")) {
//                        String tenKH = MSGHelper.promot(this, "Vui Lòng Nhập Tên Khách Hàng");
//                        serviceBH.addKhachHang(randomAlphaNumeric(6), tenKH, SDT);
//                        KhachHang khNew = serviceBH.getbySDTKHACHHANG(SDT);
//                        serviceBH.UPDATEKhachHang(khNew.getId(), IDHD);
//                        showKH(khNew);
//                    }
//                } else {
//                    serviceBH.UPDATEKhachHang(kh.getId(), IDHD);
//                    showKH(kh);
//                }
//            } catch (Exception e) {
//                MSGHelper.alert(this, "Vui Lòng Nhập Đúng Số Điện Thoại");
////            throw new RuntimeException(e);
//            }
//        } else {
//            MSGHelper.alert(this, "Vui Lòng Chọn Vào Hóa Đơn Muốn Thêm Khách Hàng");
//        }
    }//GEN-LAST:event_SoDienThoaiKhachHangActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
//         if (IDHD >= 0) {
//            try {
//                String SDT = SoDienThoaiKhachHang.getText();
////            MSGHelper.alert(this, SDT);
//                VaLiDate.CheckValueInput("^(0)(3[0-9]|5[0-9]|7[0-9]|8[0-9]|9[0-9])\\d{7}$", SDT);
//                KhachHang kh = serviceBH.getbySDTKHACHHANG(SDT);
//                if (kh == null) {
//                    if (MSGHelper.confirm(this, "Khách Hàng Không Tồn Tại\n Bạn Có Muốn Thêm Mới Không")) {
//                        String tenKH = MSGHelper.promot(this, "Vui Lòng Nhập Tên Khách Hàng");
//                        serviceBH.addKhachHang(randomAlphaNumeric(6), tenKH, SDT);
//                        KhachHang khNew = serviceBH.getbySDTKHACHHANG(SDT);
//                        serviceBH.UPDATEKhachHang(khNew.getId(), IDHD);
//                        showKH(khNew);
//                    }
//                } else {
//                    serviceBH.UPDATEKhachHang(kh.getId(), IDHD);
//                    showKH(kh);
//                }
//            } catch (Exception e) {
//                MSGHelper.alert(this, "Vui Lòng Nhập Đúng Số Điện Thoại");
////            throw new RuntimeException(e);
//            }
//        } else {
//            MSGHelper.alert(this, "Vui Lòng Chọn Vào Hóa Đơn Muốn Thêm Khách Hàng");
//        }
//if (IDHD >= 0) {
//    try {
//        String SDT = SoDienThoaiKhachHang.getText();
//        // Kiểm tra số điện thoại có hợp lệ hay không
//        VaLiDate.CheckValueInput("^(0)(3[0-9]|5[0-9]|7[0-9]|8[0-9]|9[0-9])\\d{7}$", SDT);
//
//        KhachHang kh = serviceBH.getbySDTKHACHHANG(SDT);
//        if (kh == null) {
//            if (MSGHelper.confirm(this, "Khách Hàng Không Tồn Tại\n Bạn Có Muốn Thêm Mới Không")) {
//                String tenKH = MSGHelper.promot(this, "Vui Lòng Nhập Tên Khách Hàng");
//                serviceBH.addKhachHang(randomAlphaNumeric(6), tenKH, SDT);
//                KhachHang khNew = serviceBH.getbySDTKHACHHANG(SDT);
//                serviceBH.UPDATEKhachHang(khNew.getId(), IDHD);
//                showKH(khNew);
//            }
//        } else {
//            serviceBH.UPDATEKhachHang(kh.getId(), IDHD);
//            showKH(kh);
//        }
//    } catch (IllegalArgumentException e) {
//        // Bắt ngoại lệ IllegalArgumentException và hiển thị thông báo lỗi
//        MSGHelper.alert(this, "Vui Lòng Nhập Đúng Số Điện Thoại");
//    } catch (Exception e) {
//        // Bắt bất kỳ ngoại lệ nào khác và hiển thị thông báo lỗi chung
//        MSGHelper.alert(this, "Đã xảy ra lỗi: " + e.getMessage());
//    }
//} else {
//    MSGHelper.alert(this, "Vui Lòng Chọn Vào Hóa Đơn Muốn Thêm Khách Hàng");
//}
 ArrayList<KhachHang> list = (ArrayList<KhachHang>) serviceKhachHang.getAll();
        KhachHang kh = new KhachHang();
        String SDT = SoDienThoaiKhachHang.getText().trim();
        for (KhachHang khachhang : list) {
            if (SDT.equalsIgnoreCase(khachhang.getSdt())) {
                HoTenKhachHang.setText(String.valueOf(khachhang.getTenKH()));
            } else if (SDT.equalsIgnoreCase("")) {
                HoTenKhachHang.setText("Khách vãng lai");
            }
        }

    }//GEN-LAST:event_jButton4ActionPerformed

    private void tfTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemKeyTyped
        // TODO add your handling code here:
        showDataSanPhamChiTiet(serviceSpct.searchSpct(tfTimKiem.getText()));
    }//GEN-LAST:event_tfTimKiemKeyTyped



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField HoTenKhachHang;
    private utils.TextField SoDienThoaiKhachHang;
    private javax.swing.JTable TBLHoaDon;
    private javax.swing.JTable TBLHoaDonChiTiet;
    private javax.swing.JTable TBLSanPhamChiTiet;
    private javax.swing.JButton btnHD;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private utils.TextField tfTimKiem;
    private javax.swing.JTextField txtMaVC;
    private utils.TextField txtNhapMa;
    private javax.swing.JTextField txtTienGiam;
    private javax.swing.JTextField txtTongTienHD;
    // End of variables declaration//GEN-END:variables
}
