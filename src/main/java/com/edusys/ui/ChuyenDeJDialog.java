/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package com.edusys.ui;

import com.edusys.dao.ChuyenDeDAO;
import com.edusys.entity.ChuyenDe;
import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;
import java.io.File;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Dino
 */
public class ChuyenDeJDialog extends javax.swing.JDialog {

    JFileChooser fileChoser = new JFileChooser();
    ChuyenDeDAO dao = new ChuyenDeDAO();
    int row = 0;

    /**
     * Creates new form ChuyenDeJDialog
     */
    public ChuyenDeJDialog(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        init();
    }

    public void fillTable() {
        DefaultTableModel model = (DefaultTableModel) tbl_chuyenDe.getModel();
        model.setRowCount(0);
        try {
            List<ChuyenDe> list = dao.selectAll();
            for (ChuyenDe nv : list) {
                Object[] row = {
                    nv.getMaCD(),
                    nv.getTenCD(),
                    nv.getHocPhi(),
                    nv.getThoiLuong(),
                    nv.getHinh()
                };
                model.addRow(row);
            }
        } catch (Exception e) {
            MsgBox.alter(this, "Lỗi truy vấn dữ liệu");
        }
    }

    void init() {
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("Edusys - Quản Lý Chuyên Đề");
        fillTable();
        updateStatus();
    }

    void chonAnh() {
        if (fileChoser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChoser.getSelectedFile();
            XImage.save(file);
            ImageIcon icon = XImage.read(file.getName());
            lbl_hinhAnh.setIcon(icon);
            lbl_hinhAnh.setToolTipText(file.getName());
        }
    }

    void setForm(ChuyenDe model) {
        txt_maCD.setText(model.getMaCD());
        txt_tenCD.setText(model.getTenCD());
        txt_hocphi.setText(String.valueOf(model.getHocPhi()));
        txt_thoiluong.setText(String.valueOf(model.getThoiLuong()));
        txt_mota.setText(model.getMoTa());
        //ktra lỡ k có hình ảnh 

        if (model.getHinh() != null && !model.getHinh().equals("")) {
            // Thực hiện xử lý khi trường hinh có giá trị
            lbl_hinhAnh.setIcon(XImage.read(model.getHinh()));
            lbl_hinhAnh.setToolTipText(model.getHinh());
        }

    }

    ChuyenDe getForm() {
        ChuyenDe cd = new ChuyenDe();
        cd.setMaCD(txt_maCD.getText());
        cd.setTenCD(txt_tenCD.getText());
        cd.setHocPhi(Double.parseDouble(txt_hocphi.getText()));
        cd.setThoiLuong(Integer.parseInt(txt_thoiluong.getText()));
        cd.setMoTa(txt_mota.getText());
        cd.setHinh(lbl_hinhAnh.getToolTipText());
        return cd;
    }

    void edit() {
        try {
            String MaCD = (String) tbl_chuyenDe.getValueAt(this.row, 0);
            ChuyenDe model = dao.selectById(MaCD);
            if (model != null) {
                setForm(model);
                updateStatus();
                tabs.setSelectedIndex(0);// chuyển tab khi click

            }
        } catch (Exception e) {
            MsgBox.alter(this, "Lỗi truy vấn dữ liệu");
        }
    }

//    void updateStatus() {
//        boolean edit = this.row >= 0;
//        boolean first = this.row == 0;
//        boolean last = this.row == tbl_chuyenDe.getRowCount() - 1;
//        txt_maCD.setEditable(edit);
//        //khi insert thì ko update, delete
//        btn_them.setEnabled(!edit);
//        btn_sua.setEnabled(edit);
//        btn_xoa.setEnabled(edit);
//
//        btn_first.setEnabled(edit && !first);
//        btn_prev.setEnabled(edit && !first);
//        btn_next.setEnabled(edit && !last);
//        btn_last.setEnabled(edit && !last);
//    }
    void updateStatus() {
    boolean edit = this.row >= 0;
    boolean first = this.row == 0;
    boolean last = this.row == tbl_chuyenDe.getRowCount() - 1;
//    txt_maCD.setEditable(edit);

    // Tắt nút "Thêm" khi đã chọn một dòng trong bảng
    btn_them.setEnabled(!edit);

    // Cho phép sử dụng nút "Sửa" và "Xóa" khi đã chọn một dòng
    btn_sua.setEnabled(edit);
    btn_xoa.setEnabled(edit);

    // Cho phép sử dụng nút "Mới" trong mọi trường hợp
    btn_moi.setEnabled(true);

    // Kiểm tra trạng thái của các nút chuyển trang
    btn_first.setEnabled(edit && !first);
    btn_prev.setEnabled(edit && !first);
    btn_next.setEnabled(edit && !last);
    btn_last.setEnabled(edit && !last);
}



