/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.edusys.ui;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.dao.HocVienDAO;
import com.edusys.dao.KhoaHocDAO;
import com.edusys.dao.NguoiHocDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.entity.HocVien;
import com.edusys.entity.KhoaHoc;
import com.edusys.entity.NguoiHoc;

import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;

import java.util.List;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dino
 */
public class HocVienJDialog extends javax.swing.JDialog {

    ChuyenDeDAO CDdao = new ChuyenDeDAO();
    KhoaHocDAO KHdao = new KhoaHocDAO();
    NguoiHocDAO NHdao = new NguoiHocDAO();
    HocVienDAO HVdao = new HocVienDAO();

    private int selectedMaKH = -1;

    /**
     * Creates new form HocVienJDialog
     */
    public HocVienJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    void init() {
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("Edusys - Quản Lý Học Viên");
        fillComboBoxChuyenDe();
    }

    void fillComboBoxChuyenDe() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbo_chuyende.getModel();
        model.removeAllElements();
//        try {
        List<ChuyenDe> list = CDdao.selectAll();
        for (ChuyenDe cd : list) {
            model.addElement(cd);
        }
        fillComboBoxKhoaHoc();
//        } catch (Exception e) {
//            MsgBox.alter(this, "Lỗi truy vấn dữ liệu");
//        }
    }

//    void fillComboBoxKhoaHoc() {
//        DefaultComboBoxModel model = (DefaultComboBoxModel) cbo_khoahoc.getModel();
//        model.removeAllElements();
//        try {
//            ChuyenDe chuyenDe = (ChuyenDe) cbo_chuyende.getSelectedItem();
//            if (chuyenDe != null) {
//                List<KhoaHoc> list = KHdao.selectKhoaHocByChuyenDe(chuyenDe.getMaCD());
//                for (KhoaHoc khoaHoc : list) {
//                    model.addElement(khoaHoc);
//                }
//            }
//            fillTableHocVien();
//        } catch (Exception e) {
//            MsgBox.alter(this, "Lỗi truy vấn dữ liệu cbo khoahoc");
//        }
//    }
    void fillComboBoxKhoaHoc() {
        DefaultComboBoxModel model = (DefaultComboBoxModel) cbo_khoahoc.getModel();
        model.removeAllElements();
        ChuyenDe chuyende = (ChuyenDe) cbo_chuyende.getSelectedItem(); // lấy ra cd từ combobox
        if (chuyende != null) {
            List<KhoaHoc> list = KHdao.selectKhoaHocByChuyenDe(chuyende.getMaCD()); // đổ những khóa học của chuyên đề đc chọn thôi
            for (KhoaHoc kh : list) {
                model.addElement(kh);
            }
            this.fillTableHocVien();
        }
    }

    //nguoihoc
//     public void FilltableNguoiHoc(){
//         DefaultTableModel model = (DefaultTableModel) tbl_nguoihoc.getModel();
//         model.setRowCount(0);
//         try {
//                KhoaHoc kh = (KhoaHoc) cbo_khoahoc.getSelectedItem();  
////                System.out.println("MaKH11:" + kh.getMaKH());
//             if(kh != null){
//                 System.out.println("MaKH:" + kh.getMaKH());
////                 String keyword = txt_timkiem.getText();
//                 List<NguoiHoc> list = NHdao.selectNotInCourse(kh.getMaKH());
//                 for (NguoiHoc nh : list ){
//                     model.addRow(new Object[]{
//                         nh.getMaNH(), 
//                         nh.getHoTen(),
//                         nh.isGioiTinh() ? "Nam" : "Nữ", 
//                         nh.getNgaySinh(), 
//                         nh.getDienThoai(), 
//                         nh.getEmail()
//                     });
//                 }
//
////                   System.out.println("test");
//             }
//         } catch (Exception e) {
//             MsgBox.alter(this, "Lỗi truy vấn dữ liệu! fillNguoHoc");
//         }
//     }    
    void FilltableNguoiHoc() {
        DefaultTableModel model = (DefaultTableModel) tbl_nguoihoc.getModel();
        model.setRowCount(0);
        KhoaHoc khoahoc = (KhoaHoc) cbo_khoahoc.getSelectedItem();  // lấy ra thông tin đc chọn ở combobox
        String keywork = txt_timkiem.getText();
//        List<NguoiHoc> list = nhdao.selectNotlnCourse(khoahoc.getMaKH(), keywork); // không đc lấy người học có mặt trong khóa học này và phù hợp với tìm kiếm
        List<NguoiHoc> list = NHdao.selectNotlnCourse2(khoahoc.getMaKH(), keywork);
        for (NguoiHoc nh : list) { // duyệt nh rồi đưa lên table như bình thường
            model.addRow(new Object[]{
                nh.getMaNH(),
                nh.getHoTen(),
                nh.isGioiTinh() ? "Nam" : "Nữ",
                nh.getNgaySinh(),
                nh.getDienThoai(),
                nh.getEmail()
            });
        }
    }

    public void fillTableHocVien() {
        DefaultTableModel model = (DefaultTableModel) tbl_hocvien.getModel();
        model.setRowCount(0);
        KhoaHoc kh = (KhoaHoc) cbo_khoahoc.getSelectedItem();
        if (kh != null) {
            System.out.println("filltableHocvien MaKH: " + kh.getMaKH());
            List<HocVien> list = HVdao.selectByKHoaHoc(kh.getMaKH());
            System.out.println("list: " + list.size());
            for (int i = 0; i < list.size(); i++) {
                HocVien hv = list.get(i);
                String hoTen = NHdao.selectById(hv.getMaNH()).getHoTen();
                Object[] row = {
                    i + 1, hv.getMaHV(), hv.getMaNH(), hoTen, hv.getDiem()
                };
                model.addRow(row);
            }
        }
        FilltableNguoiHoc();

    }

