package View;

import Model.ChatLieu;
import Model.MauSac;
import Model.SanPham;
import Model.SanPhamChiTiet;
import Model.Size;
import Model.ThuongHieu;
import Repo.DBConnect;
import Service.ChatLieuService;
import Service.MauSacService;
import Service.SanPhamChiTietService;
import Service.SanPhamService;
import Service.SizeService;
import Service.ThuongHieuService;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


/**
 *
 * @author PC
 */
public class SanPhamJPanel extends javax.swing.JPanel {

    DefaultTableModel modelSp = new DefaultTableModel();
    DefaultTableModel modelSpct = new DefaultTableModel();
    DefaultTableModel modelSize = new DefaultTableModel();
    DefaultTableModel modelCl = new DefaultTableModel();
    DefaultTableModel modelMs = new DefaultTableModel();
    DefaultTableModel modelth = new DefaultTableModel();
    SanPhamService serviceSp = new SanPhamService();
    MauSacService serviceMs = new MauSacService();
    ChatLieuService serviceCl = new ChatLieuService();
    SizeService serviceSize = new SizeService();
    ThuongHieuService serviceth = new ThuongHieuService();
    SanPhamChiTietService serviceSpct = new SanPhamChiTietService();
    DefaultComboBoxModel<MauSac> cbbModelMauSac = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<ChatLieu> cbbModelChatLieu = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<ThuongHieu> cbbModelth = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<Size> cbbModelSize = new DefaultComboBoxModel<>();
    DefaultComboBoxModel<SanPham> cbbModelSp = new DefaultComboBoxModel<>();
    private static final String alpha = "abcdefghijklmnopqrstuvwxyz"; // a-z
    private static final String alphaUpperCase = alpha.toUpperCase(); // A-Z
    private static final String digits = "0123456789"; // 0-9
    private static final String ALPHA_NUMERIC = alpha + alphaUpperCase + digits;
    List<MauSac> listMs = new ArrayList<>();
    List<ChatLieu> listCl = new ArrayList<>();
    List<ThuongHieu> listM = new ArrayList<>();
    List<Size> listS = new ArrayList<>();
    List<SanPham> listSp = new ArrayList<>();
    List<SanPhamChiTiet> listSpct = new ArrayList<>();
    int count, soTrang, trang = 1;
    int countSpct, soTrangSpct, trangSpct = 1;

    public SanPhamJPanel() {
        initComponents();
        modelSp = (DefaultTableModel) tbSanPham.getModel();
        modelSpct = (DefaultTableModel) tbSanPhamChiTiet.getModel();
        modelSize = (DefaultTableModel) tbThuocTinh.getModel();
        modelth = (DefaultTableModel) tbThuocTinh.getModel();
        modelCl = (DefaultTableModel) tbThuocTinh.getModel();
        modelMs = (DefaultTableModel) tbThuocTinh.getModel();
        cbbModelMauSac = (DefaultComboBoxModel) cbbMauSac.getModel();
        cbbModelSize = (DefaultComboBoxModel) cbbSize.getModel();
        cbbModelChatLieu = (DefaultComboBoxModel) cbbChatLieu.getModel();
        cbbModelSp = (DefaultComboBoxModel) cbbTenSanPham.getModel();
        cbbModelth = (DefaultComboBoxModel) cbbTh.getModel();
        loaiMauSac();
        loaiChatLieu();
        loaiTh();
        loaiSize();
        tenSp();
        countDbSp();
        countDbSpct();
        if (count % 5 == 0) {
            soTrang = count / 5;
        } else {
            soTrang = count / 5 + 1;
        }
        if (countSpct % 5 == 0) {
            soTrangSpct = countSpct / 5;
        } else {
            soTrangSpct = countSpct / 5 + 1;
        }
        listSp = serviceSp.resultPage(trang, 5);
        listSpct = serviceSpct.resultPage(trang, 5);
        lbSoTrang.setText("1/" + soTrang);
        lbTrang.setText("1");
        lbSoTrangSpct.setText("1/" + soTrangSpct);
        lbTrangSpct.setText("1");
        showDataSanPham(listSp);
        showDataSize(serviceSize.getALL());
        showDataSanPhamChiTiet(listSpct);
    }

    void fillTable() {
        int row = tbSanPham.getSelectedRow();
        tfMaSp.setText(tbSanPham.getValueAt(row, 1).toString());
        tfTenSanPham.setText(tbSanPham.getValueAt(row, 2).toString());
    }

