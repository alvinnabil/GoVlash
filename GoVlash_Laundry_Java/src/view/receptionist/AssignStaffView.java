package view.receptionist;

import controller.TransactionController;
import dao.TransactionDAO;
import dao.UserDAO;
import model.Transaction;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class AssignStaffView extends JFrame {

    private TransactionDAO transDAO = new TransactionDAO();
    private UserDAO userDAO = new UserDAO();
    private TransactionController controller = new TransactionController();

    public AssignStaffView(User receptionist) {
        setTitle("Assign Laundry Staff");
        setSize(600, 400);
        setLocationRelativeTo(null);

        // Load pending transactions
        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Customer", "Weight", "Notes"}, 0);

        JTable table = new JTable(model);
        loadPending(model);

        // Load staff list
        ArrayList<User> users = userDAO.getAllUsers();
        ArrayList<User> staff = new ArrayList<>();

        for(User u : users) {
            if(u.getRole().equals("Laundry Staff")) staff.add(u);
        }

        String[] staffList = new String[staff.size()];
        int i = 0;
        for(User s : staff) {
            staffList[i++] = s.getUserId() + " - " + s.getName();
        }

        JComboBox<String> staffBox = new JComboBox<>(staffList);
        JButton assignBtn = new JButton("Assign");

        JPanel bottom = new JPanel();
        bottom.add(new JLabel("Select Staff:"));
        bottom.add(staffBox);
        bottom.add(assignBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        assignBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row == -1) {
                JOptionPane.showMessageDialog(this, "Select a transaction!");
                return;
            }

            int transId = (int) table.getValueAt(row, 0);
            int staffId = Integer.parseInt(staffBox.getSelectedItem().toString().split(" - ")[0]);

            controller.assignStaff(transId, receptionist.getUserId(), staffId);

            JOptionPane.showMessageDialog(this, "Assigned!");
            loadPending(model);
        });
    }

    private void loadPending(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<Transaction> list = transDAO.getPendingTransactions();

        for(Transaction t : list) {
            model.addRow(new Object[]{
                    t.getTransactionId(),
                    t.getCustomerId(),
                    t.getTotalWeight(),
                    t.getNotes()
            });
        }
    }
}
