/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package com.edusys.ui;

import com.edusys.utils.Auth;
import com.edusys.utils.MsgBox;
import com.edusys.utils.XImage;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.Timer;

/**
 *
 * @author Dino
 */
public class EdusysJFrame extends javax.swing.JFrame {

    /**
     * Creates new form EdusysJFrame
     */
    public EdusysJFrame() {
        initComponents();
        init();
    }
    
    void init() {
        setSize(1000, 600);
        setIconImage(XImage.getAppIcon());
        setLocationRelativeTo(null);
        setTitle("Hệ Thống Quản Lý Đào Tạo Edusys");
        new Timer(1000, new ActionListener() {
            SimpleDateFormat fomat = new SimpleDateFormat("hh:mm:ss a");
            
            @Override
            public void actionPerformed(ActionEvent e) {
                lblDongho.setText(fomat.format(new Date()));
                
            }
        }).start();
        this.openWelcome();
        this.openLogin();
        
    }
    
    void openWelcome() {
        new ChaoJDialog(this, true).setVisible(true);
        
    }
    
    void openLogin() {
        new DangNhapJDialog(this, true).setVisible(true);
    }
    
    void openDoiMatKhau() {
        if (Auth.isLogin()) {
            new DoiPassJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alter(this, "Vui lòng đăng nhập !");
        }
        
    }
    
    void openChuyenDe() {
        if (Auth.isLogin()) {
            new ChuyenDeJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alter(this, "Vui lòng đăng nhập !");
        }
    }
    
    void openThongKe(int index) {
        
        if (Auth.isLogin()) {
            ThongKeJDialog tk = new ThongKeJDialog(this, true);
//            tk.selectTab(index);
            tk.CheckRole(index);
            tk.setVisible(true);
        } else {
            MsgBox.alter(this, "Vui lòng đăng nhập !");
        }
    }
    
    void openNguoiHoc() {
        if (Auth.isLogin()) {
            new NguoiHocJDialog(this, true).setVisible(true);
//            new Nguoi2HocDialog(this, true).setVisible(true);
        } else {
            MsgBox.alter(this, "Vui lòng đăng nhập !");
        }
    }
    
    void openKhoaHoc() {
        if (Auth.isLogin()) {
            new KhoaHocJDialog(this, true).setVisible(true);
//             new Khoa2HocDialog(this, true).setVisible(true);
        } else {
            MsgBox.alter(this, "Vui lòng đăng nhập !");
        }
    }
    
    void openHocVien() {
        if (Auth.isLogin()) {
            new HocVienJDialog(this, true).setVisible(true);
//             new Hoc2testVienDialog(this, true).setVisible(true);
        } else {
            MsgBox.alter(this, "Vui lòng đăng nhập !");
        }
    }
    
    void openNhanVien() {
        if (Auth.isLogin()) {
            new NhanVienJDialog(this, true).setVisible(true);
        } else {
            MsgBox.alter(this, "Vui lòng đăng nhập !");
        }
    }
    
    void openDangNhap() {
        
        new DangNhapJDialog(this, true).setVisible(true);
//             new Hoc2testVienDialog(this, true).setVisible(true);
        
    }

    void openHuongDan() {
        try {
            Desktop.getDesktop().browse(new File("src/main/java/com/edusys/help/index.html").toURI());
        } catch (Exception e) {
            MsgBox.alter(this, "Không tìm thấy file hướng dẫn");
        }
        
    }
    
    void openGioiThieu() {
        new GioiThieuJDialog(this, true).setVisible(true);
    }
    
    void dangXuat() {
        Auth.clear();
        openLogin();
    }
    