    void fillTableSpct() {
        int row = tbSanPhamChiTiet.getSelectedRow();
        tfMaSpct.setText(tbSanPhamChiTiet.getValueAt(row, 1).toString());
        cbbModelSp.setSelectedItem(tbSanPhamChiTiet.getValueAt(row, 2).toString());
        cbbModelMauSac.setSelectedItem(tbSanPhamChiTiet.getValueAt(row, 3).toString());
        cbbModelChatLieu.setSelectedItem(tbSanPhamChiTiet.getValueAt(row, 4).toString());
        cbbModelth.setSelectedItem(tbSanPhamChiTiet.getValueAt(row, 5).toString());
        cbbModelSize.setSelectedItem(tbSanPhamChiTiet.getValueAt(row, 7).toString());
        tfSoLuong.setText(tbSanPhamChiTiet.getValueAt(row, 6).toString());
        tfGia.setText(tbSanPhamChiTiet.getValueAt(row, 8).toString());
        taMoTa.setText(tbSanPhamChiTiet.getValueAt(row, 9).toString());

    }
    void fillTableThuocTinh(){
        int row = tbThuocTinh.getSelectedRow();
        tfMaThuocTinh.setText(tbThuocTinh.getValueAt(row, 1).toString());
        tfTenThuocTinh.setText(tbThuocTinh.getValueAt(row, 2).toString());
    }
//---------------------------load dữ liệu cbb------------------------------//

    void loaiMauSac() {
        cbbMauSac.removeAllItems();
        for (MauSac ms : serviceMs.getCbbMs()) {
            cbbModelMauSac.addElement(ms);
        }
    }

    void tenSp() {
        cbbTenSanPham.removeAllItems();
        for (SanPham sp : serviceSp.getTenSp()) {
            cbbModelSp.addElement(sp);
        }
    }

    void loaiTh() {
        cbbTh.removeAllItems();
        for (ThuongHieu th : serviceth.getCbbTh()) {
            cbbModelth.addElement(th);
        }
    }

    void loaiSize() {
        cbbSize.removeAllItems();
        for (Size s : serviceSize.getCbbSize()) {
            cbbModelSize.addElement(s);
        }
    }

    void loaiChatLieu() {
        cbbChatLieu.removeAllItems();
        for (ChatLieu cl : serviceCl.getCbbCl()) {
            cbbModelChatLieu.addElement(cl);
        }
    }
//--------------------Lọc---------------------------

    void tenSpLoc() {
        cbbTenSanPham.removeAllItems();
        for (SanPham sp : serviceSp.getTenSp()) {
            cbbModelSp.addElement(sp);
        }
    }

//-------------------------------Showdata-------------------------------
    void showDataSanPham(List<SanPham> list) {
        int indexSp = 1;
        modelSp.setRowCount(0);
        for (SanPham sanPham : list) {
            modelSp.addRow(new Object[]{
                indexSp++,
                sanPham.getMaSp(),
                sanPham.getTenSp(),
                serviceSp.getSoLuongByID(Integer.parseInt(sanPham.getId() + "")),
                sanPham.getId()
            });
        }
    }

    void showDataSanPhamChiTiet(List<SanPhamChiTiet> list) {
        int indexSpct = 1;
        modelSpct.setRowCount(0);
        for (SanPhamChiTiet spct : list) {
            String maSp = serviceSp.getbyID(spct.getIdSp()).getMaSp();
            String tenSp = serviceSp.getbyID(spct.getIdSp()).getTenSp();
            String tenMs = serviceMs.getbyID(spct.getIdMs()).getTenMs();
            String tenCl = serviceCl.getbyID(spct.getIdCl()).getTenCL();
            String tenth = serviceth.getbyID(spct.getIdThuongHieu()).getTenTH();
            String tenSize = serviceSize.getbyID(spct.getIdSize()).getTenS();
            modelSpct.addRow(new Object[]{
                indexSpct++,
                //                spct.getId(),
                maSp,
                tenSp,
                tenMs,
                tenCl,
                tenth,
                spct.getSoLuong(),
                tenSize,
                spct.getGia(),
                spct.getMoTa(),
                spct.isTrangThai() == true ? "Còn" : "Hết"
            });
        }
    }

    void showDataSize(List<Size> list) {
        int index = 1;
        modelSize.setRowCount(0);
        for (Size s : list) {
            modelSize.addRow(new Object[]{
                index++,
                s.getMaS(),
                s.getTenS()
            });
        }
    }

    void showDatath(List<ThuongHieu> list) {
        int index = 1;
        modelth.setRowCount(0);
        for (ThuongHieu th : list) {
            modelth.addRow(new Object[]{
                index++,
                th.getMaTH(),
                th.getTenTH()
            });
        }
    }

    void showDataMs(List<MauSac> list) {
        int index = 1;
        modelMs.setRowCount(0);
        for (MauSac ms : list) {

            modelMs.addRow(new Object[]{
                index++,
                ms.getMaMs(),
                ms.getTenMs()
            });
        }
    }

