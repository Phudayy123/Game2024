package CSDL;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class runDatabase{
public void LeaderBoard() {
    JFrame frame = new JFrame("Leader Board");
    frame.setLocationRelativeTo(null);
    frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    
    DefaultTableModel model = new DefaultTableModel();
    
    model.addColumn("Tên Người Chơi");
    model.addColumn("Điểm");
    
    PeopleDAO peopleDAO = PeopleDAO.getInstance();
    ArrayList<People> peopleList = peopleDAO.selectAll();
    
    // Thêm dữ liệu vào DefaultTableModel
    for (People people : peopleList) {   	
        Object[] rowData = {people.getTenNguoiChoi(), people.getDiem()};
        model.addRow(rowData);       
    }
    
    // Tạo JTable với mô hình DefaultTableModel
    JTable table = new JTable(model);   
    JScrollPane scrollPane = new JScrollPane(table);
    
    // Thêm JScrollPane vào JFrame
    frame.getContentPane().add(scrollPane, BorderLayout.CENTER);   
    frame.setSize(500, 300);
    frame.setVisible(true);
	
}
}