    void ketThuc() {
        if (MsgBox.confirm(this, "Bạn muốn kết thúc phiên làm việc")) {
            System.exit(0);
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jToolBar1 = new javax.swing.JToolBar();
        btn_dangxuat = new javax.swing.JButton();
        btn_kthuc = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JToolBar.Separator();
        btn_chuyende = new javax.swing.JButton();
        btn_nguoihoc = new javax.swing.JButton();
        btn_khoahoc = new javax.swing.JButton();
        btn_hocvien = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JToolBar.Separator();
        btn_huongdan = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        lblDongho = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        mnu_hethong = new javax.swing.JMenu();
        mni_login = new javax.swing.JMenuItem();
        mni_dangxuat = new javax.swing.JMenuItem();
        jSeparator3 = new javax.swing.JPopupMenu.Separator();
        mni_doipass = new javax.swing.JMenuItem();
        jSeparator4 = new javax.swing.JPopupMenu.Separator();
        mni_exit = new javax.swing.JMenuItem();
        mnu_quanly = new javax.swing.JMenu();
        mni_nguoihoc = new javax.swing.JMenuItem();
        mni_chuyende = new javax.swing.JMenuItem();
        mni_khoahoc = new javax.swing.JMenuItem();
        mni_hocvien = new javax.swing.JMenuItem();
        jSeparator8 = new javax.swing.JPopupMenu.Separator();
        mni_nhanvien = new javax.swing.JMenuItem();
        mnu_thongke = new javax.swing.JMenu();
        mni_bangdiem = new javax.swing.JMenuItem();
        mni_luongnguoihoc = new javax.swing.JMenuItem();
        jSeparator5 = new javax.swing.JPopupMenu.Separator();
        mni_diemchuyende = new javax.swing.JMenuItem();
        jSeparator6 = new javax.swing.JPopupMenu.Separator();
        mni_doanhthu = new javax.swing.JMenuItem();
        mnu_trogiup = new javax.swing.JMenu();
        mni_huongdansudung = new javax.swing.JMenuItem();
        jSeparator7 = new javax.swing.JPopupMenu.Separator();
        mni_gioithieu = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jToolBar1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jToolBar1.setRollover(true);

        btn_dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Log out.png"))); // NOI18N
        btn_dangxuat.setText("Đăng xuất");
        btn_dangxuat.setFocusable(false);
        btn_dangxuat.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_dangxuat.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_dangxuatActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_dangxuat);

        btn_kthuc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Stop.png"))); // NOI18N
        btn_kthuc.setText("Kết thúc");
        btn_kthuc.setFocusable(false);
        btn_kthuc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_kthuc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_kthuc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_kthucActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_kthuc);
        jToolBar1.add(jSeparator1);