    void clearForm() {
        this.setForm(new ChuyenDe());
        lbl_hinhAnh.setIcon(null);
        lbl_hinhAnh.setToolTipText("");
        this.updateStatus();
        row = -1;
//        updateStatus();
    }

//  private boolean validateForm() {
//    boolean valid = true;
//    StringBuilder errorMessage = new StringBuilder();
//
//    if (txt_thoiluong.getText().isEmpty()) {
//        errorMessage.append("Vui lòng nhập thời lượng.\n");
//        valid = false;
//    }
//
//    if (txt_hocphi.getText().isEmpty()) {
//        errorMessage.append("Vui lòng nhập học phí.\n");
//        valid = false;
//    }
//
//    if (txt_tenCD.getText().isEmpty()) {
//        errorMessage.append("Vui lòng nhập tên chương trình đào tạo.\n");
//        valid = false;
//    }
//
//    String maChuyenDe = txt_maCD.getText();
//    if (maChuyenDe.isEmpty()) {
//        errorMessage.append("Vui lòng nhập mã chuyên đề.\n");
//        valid = false;
//    }
//
//    if (!valid) {
//        JOptionPane.showMessageDialog(this, errorMessage.toString());
//    }
//
//    return valid;
//}

    private boolean validateForm() {
    boolean valid = true;
    StringBuilder errorMessage = new StringBuilder();

    if (txt_thoiluong.getText().isEmpty()) {
        errorMessage.append("Vui lòng nhập thời lượng.\n");
        valid = false;
    } else {
        try {
            int thoiluong = Integer.parseInt(txt_thoiluong.getText());
            if (thoiluong <= 0) {
                errorMessage.append("Thời lượng phải lớn hơn 0.\n");
                valid = false;
            }
        } catch (NumberFormatException e) {
            errorMessage.append("Thời lượng phải là số .\n");
            valid = false;
        }
    }

    if (txt_hocphi.getText().isEmpty()) {
        errorMessage.append("Vui lòng nhập học phí.\n");
        valid = false;
    } else {
        try {
            double hocphi = Double.parseDouble(txt_hocphi.getText());
            if (hocphi <= 0) {
                errorMessage.append("Học phí phải lớn hơn 0.\n");
                valid = false;
            }
        } catch (NumberFormatException e) {
            errorMessage.append("Học phí phải là số.\n");
            valid = false;
        }
    }

    if (txt_tenCD.getText().isEmpty()) {
        errorMessage.append("Vui lòng nhập tên chương trình đào tạo.\n");
        valid = false;
    }

    String maChuyenDe = txt_maCD.getText();
    if (maChuyenDe.isEmpty()) {
        errorMessage.append("Vui lòng nhập mã chuyên đề.\n");
        valid = false;
    }

    if (!valid) {
        JOptionPane.showMessageDialog(this, errorMessage.toString());
    }

    return valid;
}



    
    void insert() {
        ChuyenDe model = getForm();
        try {
            dao.insert(model);
            this.fillTable();
            this.clearForm();
            MsgBox.alter(this, "Thêm mới thành công");
        } catch (Exception e) {

            MsgBox.alter(this, "Thêm mới thất bại!");
        }
    }