//     void findBYname(){
//         DefaultTableModel model = (DefaultTableModel) tbl_nguoihoc.getModel();
//        model.setRowCount(0);
//        try {
//            KhoaHoc kh = (KhoaHoc) cbo_khoahoc.getSelectedItem();
//            if (kh != null) {
//                String name = txt_timkiem.getText();
//                List<NguoiHoc> list = NHdao.selectByKeyWord(kh.getMaKH(), name);
//                System.out.println("listFind:" + list.size());
//                for (NguoiHoc nguoiHoc : list) {
//                    model.addRow(new Object[]{
//                        nguoiHoc.getMaNH(),
//                        nguoiHoc.getHoTen(),
//                        nguoiHoc.isGioiTinh() ? "Nam" : "Nữ",
//                        nguoiHoc.getNgaySinh(),
//                        nguoiHoc.getDienThoai(),
//                        nguoiHoc.getEmail()
//                    });
//                }
//            }
//        } catch (Exception e) {
//            MsgBox.alter(this, "Lỗi tìm kiếm theo tên!");
//        }
//     }
    //chức năng
    void addHocVien() {
        KhoaHoc khoaHoc = (KhoaHoc) cbo_khoahoc.getSelectedItem();
        for (int row : tbl_nguoihoc.getSelectedRows()) {
            HocVien hv = new HocVien();
            hv.setMaKH(khoaHoc.getMaKH());
            hv.setDiem(0);
            hv.setMaNH((String) tbl_nguoihoc.getValueAt(row, 0));
            System.out.println("=>" + hv.getMaHV() + "-" + hv.getMaNH() + "-" + hv.getDiem());
            HVdao.insert(hv);
        }
        fillTableHocVien();

        tabs.setSelectedIndex(0);
    }

    void removeHocVien() {
        if (!Auth.isManager()) {
            MsgBox.alter(this, "Bạn  không đủ quyền để xóa học viên");
        } else {
            if (MsgBox.confirm(this, "Bạn muốn xóa các học viên được chọn ?")) {
                for (int row : tbl_hocvien.getSelectedRows()) {
                    int maHV = (Integer) tbl_hocvien.getValueAt(row, 1);
                    HVdao.delete(maHV);

                }
                fillTableHocVien();

            }
        }
    }

    void updateDiem() {
        for (int i = 0; i < tbl_hocvien.getRowCount(); i++) {
            int maHV = (Integer) tbl_hocvien.getValueAt(i, 1);
            HocVien hocVien = HVdao.selectById(maHV);
//            hocVien.setDiem((Double)tbl_hocvien.getValueAt(i, 4));
            hocVien.setDiem(Double.parseDouble(tbl_hocvien.getValueAt(i, 4).toString()));
            HVdao.update(hocVien);
        }
        MsgBox.alter(this, "Cập nhật điểm thành công !");
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        pn_chuyende = new javax.swing.JPanel();
        cbo_chuyende = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        pn_khoahoc = new javax.swing.JPanel();
        cbo_khoahoc = new javax.swing.JComboBox<>();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_hocvien = new javax.swing.JTable();
        btn_xoaKhoaHoc = new javax.swing.JButton();
        btn_capnhatdiemKhoaHoc = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tbl_nguoihoc = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        txt_timkiem = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        btn_themvaoKhoaHoc = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setText("CHUYÊN ĐỀ");

        pn_chuyende.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbo_chuyende.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_chuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_chuyendeActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_chuyendeLayout = new javax.swing.GroupLayout(pn_chuyende);
        pn_chuyende.setLayout(pn_chuyendeLayout);
        pn_chuyendeLayout.setHorizontalGroup(
            pn_chuyendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chuyendeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbo_chuyende, 0, 301, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_chuyendeLayout.setVerticalGroup(
            pn_chuyendeLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_chuyendeLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbo_chuyende, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel2.setText("KHÓA HỌC");

        pn_khoahoc.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        cbo_khoahoc.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        cbo_khoahoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbo_khoahocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout pn_khoahocLayout = new javax.swing.GroupLayout(pn_khoahoc);
        pn_khoahoc.setLayout(pn_khoahocLayout);
        pn_khoahocLayout.setHorizontalGroup(
            pn_khoahocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_khoahocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbo_khoahoc, 0, 320, Short.MAX_VALUE)
                .addContainerGap())
        );
        pn_khoahocLayout.setVerticalGroup(
            pn_khoahocLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pn_khoahocLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cbo_khoahoc, javax.swing.GroupLayout.DEFAULT_SIZE, 32, Short.MAX_VALUE)
                .addContainerGap())
        );

        tbl_hocvien.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "TT", "Mã Học Viên", "Mã Người Học", "Họ Tên", "Điểm"
            }
        ));
        jScrollPane1.setViewportView(tbl_hocvien);
        if (tbl_hocvien.getColumnModel().getColumnCount() > 0) {
            tbl_hocvien.getColumnModel().getColumn(0).setResizable(false);
            tbl_hocvien.getColumnModel().getColumn(0).setPreferredWidth(10);
            tbl_hocvien.getColumnModel().getColumn(1).setResizable(false);
            tbl_hocvien.getColumnModel().getColumn(1).setPreferredWidth(16);
            tbl_hocvien.getColumnModel().getColumn(2).setResizable(false);
            tbl_hocvien.getColumnModel().getColumn(2).setPreferredWidth(16);
            tbl_hocvien.getColumnModel().getColumn(3).setPreferredWidth(200);
            tbl_hocvien.getColumnModel().getColumn(4).setResizable(false);
            tbl_hocvien.getColumnModel().getColumn(4).setPreferredWidth(15);
        }

        btn_xoaKhoaHoc.setText("Xóa Khỏi Khóa Học");
        btn_xoaKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaKhoaHocActionPerformed(evt);
            }
        });

        btn_capnhatdiemKhoaHoc.setText("Cập Nhật Điểm");
        btn_capnhatdiemKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_capnhatdiemKhoaHocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 688, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_xoaKhoaHoc)
                        .addGap(27, 27, 27)
                        .addComponent(btn_capnhatdiemKhoaHoc)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 406, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_capnhatdiemKhoaHoc)
                    .addComponent(btn_xoaKhoaHoc))
                .addContainerGap(11, Short.MAX_VALUE))
        );

        tabs.addTab("Học Viên", jPanel1);

        tbl_nguoihoc.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã Người Học", "Họ Tên", "Giới Tính", "Ngày Sinh", "Điện Thoại", "Email"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tbl_nguoihoc);

        jPanel3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        txt_timkiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_timkiemActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(txt_timkiem)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(txt_timkiem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        jLabel3.setText("Tìm Kiếm");

        btn_themvaoKhoaHoc.setText("Thêm Vào Khóa Học");
        btn_themvaoKhoaHoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themvaoKhoaHocActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 688, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(jLabel3)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                                .addGap(0, 0, Short.MAX_VALUE)
                                .addComponent(btn_themvaoKhoaHoc)))))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(10, 10, 10)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 297, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addComponent(btn_themvaoKhoaHoc)
                .addGap(11, 11, 11))
        );

        tabs.addTab("Người Học", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(pn_chuyende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(273, 273, 273))
                            .addComponent(pn_khoahoc, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, 700, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(pn_khoahoc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pn_chuyende, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_capnhatdiemKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_capnhatdiemKhoaHocActionPerformed
        updateDiem();
    }//GEN-LAST:event_btn_capnhatdiemKhoaHocActionPerformed

    private void btn_xoaKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaKhoaHocActionPerformed
        removeHocVien();
    }//GEN-LAST:event_btn_xoaKhoaHocActionPerformed

    private void cbo_chuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_chuyendeActionPerformed
        fillComboBoxKhoaHoc();
    }//GEN-LAST:event_cbo_chuyendeActionPerformed

    private void btn_themvaoKhoaHocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themvaoKhoaHocActionPerformed
        addHocVien();
    }//GEN-LAST:event_btn_themvaoKhoaHocActionPerformed

    private void cbo_khoahocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbo_khoahocActionPerformed
        KhoaHoc kh = (KhoaHoc) cbo_khoahoc.getSelectedItem();
        if (kh != null) {
            fillTableHocVien();
        }
    }//GEN-LAST:event_cbo_khoahocActionPerformed

    private void txt_timkiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_timkiemActionPerformed
        FilltableNguoiHoc();
    }//GEN-LAST:event_txt_timkiemActionPerformed

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
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HocVienJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                HocVienJDialog dialog = new HocVienJDialog(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_capnhatdiemKhoaHoc;
    private javax.swing.JButton btn_themvaoKhoaHoc;
    private javax.swing.JButton btn_xoaKhoaHoc;
    private javax.swing.JComboBox<String> cbo_chuyende;
    private javax.swing.JComboBox<String> cbo_khoahoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JPanel pn_chuyende;
    private javax.swing.JPanel pn_khoahoc;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbl_hocvien;
    private javax.swing.JTable tbl_nguoihoc;
    private javax.swing.JTextField txt_timkiem;
    // End of variables declaration//GEN-END:variables
}