    void showDataCl(List<ChatLieu> list) {
        int index = 1;
        modelCl.setRowCount(0);
        for (ChatLieu cl : list) {
            modelCl.addRow(new Object[]{
                index++,
                cl.getMaCl(),
                cl.getTenCL()
            });
        }
    }
    //--------------------getForm Thuộc Tính--------------------------------//
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

    Size getFormSize() {
        return new Size(randomAlphaNumeric(6), tfTenThuocTinh.getText(), true);
    }

    ChatLieu getFormCl() {
        return new ChatLieu(randomAlphaNumeric(6), tfTenThuocTinh.getText(), true);
    }

    ThuongHieu getFormth() {
        return new ThuongHieu(randomAlphaNumeric(6), tfTenThuocTinh.getText(), true);
    }

    MauSac getFormMs() {
        return new MauSac(randomAlphaNumeric(6), tfTenThuocTinh.getText(), true);
    }

    //=========================END==============================
    //---------------------------getForm Sản Phẩm ---------------------------//
    SanPham getFormSP() {
        return new SanPham(randomAlphaNumeric(6), tfTenSanPham.getText(), true);
    }

    SanPhamChiTiet getFormSPCT() {
        String sp = cbbTenSanPham.getSelectedItem().toString();
        int sanPham = serviceSpct.idSP(sp);
        String mau = cbbMauSac.getSelectedItem().toString();
        int mauSac = serviceSpct.idMs(mau);
        String thieu = cbbTh.getSelectedItem().toString();
        int ThuongHieu = serviceSpct.idTH(thieu);
        String cl = cbbChatLieu.getSelectedItem().toString();
        int chatLieu = serviceSpct.idCl(cl);
        String s = cbbSize.getSelectedItem().toString();
        int size = serviceSpct.idSize(s);
        return new SanPhamChiTiet(sanPham, mauSac, chatLieu, size, ThuongHieu, taMoTa.getText(), Integer.parseInt(tfSoLuong.getText()), Float.parseFloat(tfGia.getText()), true);
    }

    //========================phân trang====================//
    void countDbSp() {
        try {
            String sql = "select COUNT(*) from SANPHAM";
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        pnTong = new javax.swing.JTabbedPane();
        jPanel5 = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        tfTenSanPham = new javax.swing.JTextField();
        tfMaSp = new javax.swing.JTextField();
        jPanel10 = new javax.swing.JPanel();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        tfTimKiem = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbSanPham = new javax.swing.JTable();
        btnLast = new javax.swing.JButton();
        btnPrev = new javax.swing.JButton();
        lbTrang = new javax.swing.JLabel();
        btnNext = new javax.swing.JButton();
        btnFert = new javax.swing.JButton();
        lbSoTrang = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        tfMaSpct = new javax.swing.JTextField();
        tfSoLuong = new javax.swing.JTextField();
        tfGia = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        taMoTa = new javax.swing.JTextArea();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        cbbTh = new javax.swing.JComboBox<>();
        cbbSize = new javax.swing.JComboBox<>();
        cbbMauSac = new javax.swing.JComboBox<>();
        cbbChatLieu = new javax.swing.JComboBox<>();
        jPanel15 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        cbbTenSanPham = new javax.swing.JComboBox<>();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jPanel13 = new javax.swing.JPanel();
        tfTimKiemSpct = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        jScrollPane5 = new javax.swing.JScrollPane();
        tbSanPhamChiTiet = new javax.swing.JTable();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        lbTrangSpct = new javax.swing.JLabel();
        lbSoTrangSpct = new javax.swing.JLabel();
        jPanel7 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel28 = new javax.swing.JLabel();
        rdSize = new javax.swing.JRadioButton();
        rdMauSac = new javax.swing.JRadioButton();
        rdChatLieu = new javax.swing.JRadioButton();
        rdMu = new javax.swing.JRadioButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        tfMaThuocTinh = new javax.swing.JTextField();
        tfTenThuocTinh = new javax.swing.JTextField();
        jPanel14 = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tbThuocTinh = new javax.swing.JTable();

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jPanel8.setBackground(new java.awt.Color(255, 255, 255));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sản Phẩm"));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel5.setText("Mã Sản Phẩm");

        jLabel7.setText("Tên Sản Phẩm");

        tfMaSp.setEnabled(false);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(18, 18, 18)
                        .addComponent(tfTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addGap(18, 18, 18)
                        .addComponent(tfMaSp)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfMaSp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(tfTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23))
        );

        jPanel10.setBackground(new java.awt.Color(255, 255, 255));
        jPanel10.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton9.setText("Thêm");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton10.setText("Sửa");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jButton11.setText("Chi Tiết Sản Phẩm");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setText("Làm mới");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(80, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton12)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel8Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        jPanel11.setBackground(new java.awt.Color(255, 255, 255));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder("Dạnh Sách Sản Phẩm"));