    void update() {
        ChuyenDe model = getForm();
        try {
            dao.update(model);
            this.fillTable();
            MsgBox.alter(this, "Cập nhật thành công!");
        } catch (Exception e) {
            MsgBox.alter(this, "Cập nhật thất bại!");
        }
    }

    void delete() {
        if (!Auth.isManager()) {
            MsgBox.alter(this, "Bạn không có quyền xóa chuyên đề !");
        } else {
            if (MsgBox.confirm(this, "Bạn thực sự muốn xóa chuyên đề này ?")) {
                String manv = txt_maCD.getText();
                try {
                    dao.delete(manv);
                    this.fillTable();
                    this.clearForm();
                    MsgBox.alter(this, "Xóa thành công !");
                } catch (Exception e) {
                    MsgBox.alter(this, "Xóa thất bại !");
                }
            }
        }

    }

    void first() {
        row = 0;
        edit();
    }

    void prev() {
        if (row > 0) {
            row--;
            edit();
        }
    }

    void next() {
        if (row < tbl_chuyenDe.getRowCount() - 1) {
            row++;
            edit();
        }
    }

    void last() {
        row = tbl_chuyenDe.getRowCount() - 1;
        edit();
    }


    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel6 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        tabs = new javax.swing.JTabbedPane();
        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        lbl_hinhAnh = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txt_maCD = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        txt_tenCD = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txt_thoiluong = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_hocphi = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        txt_mota = new javax.swing.JTextArea();
        jPanel3 = new javax.swing.JPanel();
        btn_them = new javax.swing.JButton();
        btn_sua = new javax.swing.JButton();
        btn_xoa = new javax.swing.JButton();
        btn_moi = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        btn_first = new javax.swing.JButton();
        btn_prev = new javax.swing.JButton();
        btn_next = new javax.swing.JButton();
        btn_last = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tbl_chuyenDe = new javax.swing.JTable();

