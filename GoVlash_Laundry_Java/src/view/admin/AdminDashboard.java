package view.admin;

import model.User;
import view.LoginView;

import javax.swing.*;
import java.awt.*;

public class AdminDashboard extends JFrame {

    public AdminDashboard(User admin) {
    	
    	if (!admin.getRole().equals("Admin")) {
            JOptionPane.showMessageDialog(this, "Access denied!");
            dispose();
            return;
        }
    	
        setTitle("Admin Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JButton serviceBtn = new JButton("Manage Services");
        JButton employeeBtn = new JButton("Manage Employees");
        JButton logoutBtn = new JButton("Logout");

        JPanel panel = new JPanel(new GridLayout(3, 1, 10, 10));
        panel.add(serviceBtn);
        panel.add(employeeBtn);
        panel.add(logoutBtn);

        add(panel);

        serviceBtn.addActionListener(e -> new ManageServicesView().setVisible(true));
        employeeBtn.addActionListener(e -> new ManageEmployeesView().setVisible(true));

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginView().setVisible(true);
        });
    }
}