        tfTimKiem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimKiemKeyTyped(evt);
            }
        });

        jLabel8.setText("Tìm Kiếm");

        tbSanPham.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "Mã Sản Phẩm", "Tên Sản Phẩm", "Số Lượng", "IDSp"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbSanPham.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tbSanPham);
        if (tbSanPham.getColumnModel().getColumnCount() > 0) {
            tbSanPham.getColumnModel().getColumn(4).setMinWidth(0);
            tbSanPham.getColumnModel().getColumn(4).setPreferredWidth(0);
            tbSanPham.getColumnModel().getColumn(4).setMaxWidth(0);
        }

        btnLast.setText("<<");
        btnLast.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLastActionPerformed(evt);
            }
        });

        btnPrev.setText("<");
        btnPrev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPrevActionPerformed(evt);
            }
        });

        lbTrang.setText("jLabel3");

        btnNext.setText(">");
        btnNext.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNextActionPerformed(evt);
            }
        });

        btnFert.setText(">>");
        btnFert.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFertActionPerformed(evt);
            }
        });

        lbSoTrang.setText("jLabel4");

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 886, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel11Layout.createSequentialGroup()
                                .addComponent(jLabel8)
                                .addGap(18, 18, 18)
                                .addComponent(tfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel11Layout.createSequentialGroup()
                        .addGap(237, 237, 237)
                        .addComponent(btnLast, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(23, 23, 23)
                        .addComponent(btnPrev, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbTrang)
                        .addGap(18, 18, 18)
                        .addComponent(btnNext, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btnFert, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(31, 31, 31)
                        .addComponent(lbSoTrang)))
                .addContainerGap(74, Short.MAX_VALUE))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTimKiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8))
                .addGap(10, 10, 10)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLast)
                    .addComponent(btnPrev)
                    .addComponent(lbTrang)
                    .addComponent(btnNext)
                    .addComponent(btnFert)
                    .addComponent(lbSoTrang))
                .addContainerGap(347, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnTong.addTab("Sản Phẩm", jPanel5);

        jPanel6.setBackground(new java.awt.Color(255, 255, 255));

        jPanel9.setBackground(new java.awt.Color(255, 255, 255));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder("Thông Tin Sản Phẩm"));

        jLabel9.setText("Mã SP");

        jLabel10.setText("Tên SP");

        jLabel11.setText("Số Lượng");

        jLabel12.setText("Giá");

        jLabel13.setText("Mô Tả");

        tfMaSpct.setEnabled(false);

        taMoTa.setColumns(20);
        taMoTa.setRows(5);
        jScrollPane2.setViewportView(taMoTa);

        jLabel14.setText("Size");

        jLabel15.setText("Màu Sắc");

        jLabel16.setText("Chất Liệu");

        jLabel20.setText("Thương Hiệu");

        jPanel15.setBackground(new java.awt.Color(255, 255, 255));
        jPanel15.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton2.setText("Thêm Sản Phẩm");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Cập Nhật Sản Phẩm");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Làm Mới");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Quay Lại Sản Phẩm");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(56, Short.MAX_VALUE))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton2)
                .addGap(28, 28, 28)
                .addComponent(jButton3)
                .addGap(26, 26, 26)
                .addComponent(jButton4)
                .addGap(34, 34, 34)
                .addComponent(jButton5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        cbbTenSanPham.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton6.setText("+");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("+");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton13.setText("+");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("+");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jLabel13))
                .addGap(26, 26, 26)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 310, Short.MAX_VALUE)
                    .addComponent(tfGia)
                    .addComponent(tfSoLuong)
                    .addComponent(tfMaSpct)
                    .addComponent(cbbTenSanPham, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(65, 65, 65)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel14)
                    .addComponent(jLabel16)
                    .addComponent(jLabel20))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(cbbTh, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cbbChatLieu, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, 121, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton6)
                    .addComponent(jButton7)
                    .addComponent(jButton13)
                    .addComponent(jButton14))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 26, Short.MAX_VALUE)
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel9Layout.createSequentialGroup()
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(tfMaSpct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel14)
                            .addComponent(cbbSize, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton6))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(25, 25, 25)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel10)
                                    .addComponent(cbbTenSanPham, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel15)
                                    .addComponent(cbbMauSac, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton7))))
                        .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel11)
                                    .addComponent(tfSoLuong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel16)
                                    .addComponent(cbbChatLieu, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton13))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(tfGia, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel13)
                                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel9Layout.createSequentialGroup()
                                .addGap(76, 76, 76)
                                .addGroup(jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(cbbTh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel20)
                                    .addComponent(jButton14)))))
                    .addComponent(jPanel15, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(21, Short.MAX_VALUE))
        );

        jPanel13.setBackground(new java.awt.Color(255, 255, 255));
        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder("Dạnh Sách Sản Phẩm"));

        tfTimKiemSpct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tfTimKiemSpctActionPerformed(evt);
            }
        });
        tfTimKiemSpct.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tfTimKiemSpctKeyTyped(evt);
            }
        });

        jLabel21.setText("Tìm Kiếm");

        tbSanPhamChiTiet.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "STT", "MASP", "TENSP", "MAUSAC", "CHATLIEU", "THUONGHIEU", "SOLUONG", "SIZE", "GIA", "MOTA", "TRANGTHAI"
            }
        ));
        tbSanPhamChiTiet.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbSanPhamChiTietMouseClicked(evt);
            }
        });
        jScrollPane5.setViewportView(tbSanPhamChiTiet);

        jButton15.setText("<<");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("<");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText(">");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setText(">>");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        lbTrangSpct.setText("jLabel3");

        lbSoTrangSpct.setText("jLabel4");

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 887, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel13Layout.createSequentialGroup()
                                .addComponent(jLabel21)
                                .addGap(18, 18, 18)
                                .addComponent(tfTimKiemSpct, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addGroup(jPanel13Layout.createSequentialGroup()
                        .addGap(205, 205, 205)
                        .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbTrangSpct)
                        .addGap(18, 18, 18)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(30, 30, 30)
                        .addComponent(lbSoTrangSpct)))
                .addContainerGap(314, Short.MAX_VALUE))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(16, 16, 16)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(tfTimKiemSpct, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel21))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 201, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton15)
                    .addComponent(jButton16)
                    .addComponent(lbTrangSpct)
                    .addComponent(jButton17)
                    .addComponent(jButton18)
                    .addComponent(lbSoTrangSpct))
                .addContainerGap(210, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel6Layout.createSequentialGroup()
                        .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 241, Short.MAX_VALUE))
                    .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnTong.addTab("Sản Phẩm Chi Tiết", jPanel6);

        jPanel7.setBackground(new java.awt.Color(255, 255, 255));

        jPanel12.setBackground(new java.awt.Color(255, 255, 255));
        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder("Thiết Lập Thuộc Tính"));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jButton1.setText("Thêm");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton19.setText("Sửa");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton28.setText("Làm mới");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(49, 49, 49)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton19, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(jButton1)
                .addGap(27, 27, 27)
                .addComponent(jButton19)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
                .addComponent(jButton28)
                .addGap(25, 25, 25))
        );

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));
        jPanel3.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel22.setText("Size");

        jLabel23.setText("Màu Sắc");

        jLabel24.setText("Chất Liệu");

        jLabel28.setText("Thương Hiệu");

        buttonGroup1.add(rdSize);
        rdSize.setSelected(true);
        rdSize.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdSizeActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdMauSac);
        rdMauSac.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMauSacActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdChatLieu);
        rdChatLieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdChatLieuActionPerformed(evt);
            }
        });

        buttonGroup1.add(rdMu);
        rdMu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rdMuActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel22)
                        .addGap(48, 48, 48)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdSize)
                            .addComponent(rdMauSac))))
                .addGap(37, 37, 37)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel28)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel24)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(rdMu)
                            .addComponent(rdChatLieu))))
                .addContainerGap(34, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(39, 39, 39)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel24)
                    .addComponent(rdChatLieu)
                    .addComponent(rdSize))
                .addGap(47, 47, 47)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(rdMu)
                    .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel28)
                        .addComponent(jLabel23))
                    .addComponent(rdMauSac))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));
        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("Mã Thuộc Tính");

        jLabel2.setText("Tên Thuộc Tính");

        tfMaThuocTinh.setEnabled(false);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addGap(42, 42, 42)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(tfMaThuocTinh)
                    .addComponent(tfTenThuocTinh, javax.swing.GroupLayout.DEFAULT_SIZE, 213, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(tfMaThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(tfTenThuocTinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(56, 56, 56))
        );

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(27, 27, 27))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(49, Short.MAX_VALUE))
        );

        jPanel14.setBackground(new java.awt.Color(255, 255, 255));
        jPanel14.setBorder(javax.swing.BorderFactory.createTitledBorder("Danh Sách Thuộc Tính"));

        tbThuocTinh.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "STT", "Mã Thuộc TÍnh", "Tên Thuộc Tính"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbThuocTinh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbThuocTinhMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tbThuocTinh);

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 877, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel14Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 313, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(129, 129, 129))
        );

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(321, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pnTong.addTab("Thuộc Tính Sản Phẩm", jPanel7);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnTong)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(pnTong, javax.swing.GroupLayout.Alignment.TRAILING)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?") == 0) {
            JOptionPane.showMessageDialog(this, serviceSp.addEntity(getFormSP()));
            listSp = serviceSp.resultPage(trang, 5);
            countDbSp();
            showDataSanPham(listSp);
            lbTrang.setText(trang + "");
            lbSoTrang.setText(trang + "/" + soTrang);
        }
        tenSp();

    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        tfMaSp.setText("");
        tfTenSanPham.setText("");
        tfTimKiem.setText("");
        showDataSanPham(serviceSp.resultPage(trang, 5));
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        if (rdSize.isSelected()) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?") == 0) {
                JOptionPane.showMessageDialog(this, serviceSize.addEntity(getFormSize()));
                showDataSize(serviceSize.getALL());
                loaiSize();
            }
        } else if (rdChatLieu.isSelected()) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?") == 0) {
                JOptionPane.showMessageDialog(this, serviceCl.addEntity(getFormCl()));
                showDataCl(serviceCl.getALL());
                loaiChatLieu();
            }
        } else if (rdMu.isSelected()) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?") == 0) {
                JOptionPane.showMessageDialog(this, serviceth.addEntity(getFormth()));
                showDatath(serviceth.getALL());
                loaiTh();
            }
        } else if (rdMauSac.isSelected()) {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?") == 0) {
                JOptionPane.showMessageDialog(this, serviceMs.addEntity(getFormMs()));
                showDataMs(serviceMs.getALL());
                loaiMauSac();
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton28ActionPerformed

    private void rdSizeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdSizeActionPerformed
        // TODO add your handling code here:
        showDataSize(serviceSize.getALL());
    }//GEN-LAST:event_rdSizeActionPerformed

    private void rdMauSacActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMauSacActionPerformed
        // TODO add your handling code here:
        showDataMs(serviceMs.getALL());
    }//GEN-LAST:event_rdMauSacActionPerformed

    private void rdChatLieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdChatLieuActionPerformed
        // TODO add your handling code here:
        showDataCl(serviceCl.getALL());
    }//GEN-LAST:event_rdChatLieuActionPerformed

    private void rdMuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rdMuActionPerformed
        // TODO add your handling code here:
        showDatath(serviceth.getALL());
    }//GEN-LAST:event_rdMuActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(SanPhamJPanel.this);
        ViewKichThuoc vs = new ViewKichThuoc();
        vs.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cbbSize.setSelectedItem(0);
                loaiSize();
            }

        });
        vs.setVisible(true);
        new ViewThuongHieu().dispose();
        new ViewChatvai().dispose();
        new ViewMauSac().dispose();


    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(SanPhamJPanel.this);
        ViewMauSac vMs = new ViewMauSac();
        vMs.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cbbMauSac.setSelectedItem(0);
                loaiMauSac();
            }
        });
        vMs.setVisible(true);
        new ViewThuongHieu().dispose();
        new ViewChatvai().dispose();
