package view.customer;

import controller.NotificationController;
import dao.NotificationDAO;
import model.Notification;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class NotificationListView extends JFrame {

    private NotificationDAO dao = new NotificationDAO();
    private NotificationController controller = new NotificationController();

    public NotificationListView(User customer) {
        setTitle("Notifications");
        setSize(500, 350);
        setLocationRelativeTo(null);

        DefaultTableModel model = new DefaultTableModel(
                new Object[]{"ID", "Message", "Read"}, 0);

        JTable table = new JTable(model);
        loadNotif(model, customer.getUserId());

        JButton readBtn = new JButton("Mark as Read");
        JButton deleteBtn = new JButton("Delete");

        JPanel bottom = new JPanel();
        bottom.add(readBtn);
        bottom.add(deleteBtn);

        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        readBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row == -1) return;

            int id = (int) table.getValueAt(row, 0);
            controller.markRead(id);
            loadNotif(model, customer.getUserId());
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row == -1) return;

            int id = (int) table.getValueAt(row, 0);
            controller.deleteNotification(id);
            loadNotif(model, customer.getUserId());
        });
    }

    private void loadNotif(DefaultTableModel model, int userId) {
        model.setRowCount(0);
        ArrayList<Notification> list = dao.getNotificationsByUser(userId);

        for(Notification n : list) {
            model.addRow(new Object[]{
                    n.getNotifId(),
                    n.getMessage(),
                    n.isRead() ? "Yes" : "No"
            });
        }
    }
}
