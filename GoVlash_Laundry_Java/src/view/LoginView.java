package view;

import controller.AuthController;
import model.User;

import javax.swing.*;
import java.awt.*;

public class LoginView extends JFrame {

    private AuthController authController = new AuthController();

    public LoginView() {
    	
   

        setTitle("GoVlash Laundry - Login");
        setSize(350, 250);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(4, 2, 10, 10));

        JLabel emailLbl = new JLabel("Email:");
        JTextField emailField = new JTextField();

        JLabel passLbl = new JLabel("Password:");
        JPasswordField passField = new JPasswordField();

        JButton loginBtn = new JButton("Login");
        JButton registerBtn = new JButton("Register");

        panel.add(emailLbl);
        panel.add(emailField);
        panel.add(passLbl);
        panel.add(passField);
        panel.add(loginBtn);
        panel.add(registerBtn);

        add(panel);

        // Action Login
        loginBtn.addActionListener(e -> {
            String email = emailField.getText();
            String pass = String.valueOf(passField.getPassword());

            User u = authController.login(email, pass);

            if(u == null) {
            	JOptionPane.showMessageDialog(this, "Invalid credentials!", "Login Failed", JOptionPane.ERROR_MESSAGE);
                return;
            }

            JOptionPane.showMessageDialog(this, "Login successful!", "Success", JOptionPane.INFORMATION_MESSAGE);

            dispose();

            switch (u.getRole()) {
                case "Admin":
                    new view.admin.AdminDashboard(u).setVisible(true);
                    break;
                case "Customer":
                    new view.customer.CustomerDashboard(u).setVisible(true);
                    break;
                case "Receptionist":
                    new view.receptionist.ReceptionistDashboard(u).setVisible(true);
                    break;
                case "Laundry Staff":
                    new view.staff.StaffDashboard(u).setVisible(true);
                    break;
            }
        });

        // Go to register
        registerBtn.addActionListener(e -> {
            dispose();
            new RegisterView().setVisible(true);
        });
    }
}
