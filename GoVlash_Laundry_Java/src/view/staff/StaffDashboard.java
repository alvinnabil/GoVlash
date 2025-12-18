package view.staff;

import model.User;
import javax.swing.*;
import java.awt.*;

public class StaffDashboard extends JFrame {

    public StaffDashboard(User s) {
    	
    	if(!s.getRole().equals("Laundry Staff")) {
    	    JOptionPane.showMessageDialog(this, "Access denied!");
    	    dispose();
    	    return;
    	}

        setTitle("Staff Dashboard");
        setSize(400, 300);
        setLocationRelativeTo(null);

        JButton viewAssignedBtn = new JButton("View Assigned Work");
        JButton logoutBtn = new JButton("Logout");

        JPanel panel = new JPanel(new GridLayout(2, 1, 10, 10));
        panel.add(viewAssignedBtn);
        panel.add(logoutBtn);

        add(panel);

        viewAssignedBtn.addActionListener(e -> new StaffAssignedView(s).setVisible(true));

        logoutBtn.addActionListener(e -> {
            dispose();
            new view.LoginView().setVisible(true);
        });
    }
}