        jLabel6.setText("jLabel6");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 51, 255));
        jLabel1.setText("QUẢN LÝ CHUYÊN ĐỀ");

        jLabel2.setText("Hình logo");

        lbl_hinhAnh.setText("chọn hình");
        lbl_hinhAnh.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        lbl_hinhAnh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                lbl_hinhAnhMousePressed(evt);
            }
        });

        jLabel3.setText("Mã chuyên đề");

        jLabel4.setText("Tên chuyên đề");

        jLabel5.setText("Thời lượng (giờ)");

        jLabel7.setText("Học phí");

        jLabel8.setText("Mô tả chuyên đề");

        txt_mota.setColumns(20);
        txt_mota.setRows(5);
        jScrollPane2.setViewportView(txt_mota);

        jPanel3.setLayout(new java.awt.GridLayout(1, 4, 5, 5));

        btn_them.setText("Thêm");
        btn_them.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_themActionPerformed(evt);
            }
        });
        jPanel3.add(btn_them);

        btn_sua.setText("Sửa");
        btn_sua.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_suaActionPerformed(evt);
            }
        });
        jPanel3.add(btn_sua);

        btn_xoa.setText("Xóa");
        btn_xoa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_xoaActionPerformed(evt);
            }
        });
        jPanel3.add(btn_xoa);

        btn_moi.setText("Mới");
        btn_moi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_moiActionPerformed(evt);
            }
        });
        jPanel3.add(btn_moi);

        jPanel4.setLayout(new java.awt.GridLayout(1, 4, 5, 5));

        btn_first.setText("|<");
        btn_first.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_firstActionPerformed(evt);
            }
        });
        jPanel4.add(btn_first);

        btn_prev.setText("<<");
        btn_prev.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prevActionPerformed(evt);
            }
        });
        jPanel4.add(btn_prev);

        btn_next.setText(">>");
        btn_next.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nextActionPerformed(evt);
            }
        });
        jPanel4.add(btn_next);

        btn_last.setText(">|");
        btn_last.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_lastActionPerformed(evt);
            }
        });
        jPanel4.add(btn_last);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(lbl_hinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 72, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(txt_tenCD, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_maCD, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_thoiluong, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(txt_hocphi, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(72, 72, 72))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(26, 26, 26)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 256, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane2))
                .addGap(15, 15, 15))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_maCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_tenCD, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txt_thoiluong, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(14, 14, 14)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txt_hocphi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(lbl_hinhAnh, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tabs.addTab("Cập nhật", jPanel1);

        tbl_chuyenDe.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Mã CD", "Tên CD", "Học Phí", "Thời Lượng", "Hình"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tbl_chuyenDe.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tbl_chuyenDeMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tbl_chuyenDe);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 674, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 406, Short.MAX_VALUE)
                .addContainerGap())
        );

        tabs.addTab("Danh sách", jPanel2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(tabs))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(tabs, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_lastActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_lastActionPerformed
        last();
    }//GEN-LAST:event_btn_lastActionPerformed

    private void lbl_hinhAnhMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbl_hinhAnhMousePressed
        if (evt.getClickCount() == 2) {
            chonAnh();
        }
    }//GEN-LAST:event_lbl_hinhAnhMousePressed

    private void tbl_chuyenDeMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbl_chuyenDeMousePressed
        if (evt.getClickCount() == 2) {
            this.row = tbl_chuyenDe.rowAtPoint(evt.getPoint());
            edit();
        }
    }//GEN-LAST:event_tbl_chuyenDeMousePressed

    private void btn_themActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_themActionPerformed
        if(validateForm()){
        insert();}
    }//GEN-LAST:event_btn_themActionPerformed

    private void btn_suaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_suaActionPerformed
        if(validateForm()){
        update();}
    }//GEN-LAST:event_btn_suaActionPerformed

    private void btn_xoaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_xoaActionPerformed
        String maChuyenDe = txt_maCD.getText();
    if (maChuyenDe.isEmpty()) {
        JOptionPane.showMessageDialog(this, "Vui lòng nhập mã chuyên đề.");
        return; // Không thực hiện xóa nếu mã chuyên đề chưa được nhập
    }

    // Nếu mã chuyên đề đã được nhập, thực hiện hành động xóa
    delete();
    }//GEN-LAST:event_btn_xoaActionPerformed

    private void btn_moiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_moiActionPerformed
        clearForm();
    }//GEN-LAST:event_btn_moiActionPerformed

    private void btn_firstActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_firstActionPerformed
        first();
    }//GEN-LAST:event_btn_firstActionPerformed

    private void btn_prevActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_prevActionPerformed
        prev();
    }//GEN-LAST:event_btn_prevActionPerformed

    private void btn_nextActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nextActionPerformed
        next();
    }//GEN-LAST:event_btn_nextActionPerformed

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
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ChuyenDeJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ChuyenDeJDialog dialog = new ChuyenDeJDialog(new javax.swing.JFrame(), true);
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
    private javax.swing.JButton btn_first;
    private javax.swing.JButton btn_last;
    private javax.swing.JButton btn_moi;
    private javax.swing.JButton btn_next;
    private javax.swing.JButton btn_prev;
    private javax.swing.JButton btn_sua;
    private javax.swing.JButton btn_them;
    private javax.swing.JButton btn_xoa;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lbl_hinhAnh;
    private javax.swing.JTabbedPane tabs;
    private javax.swing.JTable tbl_chuyenDe;
    private javax.swing.JTextField txt_hocphi;
    private javax.swing.JTextField txt_maCD;
    private javax.swing.JTextArea txt_mota;
    private javax.swing.JTextField txt_tenCD;
    private javax.swing.JTextField txt_thoiluong;
    // End of variables declaration//GEN-END:variables
}