//        new ViewSize().dispose();
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(SanPhamJPanel.this);
        ViewChatvai vCl = new ViewChatvai();
        vCl.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                cbbMauSac.setSelectedItem(0);
                loaiChatLieu();
            }

        });
        vCl.setVisible(true);
        new ViewThuongHieu().dispose();
//        new ViewSize().dispose();
        new ViewMauSac().dispose();
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        JFrame jFrame = (JFrame) SwingUtilities.getWindowAncestor(SanPhamJPanel.this);
        ViewThuongHieu vth = new ViewThuongHieu();
        vth.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                loaiTh();
            }

        });
        vth.setVisible(true);
        new ViewKichThuoc().dispose();
        new ViewChatvai().dispose();
        new ViewMauSac().dispose();
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        try {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?") == 0) {

                JOptionPane.showMessageDialog(this, serviceSpct.addEntity(getFormSPCT()));

                listSp = serviceSp.resultPage(trang, 5);
                countDbSp();
                showDataSanPham(listSp);
                showDataSanPhamChiTiet(serviceSpct.resultPage(trang, 5));
                lbTrang.setText(trang + "");
                lbSoTrang.setText(trang + "/" + soTrang);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }//GEN-LAST:event_jButton2ActionPerformed

    private void tbSanPhamMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamMouseClicked
        // TODO add your handling code here:
