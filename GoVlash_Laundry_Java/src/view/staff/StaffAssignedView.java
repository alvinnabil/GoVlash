package view.staff;

import controller.TransactionController;
import dao.TransactionDAO;
import model.Transaction;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StaffAssignedView extends JFrame {

    private TransactionDAO transDAO = new TransactionDAO();
    private TransactionController controller = new TransactionController();

    public StaffAssignedView(User staff) {
        setTitle("Assigned Work");
        setSize(600, 400);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Service", "Weight", "Notes"}, 0);

        JTable table = new JTable(model);
        loadAssigned(model, staff.getUserId());

        JButton finishBtn = new JButton("Mark as Finished");

        JPanel bottom = new JPanel();
        bottom.add(finishBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        finishBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row == -1) return;

            int id = (int) table.getValueAt(row, 0);
            controller.finishTransaction(id);

            JOptionPane.showMessageDialog(this, "Order Marked as Finished!");
            loadAssigned(model, staff.getUserId());
        });
    }

    private void loadAssigned(DefaultTableModel model, int staffId) {
        model.setRowCount(0);
        ArrayList<Transaction> list = transDAO.getAssignedToStaff(staffId);

        for(Transaction t : list) {
            model.addRow(new Object[]{
                    t.getTransactionId(),
                    t.getServiceId(),
                    t.getTotalWeight(),
                    t.getNotes()
            });
        }
    }
}