        btn_chuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Lists.png"))); // NOI18N
        btn_chuyende.setText("Chuyên đề");
        btn_chuyende.setFocusable(false);
        btn_chuyende.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_chuyende.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_chuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_chuyendeActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_chuyende);

        btn_nguoihoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Conference.png"))); // NOI18N
        btn_nguoihoc.setText("Người học");
        btn_nguoihoc.setFocusable(false);
        btn_nguoihoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_nguoihoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_nguoihoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_nguoihocActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_nguoihoc);

        btn_khoahoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Certificate.png"))); // NOI18N
        btn_khoahoc.setText("Khóa học");
        btn_khoahoc.setFocusable(false);
        btn_khoahoc.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_khoahoc.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_khoahoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_khoahocActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_khoahoc);

        btn_hocvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/User.png"))); // NOI18N
        btn_hocvien.setText("Học Viên");
        btn_hocvien.setFocusable(false);
        btn_hocvien.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_hocvien.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_hocvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_hocvienActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_hocvien);
        jToolBar1.add(jSeparator2);

        btn_huongdan.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Globe.png"))); // NOI18N
        btn_huongdan.setText("Hướng dẫn");
        btn_huongdan.setFocusable(false);
        btn_huongdan.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_huongdan.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_huongdan.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_huongdanActionPerformed(evt);
            }
        });
        jToolBar1.add(btn_huongdan);

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        lblDongho.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Alarm.png"))); // NOI18N
        lblDongho.setText("00:23:00 SA");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Info.png"))); // NOI18N
        jLabel2.setText("Hệ quản lý đào tạo");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lblDongho, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                .addComponent(lblDongho)
                .addComponent(jLabel2))
        );

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/logo.png"))); // NOI18N

        mnu_hethong.setText("Hệ thống");

        mni_login.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_L, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mni_login.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Key.png"))); // NOI18N
        mni_login.setText("Đăng nhập");
        mni_login.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_loginActionPerformed(evt);
            }
        });
        mnu_hethong.add(mni_login);

        mni_dangxuat.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_O, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mni_dangxuat.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Log out.png"))); // NOI18N
        mni_dangxuat.setText("Đăng xuất");
        mni_dangxuat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_dangxuatActionPerformed(evt);
            }
        });
        mnu_hethong.add(mni_dangxuat);
        mnu_hethong.add(jSeparator3);

        mni_doipass.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Refresh.png"))); // NOI18N
        mni_doipass.setText("Đổi mật khẩu");
        mni_doipass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_doipassActionPerformed(evt);
            }
        });
        mnu_hethong.add(mni_doipass);
        mnu_hethong.add(jSeparator4);

        mni_exit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F10, 0));
        mni_exit.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Stop.png"))); // NOI18N
        mni_exit.setText("Kết thúc");
        mni_exit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_exitActionPerformed(evt);
            }
        });
        mnu_hethong.add(mni_exit);

        jMenuBar1.add(mnu_hethong);

        mnu_quanly.setText("Quản lý");

        mni_nguoihoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mni_nguoihoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Conference.png"))); // NOI18N
        mni_nguoihoc.setText("Người học");
        mni_nguoihoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_nguoihocActionPerformed(evt);
            }
        });
        mnu_quanly.add(mni_nguoihoc);

        mni_chuyende.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mni_chuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Lists.png"))); // NOI18N
        mni_chuyende.setText("Chuyên đề");
        mni_chuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_chuyendeActionPerformed(evt);
            }
        });
        mnu_quanly.add(mni_chuyende);

        mni_khoahoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mni_khoahoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Certificate.png"))); // NOI18N
        mni_khoahoc.setText("Khóa học");
        mni_khoahoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_khoahocActionPerformed(evt);
            }
        });
        mnu_quanly.add(mni_khoahoc);

        mni_hocvien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mni_hocvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/User.png"))); // NOI18N
        mni_hocvien.setText("Học viên");
        mni_hocvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_hocvienActionPerformed(evt);
            }
        });
        mnu_quanly.add(mni_hocvien);
        mnu_quanly.add(jSeparator8);

        mni_nhanvien.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mni_nhanvien.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/User group.png"))); // NOI18N
        mni_nhanvien.setText("Nhân viên");
        mni_nhanvien.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_nhanvienActionPerformed(evt);
            }
        });
        mnu_quanly.add(mni_nhanvien);

        jMenuBar1.add(mnu_quanly);

        mnu_thongke.setText("Thống kê");

        mni_bangdiem.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mni_bangdiem.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Card file.png"))); // NOI18N
        mni_bangdiem.setText("Bảng điểm");
        mni_bangdiem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_bangdiemActionPerformed(evt);
            }
        });
        mnu_thongke.add(mni_bangdiem);

        mni_luongnguoihoc.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F2, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mni_luongnguoihoc.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Clien list.png"))); // NOI18N
        mni_luongnguoihoc.setText("Lượng người học");
        mni_luongnguoihoc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_luongnguoihocActionPerformed(evt);
            }
        });
        mnu_thongke.add(mni_luongnguoihoc);
        mnu_thongke.add(jSeparator5);

        mni_diemchuyende.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, java.awt.event.InputEvent.CTRL_DOWN_MASK));
        mni_diemchuyende.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Bar chart.png"))); // NOI18N
        mni_diemchuyende.setText("Điểm chuyên đề");
        mni_diemchuyende.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_diemchuyendeActionPerformed(evt);
            }
        });
        mnu_thongke.add(mni_diemchuyende);
        mnu_thongke.add(jSeparator6);

        mni_doanhthu.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.SHIFT_DOWN_MASK));
        mni_doanhthu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Dollar.png"))); // NOI18N
        mni_doanhthu.setText("Doanh thu ");
        mni_doanhthu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_doanhthuActionPerformed(evt);
            }
        });
        mnu_thongke.add(mni_doanhthu);

        jMenuBar1.add(mnu_thongke);

        mnu_trogiup.setText("Trợ giúp");

        mni_huongdansudung.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F1, 0));
        mni_huongdansudung.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Globe.png"))); // NOI18N
        mni_huongdansudung.setText("Hướng dẫn sử dụng");
        mni_huongdansudung.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_huongdansudungActionPerformed(evt);
            }
        });
        mnu_trogiup.add(mni_huongdansudung);
        mnu_trogiup.add(jSeparator7);

        mni_gioithieu.setIcon(new javax.swing.ImageIcon(getClass().getResource("/com/edusys/icon/Brick house.png"))); // NOI18N
        mni_gioithieu.setText("Giới thiệu sản phẩm");
        mni_gioithieu.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mni_gioithieuActionPerformed(evt);
            }
        });
        mnu_trogiup.add(mni_gioithieu);

        jMenuBar1.add(mnu_trogiup);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 869, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 409, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btn_dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_dangxuatActionPerformed
        dangXuat();

    }//GEN-LAST:event_btn_dangxuatActionPerformed

    private void mni_khoahocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_khoahocActionPerformed
        openKhoaHoc();
    }//GEN-LAST:event_mni_khoahocActionPerformed

    private void mni_exitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_exitActionPerformed
        ketThuc();
    }//GEN-LAST:event_mni_exitActionPerformed

    private void btn_kthucActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_kthucActionPerformed
        ketThuc();
    }//GEN-LAST:event_btn_kthucActionPerformed

    private void mni_dangxuatActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_dangxuatActionPerformed
        dangXuat();
    }//GEN-LAST:event_mni_dangxuatActionPerformed

    private void mni_doipassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_doipassActionPerformed
        openDoiMatKhau();
    }//GEN-LAST:event_mni_doipassActionPerformed

    private void mni_chuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_chuyendeActionPerformed
        openChuyenDe();
    }//GEN-LAST:event_mni_chuyendeActionPerformed

    private void btn_chuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_chuyendeActionPerformed
        openChuyenDe();
    }//GEN-LAST:event_btn_chuyendeActionPerformed

    private void mni_bangdiemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_bangdiemActionPerformed
        openThongKe(0);
    }//GEN-LAST:event_mni_bangdiemActionPerformed

    private void mni_diemchuyendeActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_diemchuyendeActionPerformed
        openThongKe(2);
    }//GEN-LAST:event_mni_diemchuyendeActionPerformed

    private void btn_nguoihocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_nguoihocActionPerformed
        openNguoiHoc();
    }//GEN-LAST:event_btn_nguoihocActionPerformed

    private void mni_nguoihocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_nguoihocActionPerformed
        openNguoiHoc();
    }//GEN-LAST:event_mni_nguoihocActionPerformed

    private void btn_khoahocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_khoahocActionPerformed
        openKhoaHoc();
    }//GEN-LAST:event_btn_khoahocActionPerformed

    private void mni_hocvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_hocvienActionPerformed
        openHocVien();
    }//GEN-LAST:event_mni_hocvienActionPerformed

    private void btn_hocvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_hocvienActionPerformed
        openHocVien();
    }//GEN-LAST:event_btn_hocvienActionPerformed

    private void mni_nhanvienActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_nhanvienActionPerformed
        openNhanVien();
    }//GEN-LAST:event_mni_nhanvienActionPerformed

    private void mni_gioithieuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_gioithieuActionPerformed
        openGioiThieu();
    }//GEN-LAST:event_mni_gioithieuActionPerformed

    private void mni_luongnguoihocActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_luongnguoihocActionPerformed
        openThongKe(1);
    }//GEN-LAST:event_mni_luongnguoihocActionPerformed

    private void mni_doanhthuActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_doanhthuActionPerformed
        if (!Auth.isManager()) {
            MsgBox.alter(this, "Bạn không đủ quyền để thực hiện chức năng này!");
        } else {
            openThongKe(3);
        }
    }//GEN-LAST:event_mni_doanhthuActionPerformed

    private void btn_huongdanActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btn_huongdanActionPerformed
        openHuongDan();
    }//GEN-LAST:event_btn_huongdanActionPerformed

    private void mni_huongdansudungActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_huongdansudungActionPerformed
        openHuongDan();
    }//GEN-LAST:event_mni_huongdansudungActionPerformed

    private void mni_loginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mni_loginActionPerformed
        openDangNhap();
    }//GEN-LAST:event_mni_loginActionPerformed

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
            java.util.logging.Logger.getLogger(EdusysJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EdusysJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EdusysJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EdusysJFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EdusysJFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btn_chuyende;
    private javax.swing.JButton btn_dangxuat;
    private javax.swing.JButton btn_hocvien;
    private javax.swing.JButton btn_huongdan;
    private javax.swing.JButton btn_khoahoc;
    private javax.swing.JButton btn_kthuc;
    private javax.swing.JButton btn_nguoihoc;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JToolBar.Separator jSeparator1;
    private javax.swing.JToolBar.Separator jSeparator2;
    private javax.swing.JPopupMenu.Separator jSeparator3;
    private javax.swing.JPopupMenu.Separator jSeparator4;
    private javax.swing.JPopupMenu.Separator jSeparator5;
    private javax.swing.JPopupMenu.Separator jSeparator6;
    private javax.swing.JPopupMenu.Separator jSeparator7;
    private javax.swing.JPopupMenu.Separator jSeparator8;
    private javax.swing.JToolBar jToolBar1;
    private javax.swing.JLabel lblDongho;
    private javax.swing.JMenuItem mni_bangdiem;
    private javax.swing.JMenuItem mni_chuyende;
    private javax.swing.JMenuItem mni_dangxuat;
    private javax.swing.JMenuItem mni_diemchuyende;
    private javax.swing.JMenuItem mni_doanhthu;
    private javax.swing.JMenuItem mni_doipass;
    private javax.swing.JMenuItem mni_exit;
    private javax.swing.JMenuItem mni_gioithieu;
    private javax.swing.JMenuItem mni_hocvien;
    private javax.swing.JMenuItem mni_huongdansudung;
    private javax.swing.JMenuItem mni_khoahoc;
    private javax.swing.JMenuItem mni_login;
    private javax.swing.JMenuItem mni_luongnguoihoc;
    private javax.swing.JMenuItem mni_nguoihoc;
    private javax.swing.JMenuItem mni_nhanvien;
    private javax.swing.JMenu mnu_hethong;
    private javax.swing.JMenu mnu_quanly;
    private javax.swing.JMenu mnu_thongke;
    private javax.swing.JMenu mnu_trogiup;
    // End of variables declaration//GEN-END:variables
}