//        try {
//            if (evt.getClickCount() == 2) {
//                int row = tbSanPham.getSelectedRow();
//                String maSp = tbSanPham.getValueAt(row, 1).toString();
//                pnTong.setSelectedIndex(1);
//                showDataSanPhamChiTiet(serviceSpct.getAllByMaSp(maSp));
//            }
//        } catch (Exception e) {
//        }

        fillTable();
    }//GEN-LAST:event_tbSanPhamMouseClicked

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        cbbTenSanPham.setEnabled(true);
        tfTimKiemSpct.setText("");
        tfMaSpct.setText("");
        tfSoLuong.setText("");
        taMoTa.setText("");
        tfTimKiemSpct.setText("");
        showDataSanPhamChiTiet(serviceSpct.resultPage(trangSpct, 5));
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        // TODO add your handling code here:
        if (rdSize.isSelected()) {
            try {
                if (JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?") == 0) {
                    Size s = new Size();
                    s = serviceSize.getALL().get(tbThuocTinh.getSelectedRow());
                    JOptionPane.showMessageDialog(this, serviceSize.updateEntity(getFormSize(), s.getId()));
                    JOptionPane.showMessageDialog(this, s.getId());
                    listS = serviceSize.getALL();
                    showDataSize(listS);
                    loaiSize();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Chưa chọn dòng để sửa");
            }
        } else if (rdChatLieu.isSelected()) {
            try {
                if (JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?") == 0) {
                    ChatLieu s = new ChatLieu();
                    s = serviceCl.getALL().get(tbThuocTinh.getSelectedRow());
                    JOptionPane.showMessageDialog(this, serviceCl.updateEntity(getFormCl(), s.getId()));
                    listCl = serviceCl.getALL();
                    showDataCl(listCl);
                    loaiChatLieu();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Chưa chọn dòng để sửa");
            }
        } else if (rdMu.isSelected()) {
            try {
                if (JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?") == 0) {
                    ThuongHieu m = new ThuongHieu();
                    m = serviceth.getALL().get(tbThuocTinh.getSelectedRow());
                    JOptionPane.showMessageDialog(this, serviceth.updateEntity(getFormth(), m.getId()));
                    listM = serviceth.getALL();
                    showDatath(listM);
                    loaiTh();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Chưa chọn dòng để sửa");
            }
        } else if (rdMauSac.isSelected()) {
            try {
                if (JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?") == 0) {
                    MauSac ms = new MauSac();
                    ms = serviceMs.getALL().get(tbThuocTinh.getSelectedRow());
                    JOptionPane.showMessageDialog(this, serviceMs.updateEntity(getFormMs(), ms.getId()));
                    listMs = serviceMs.getALL();
                    showDataMs(listMs);
                    loaiMauSac();
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Chưa chọn dòng để sửa");
            }
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        try {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn Sửa không ?") == 0) {
                SanPhamChiTiet spct = new SanPhamChiTiet();
                spct = serviceSpct.getALL().get(tbSanPhamChiTiet.getSelectedRow());
                JOptionPane.showMessageDialog(this, serviceSpct.updateEntity(getFormSPCT(), spct.getId()));
                showDataSanPham(serviceSp.resultPage(trang, 5));
                showDataSanPhamChiTiet(serviceSpct.resultPage(trang, 5));
                tenSp();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng để sửa");
        }
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        // TODO add your handling code here:
        try {
            if (JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?") == 0) {
                SanPham sp = new SanPham();
                sp = serviceSp.getALL().get(tbSanPham.getSelectedRow());
                JOptionPane.showMessageDialog(this, serviceSp.updateEntity(getFormSP(), sp.getId()));
                showDataSanPham(serviceSp.resultPage(trang, 5));
                showDataSanPhamChiTiet(serviceSpct.resultPage(trangSpct, 5));
                tenSp();
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Chưa chọn dòng để sửa");
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void btnNextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNextActionPerformed
        // TODO add your handling code here:
        if (trang < soTrang) {
            this.trang++;
            listSp = serviceSp.resultPage(trang, 5);
            countDbSp();
            showDataSanPham(listSp);
            lbTrang.setText(trang + "");
            lbSoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btnNextActionPerformed

    private void btnPrevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPrevActionPerformed
        // TODO add your handling code here:
        if (trang > 1) {
            this.trang--;
            listSp = serviceSp.resultPage(trang, 5);
            countDbSp();
            showDataSanPham(listSp);
            lbTrang.setText(trang + "");
            lbSoTrang.setText(trang + "/" + soTrang);
        }
    }//GEN-LAST:event_btnPrevActionPerformed

    private void btnLastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLastActionPerformed
        // TODO add your handling code here:
        trang = 1;
        showDataSanPham(serviceSp.resultPage(trang, 5));
        lbTrang.setText(trang + "");
        lbSoTrang.setText(trang + "/" + soTrang);
    }//GEN-LAST:event_btnLastActionPerformed

    private void btnFertActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFertActionPerformed
        // TODO add your handling code here:
        trang = soTrang;
        showDataSanPham(serviceSp.resultPage(trang, 5));
        lbTrang.setText(trang + "");
        lbSoTrang.setText(trang + "/" + soTrang);
    }//GEN-LAST:event_btnFertActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        if (trangSpct < soTrangSpct) {
            this.trangSpct++;
            listSpct = serviceSpct.resultPage(trangSpct, 5);
            countDbSpct();
            showDataSanPhamChiTiet(listSpct);
            lbTrangSpct.setText(trangSpct + "");
            lbSoTrangSpct.setText(trangSpct + "/" + soTrangSpct);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        if (trangSpct > 1) {
            this.trangSpct--;
            countDbSpct();
            listSpct = serviceSpct.resultPage(trangSpct, 5);
            showDataSanPhamChiTiet(listSpct);
            lbTrangSpct.setText(trangSpct + "");
            lbSoTrangSpct.setText(trangSpct + "/" + soTrangSpct);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        trangSpct = 1;
        countDbSpct();
        listSpct = serviceSpct.resultPage(trangSpct, 5);
        showDataSanPhamChiTiet(listSpct);
        lbTrangSpct.setText(trangSpct + "");
        lbSoTrangSpct.setText(trangSpct + "/" + soTrangSpct);
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        // TODO add your handling code here:
        trangSpct = soTrangSpct;
        countDbSpct();
        showDataSanPhamChiTiet(serviceSpct.resultPage(trangSpct, 5));
        lbTrangSpct.setText(trangSpct + "");
        lbSoTrangSpct.setText(trangSpct + "/" + soTrangSpct);
    }//GEN-LAST:event_jButton18ActionPerformed

    private void tfTimKiemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemKeyTyped
        // TODO add your handling code here:
        showDataSanPham(serviceSp.search(tfTimKiem.getText()));
    }//GEN-LAST:event_tfTimKiemKeyTyped

    private void tfTimKiemSpctActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tfTimKiemSpctActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_tfTimKiemSpctActionPerformed

    private void tfTimKiemSpctKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tfTimKiemSpctKeyTyped
        // TODO add your handling code here:
        showDataSanPhamChiTiet(serviceSpct.searchSpct(tfTimKiemSpct.getText()));
    }//GEN-LAST:event_tfTimKiemSpctKeyTyped

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        try {

            int row = tbSanPham.getSelectedRow();
            String maSp = tbSanPham.getValueAt(row, 1).toString();
            pnTong.setSelectedIndex(1);
            cbbTenSanPham.setEnabled(false);
            cbbModelSp.setSelectedItem(tbSanPham.getValueAt(row, 2).toString());
            showDataSanPhamChiTiet(serviceSpct.getAllByMaSp(maSp));

        } catch (Exception e) {
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void tbSanPhamChiTietMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbSanPhamChiTietMouseClicked
        // TODO add your handling code here:
        fillTableSpct();
    }//GEN-LAST:event_tbSanPhamChiTietMouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        pnTong.setSelectedIndex(0);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void tbThuocTinhMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbThuocTinhMouseClicked
        // TODO add your handling code here:
        fillTableThuocTinh();
    }//GEN-LAST:event_tbThuocTinhMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFert;
    private javax.swing.JButton btnLast;
    private javax.swing.JButton btnNext;
    private javax.swing.JButton btnPrev;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JComboBox<String> cbbChatLieu;
    private javax.swing.JComboBox<String> cbbMauSac;
    private javax.swing.JComboBox<String> cbbSize;
    private javax.swing.JComboBox<String> cbbTenSanPham;
    private javax.swing.JComboBox<String> cbbTh;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JLabel lbSoTrang;
    private javax.swing.JLabel lbSoTrangSpct;
    private javax.swing.JLabel lbTrang;
    private javax.swing.JLabel lbTrangSpct;
    private javax.swing.JTabbedPane pnTong;
    private javax.swing.JRadioButton rdChatLieu;
    private javax.swing.JRadioButton rdMauSac;
    private javax.swing.JRadioButton rdMu;
    private javax.swing.JRadioButton rdSize;
    private javax.swing.JTextArea taMoTa;
    private javax.swing.JTable tbSanPham;
    private javax.swing.JTable tbSanPhamChiTiet;
    private javax.swing.JTable tbThuocTinh;
    private javax.swing.JTextField tfGia;
    private javax.swing.JTextField tfMaSp;
    private javax.swing.JTextField tfMaSpct;
    private javax.swing.JTextField tfMaThuocTinh;
    private javax.swing.JTextField tfSoLuong;
    private javax.swing.JTextField tfTenSanPham;
    private javax.swing.JTextField tfTenThuocTinh;
    private javax.swing.JTextField tfTimKiem;
    private javax.swing.JTextField tfTimKiemSpct;
    // End of variables declaration//GEN-END:variables
}
