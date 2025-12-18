package view.customer;

import model.User;
import view.LoginView;

import javax.swing.*;
import java.awt.*;

public class CustomerDashboard extends JFrame {

    public CustomerDashboard(User customer) {
    	
    	if(!customer.getRole().equals("Customer")) {
    	    JOptionPane.showMessageDialog(this, "Access denied!");
    	    dispose();
    	    return;
    	}

        setTitle("Customer Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JButton createTransBtn = new JButton("Create Transaction");
        JButton viewNotifBtn = new JButton("Notifications");
        JButton logoutBtn = new JButton("Logout");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(createTransBtn);
        panel.add(viewNotifBtn);
        panel.add(logoutBtn);

        add(panel);

        createTransBtn.addActionListener(e -> new CreateTransactionView(customer).setVisible(true));
        viewNotifBtn.addActionListener(e -> new NotificationListView(customer).setVisible(true));

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginView().setVisible(true);
        });
    }
}
