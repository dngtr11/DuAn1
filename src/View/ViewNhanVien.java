/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package View;

import Model.NhanVien;
import Service.NhanVienService;
import java.lang.reflect.Array;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author admin
 */
public class ViewNhanVien extends javax.swing.JFrame {

    NhanVienService service = new NhanVienService();
    DefaultTableModel dtm = new DefaultTableModel();
    List<NhanVien> list = new ArrayList<>();

    /**
     * Creates new form ViewNhanVien
     */
    public ViewNhanVien() {
        initComponents();
        setLocationRelativeTo(null);
        dtm = (DefaultTableModel) tblNhanVien.getModel();
        loadTable();

    }

    public void loadTable() {
        dtm.setRowCount(0);
        ArrayList<NhanVien> danhSachNhanVien = service.getAll();
        int STT = 1;
        for (NhanVien nhanVien : danhSachNhanVien) {
            dtm.addRow(new Object[]{
                STT++,
                nhanVien.getMANHANVIEN(),
                nhanVien.getHOTEN(),
                nhanVien.getNGAYSINH(),
                (nhanVien.getGIOITINH()) ? "Nam" : "Nữ", // Giả sử GIOITINH là true cho nam và false cho nữ
                nhanVien.getDIACHI(),
                nhanVien.getSDT(),
                nhanVien.getEMAIL(),
                nhanVien.getMATKHAU(),
                (nhanVien.getTRANGTHAI()) ? "Hoạt động" : "Không hoạt động"
            });
        }
    }

    public void loadData() {

        int index = tblNhanVien.getSelectedRow();
        if (index != -1) {
            txtMaNV.setText(tblNhanVien.getValueAt(index, 1).toString());
            txtTenNV.setText(tblNhanVien.getValueAt(index, 2).toString());
            String ngaySinhStr = "";
            Object value = tblNhanVien.getValueAt(index, 3);
            if (value != null) {
                ngaySinhStr = value.toString();
            }

            try {
                Date ngaySinh = new SimpleDateFormat("yyyy-MM-dd").parse(ngaySinhStr);
                txtNgaySinh.setText(new SimpleDateFormat("yyyy-MM-dd").format(ngaySinh));
            } catch (Exception e) {
                e.printStackTrace();
            }

            Object gioiTinhObj = tblNhanVien.getValueAt(index, 4);
            Boolean gioiTinh = false;
            if (gioiTinhObj instanceof String) {
                String gioiTinhStr = (String) gioiTinhObj;
                gioiTinh = gioiTinhStr.equals("Nam");
            } else if (gioiTinhObj instanceof Boolean) {
                gioiTinh = (Boolean) gioiTinhObj;
            } else {

            }
            rbnNam.setSelected(gioiTinh);
            rbnNu.setSelected(!gioiTinh);
            txtDiaChi.setText(tblNhanVien.getValueAt(index, 5).toString());
            txtSDT.setText(tblNhanVien.getValueAt(index, 6).toString());
            txtEmail.setText(tblNhanVien.getValueAt(index, 7).toString());
            txtMatKhau.setText(tblNhanVien.getValueAt(index, 8).toString());
            Boolean trangThai = tblNhanVien.getValueAt(index, 9).equals("Hoạt Động");
            rbnHoatDong.setSelected(true);
            rbnKhongHoatDong.setSelected(!true);

        }
    }

