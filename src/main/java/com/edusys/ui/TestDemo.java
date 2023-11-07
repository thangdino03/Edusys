
package com.edusys.ui;

import com.edusys.dao.NhanVienDao;
import com.edusys.dao.ThongKeDAO;
import com.edusys.entity.NhanVien;
import java.util.List;

/**
 *
 * @author Dino
 */
public class TestDemo {
    public static void main(String[] args) {
       
        ThongKeDAO tkDAO = new ThongKeDAO();
        List<Object[]> list = tkDAO.getBangDiem(null);
        for (Object[] obj :list){
            System.out.println("=> "+obj[0]+"-"+obj[1]);
        }
        
//        NhanVienDao dao = new NhanVienDao();
        
//        dao.insert(new NhanVien("ThangNQ03","admin123", "Nguyễn Quang Thắng",  true));
//        dao.update(new NhanVien("ThangNQ03","admin123", "Nguyễn Quang Thắng",  true));
        
//        List<NhanVien> ls = dao.selectAll();
//        for (NhanVien nv :ls) {
//            System.out.println("=> "+nv.toString());
//        }
    }
}
