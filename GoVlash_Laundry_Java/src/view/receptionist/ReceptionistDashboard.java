package view.receptionist;

import model.User;
import view.LoginView;

import javax.swing.*;
import java.awt.*;

public class ReceptionistDashboard extends JFrame {

    public ReceptionistDashboard(User r) {
    	
    	if(!r.getRole().equals("Receptionist")) {
    	    JOptionPane.showMessageDialog(this, "Access denied!");
    	    dispose();
    	    return;
    	}

        setTitle("Receptionist Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JButton assignBtn = new JButton("Assign Staff to Transaction");
        JButton logoutBtn = new JButton("Logout");

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(assignBtn);
        panel.add(logoutBtn);

        add(panel);

        assignBtn.addActionListener(e -> new AssignStaffView(r).setVisible(true));

        logoutBtn.addActionListener(e -> {
            dispose();
            new LoginView().setVisible(true);
        });
    }
}