    public void clear(NhanVien nv) {
        txtMaNV.setText("");
        txtDiaChi.setText("");
        txtEmail.setText("");
        txtMatKhau.setText("");
        txtTenNV.setText("");
        txtSDT.setText("");
        txtNgaySinh.setText("");
        rbnNam.setSelected(false);
        rbnNu.setSelected(false);
        rbnHoatDong.setSelected(false);
        rbnKhongHoatDong.setSelected(false);

    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        buttonGroup2 = new javax.swing.ButtonGroup();
        buttonGroup3 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtMaNV = new javax.swing.JTextField();
        txtTenNV = new javax.swing.JTextField();
        txtSDT = new javax.swing.JTextField();
        rbnNam = new javax.swing.JRadioButton();
        rbnNu = new javax.swing.JRadioButton();
        txtMatKhau = new javax.swing.JTextField();
        txtDiaChi = new javax.swing.JTextField();
        txtEmail = new javax.swing.JTextField();
        txtNgaySinh = new javax.swing.JTextField();
        rbnHoatDong = new javax.swing.JRadioButton();
        rbnKhongHoatDong = new javax.swing.JRadioButton();
        jPanel2 = new javax.swing.JPanel();
        btnThem = new javax.swing.JButton();
        btnSua = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        btnEXcel = new javax.swing.JButton();
        jPanel3 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblNhanVien = new javax.swing.JTable();
        jPanel4 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        txtTimTheoMa = new javax.swing.JTextField();
        jPanel5 = new javax.swing.JPanel();
        jLabel14 = new javax.swing.JLabel();
        cbbTrangThai = new javax.swing.JComboBox<>();
        btnChuyenTrangThai = new javax.swing.JButton();
        btnHoanTac = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 0, 0));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Thiết lập thông tin nhân viên");

        jLabel2.setText("Mã Nhân Viên");

        jLabel3.setText("Tên Nhân viên");

        jLabel4.setText("Số Điện Thoại");

        jLabel5.setText("Giới Tính");

        jLabel7.setText("Mật Khẩu");

        jLabel8.setText("Địa Chỉ");

        jLabel9.setText("Email");

        jLabel10.setText("Trạng Thái");

        jLabel11.setText("Ngày Sinh");

        buttonGroup1.add(rbnNam);
        rbnNam.setText("Nam");

        buttonGroup1.add(rbnNu);
        rbnNu.setText("Nữ");

        txtDiaChi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtDiaChiActionPerformed(evt);
            }
        });

        txtEmail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtEmailActionPerformed(evt);
            }
        });

        buttonGroup3.add(rbnHoatDong);
        rbnHoatDong.setText("Hoạt động");

        buttonGroup3.add(rbnKhongHoatDong);
        rbnKhongHoatDong.setText("Không hoạt động");

        btnThem.setText("Thêm");
        btnThem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnThemMouseClicked(evt);
            }
        });

        btnSua.setText("Sửa");
        btnSua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSuaActionPerformed(evt);
            }
        });

        jButton3.setText("Clear");
        jButton3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton3MouseClicked(evt);
            }
        });

        btnEXcel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/ICON/Print.png"))); // NOI18N
        btnEXcel.setText("EXPORT");
        btnEXcel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnEXcelMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnThem)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnSua)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEXcel)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnEXcel, btnSua, btnThem, jButton3});

        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(20, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnThem)
                    .addComponent(btnSua)
                    .addComponent(jButton3)
                    .addComponent(btnEXcel))
                .addContainerGap())
        );

        jPanel2Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnEXcel, btnSua, btnThem, jButton3});

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addGap(0, 0, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(160, 160, 160)
                        .addComponent(rbnNu)
                        .addGap(90, 90, 90))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(87, 87, 87)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(32, 32, 32)
                                .addComponent(txtSDT))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(32, 32, 32)
                                .addComponent(rbnNam))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(32, 32, 32)
                                .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(32, 32, 32)
                                .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(173, 173, 173)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel8)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(rbnHoatDong)
                        .addGap(18, 18, 18)
                        .addComponent(rbnKhongHoatDong)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(txtEmail, javax.swing.GroupLayout.DEFAULT_SIZE, 202, Short.MAX_VALUE)
                                .addComponent(txtDiaChi)))
                        .addGap(111, 111, 111))))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel7)
                .addGap(32, 32, 32)
                .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 202, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(111, 111, 111))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel7});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel8, jLabel9});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtMatKhau, txtSDT});

        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(jLabel8)
                    .addComponent(txtMaNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtDiaChi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel9)
                    .addComponent(txtTenNV, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtEmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jLabel10)
                    .addComponent(txtSDT, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbnHoatDong)
                    .addComponent(rbnKhongHoatDong))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel11)
                    .addComponent(rbnNam)
                    .addComponent(rbnNu)
                    .addComponent(txtNgaySinh, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(33, 33, 33))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(txtMatKhau, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel7))
                        .addGap(42, 42, 42))))
        );

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel2, jLabel3, jLabel4, jLabel5, jLabel7});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtDiaChi, txtEmail, txtMaNV, txtMatKhau, txtNgaySinh, txtSDT, txtTenNV});

        jPanel1Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel10, jLabel11, jLabel8, jLabel9});

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel12.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel12.setText("Danh Sách Nhân Viên");

        tblNhanVien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "STT", "Mã Nhân Viên", "Họ Tên", "Ngày Sinh", "Giới Tính", "Địa Chỉ", "SDT", "Email", "Mật Khẩu", "Trạng Thái"
            }
        ));
        tblNhanVien.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tblNhanVienMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tblNhanVien);

        jPanel4.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel13.setText("Tìm Nhân Viên Theo Mã");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(25, Short.MAX_VALUE)
                .addComponent(jLabel13)
                .addGap(19, 19, 19))
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(txtTimTheoMa)
                .addContainerGap())
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jLabel13)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtTimTheoMa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 10, Short.MAX_VALUE))
        );

        jPanel5.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel14.setText("Trạng Thái");

        cbbTrangThai.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Hoạt Động", "Không hoạt động" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel14)
                .addGap(51, 51, 51))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(14, Short.MAX_VALUE))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addComponent(jLabel14)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(cbbTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        btnChuyenTrangThai.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnChuyenTrangThai.setText("Chuyển Trạng Thái");
        btnChuyenTrangThai.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnChuyenTrangThaiActionPerformed(evt);
            }
        });

        btnHoanTac.setText("Hoàn Tác");
        btnHoanTac.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnHoanTacMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(77, 77, 77)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(btnHoanTac)
                        .addGap(54, 54, 54)
                        .addComponent(btnChuyenTrangThai))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(21, 21, 21)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 900, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jPanel4, jPanel5});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btnChuyenTrangThai, btnHoanTac});

        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGap(48, 48, 48)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 21, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 247, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnChuyenTrangThai, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btnHoanTac))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jPanel4, jPanel5});

        jPanel3Layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btnChuyenTrangThai, btnHoanTac});

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 936, Short.MAX_VALUE))
                .addContainerGap(28, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtDiaChiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtDiaChiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtDiaChiActionPerformed

    private void txtEmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtEmailActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtEmailActionPerformed

    private void tblNhanVienMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tblNhanVienMouseClicked
        loadData();
    }//GEN-LAST:event_tblNhanVienMouseClicked

    private void jButton3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton3MouseClicked
        // TODO add your handling code here:
        NhanVien nv = new NhanVien();
        clear(nv);
    }//GEN-LAST:event_jButton3MouseClicked

    private void btnThemMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnThemMouseClicked
        try {

            int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn thêm không ?");
            if (chon != JOptionPane.YES_OPTION) {
                return;
            }
            if (txtMaNV.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên!");
                return;
            }
            if (txtTenNV.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên");
                return;
            }
            if (!txtSDT.getText().matches("^0\\d{9}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
                return;
            }
            if (txtDiaChi.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền địa chỉ");
                return;
            }
            if (txtMatKhau.getText().equalsIgnoreCase("")) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền mật khẩu");
                return;
            }
            if (txtMatKhau.getText().length() < 5) {
                JOptionPane.showMessageDialog(this, "Mật khẩu gồm 5 ký tự trở lên");
                return;
            }

            NhanVien nv = new NhanVien();
            ArrayList<NhanVien> list = service.getAll();
            nv.setMANHANVIEN(txtMaNV.getText());
            for (NhanVien nhanVien : list) {
                if (txtMaNV.getText().equals(nhanVien.getMANHANVIEN())) {
                    JOptionPane.showMessageDialog(this, "Mã nhân viên đã tồn tại!");
                    return;
                }
            }

            nv.setHOTEN(txtTenNV.getText());

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date ngaySinh = sdf.parse(txtNgaySinh.getText());
                nv.setNGAYSINH(new java.sql.Date(ngaySinh.getTime()));  // Chuyển đổi sang java.sql.Date
            } catch (Exception e) {
                e.printStackTrace();
            }

            nv.setSDT(Integer.parseInt(txtSDT.getText()));
            nv.setEMAIL(txtEmail.getText());
            nv.setDIACHI(txtDiaChi.getText());
            nv.setMATKHAU(txtMatKhau.getText());

            if (rbnNam.isSelected()) {
                nv.setGIOITINH(true);
            } else {
                nv.setGIOITINH(false);
            }

            if (rbnHoatDong.isSelected()) {
                nv.setTRANGTHAI(true);
            } else {
                nv.setTRANGTHAI(false);
            }
            service.addNhanVien(nv);
            loadTable();
            clear(nv);
            JOptionPane.showMessageDialog(this, "thêm nhân viên thành công !");

        } catch (Exception e) {
        }


    }//GEN-LAST:event_btnThemMouseClicked

    private void btnSuaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSuaActionPerformed
        try {
            int chon = JOptionPane.showConfirmDialog(this, "Bạn có muốn sửa không ?");
            if (chon != JOptionPane.YES_OPTION) {
                return;
            }
            if (txtMaNV.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên!");
                return;
            }
            if (txtTenNV.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng nhập tên nhân viên");
                return;
            }
            if (!txtSDT.getText().matches("^0\\d{9}$")) {
                JOptionPane.showMessageDialog(this, "Số điện thoại không hợp lệ");
                return;
            }
            if (txtDiaChi.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền địa chỉ");
                return;
            }
            if (txtMatKhau.getText().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Vui lòng điền mật khẩu");
                return;
            }
            if (txtMatKhau.getText().length() < 5) {
                JOptionPane.showMessageDialog(this, "Mật khẩu gồm 5 ký tự trở lên");
                return;
            }

            NhanVien nv = new NhanVien();
            nv.setMANHANVIEN(txtMaNV.getText());
            nv.setHOTEN(txtTenNV.getText());

            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
                Date ngaySinh = sdf.parse(txtNgaySinh.getText());
                nv.setNGAYSINH(new java.sql.Date(ngaySinh.getTime()));  // Chuyển đổi sang java.sql.Date
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(this, "Ngày sinh không hợp lệ!");
                return;
            }

            nv.setSDT(Integer.parseInt(txtSDT.getText()));
            nv.setEMAIL(txtEmail.getText());
            nv.setDIACHI(txtDiaChi.getText());
            nv.setMATKHAU(txtMatKhau.getText());
            nv.setGIOITINH(rbnNam.isSelected());
            nv.setTRANGTHAI(rbnHoatDong.isSelected());

            service.UpdateNhanVien(nv);
            loadTable();
            clear(nv);
            JOptionPane.showMessageDialog(this, "Sửa nhân viên thành công!");

        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi!");
        }
    }//GEN-LAST:event_btnSuaActionPerformed

    private void btnChuyenTrangThaiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnChuyenTrangThaiActionPerformed
        try {
    String maNhanVien = txtTimTheoMa.getText().trim();
    boolean trangThai = rbnHoatDong.isSelected(); // Sử dụng true nếu chọn "Hoạt động", false nếu chọn "Không hoạt động"
        
    ArrayList<NhanVien> ketQuaTimKiem = new ArrayList<>();
        
    if (!maNhanVien.isEmpty()) {
        ketQuaTimKiem = service.timKiemNhanVien(maNhanVien);
    } else if (trangThai) {
        ketQuaTimKiem = service.timKiemNhanVienTheoTrangThai(true);
    } else {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã nhân viên hoặc chọn trạng thái.");
    }

    if (!ketQuaTimKiem.isEmpty()) {
        DefaultTableModel model = (DefaultTableModel) tblNhanVien.getModel();
        model.setRowCount(0); // Xóa dữ liệu hiện tại trong bảng
        int stt = 1;
        for (NhanVien nv : ketQuaTimKiem) {
            // Thêm một hàng mới vào mô hình bảng
            Object[] row = {
                stt++,
                nv.getMANHANVIEN(),
                nv.getHOTEN(),
                nv.getNGAYSINH(),
                (nv.getGIOITINH() ? "Nam" : "Nữ"),
                nv.getDIACHI(),
                nv.getSDT(),
                nv.getEMAIL(),
                nv.getMATKHAU(),
                (nv.getTRANGTHAI() ? "Hoạt động" : "Không hoạt động")
            };
            model.addRow(row);
        }
    } else {
        JOptionPane.showMessageDialog(this, "Không tìm thấy nhân viên phù hợp với điều kiện tìm kiếm.");
    }
} catch (Exception e) {
    JOptionPane.showMessageDialog(this, "Đã xảy ra lỗi: " + e.getMessage());
    e.printStackTrace();
}
    }//GEN-LAST:event_btnChuyenTrangThaiActionPerformed

    private void btnHoanTacMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnHoanTacMouseClicked
        loadTable();
    }//GEN-LAST:event_btnHoanTacMouseClicked

    private void btnEXcelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnEXcelMouseClicked

    }//GEN-LAST:event_btnEXcelMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewNhanVien.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewNhanVien().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnChuyenTrangThai;
    private javax.swing.JButton btnEXcel;
    private javax.swing.JButton btnHoanTac;
    private javax.swing.JButton btnSua;
    private javax.swing.JButton btnThem;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.ButtonGroup buttonGroup2;
    private javax.swing.ButtonGroup buttonGroup3;
    private javax.swing.JComboBox<String> cbbTrangThai;
    private javax.swing.JButton jButton3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JRadioButton rbnHoatDong;
    private javax.swing.JRadioButton rbnKhongHoatDong;
    private javax.swing.JRadioButton rbnNam;
    private javax.swing.JRadioButton rbnNu;
    private javax.swing.JTable tblNhanVien;
    private javax.swing.JTextField txtDiaChi;
    private javax.swing.JTextField txtEmail;
    private javax.swing.JTextField txtMaNV;
    private javax.swing.JTextField txtMatKhau;
    private javax.swing.JTextField txtNgaySinh;
    private javax.swing.JTextField txtSDT;
    private javax.swing.JTextField txtTenNV;
    private javax.swing.JTextField txtTimTheoMa;
    // End of variables declaration//GEN-END:variables
}
