package view;

import controller.AuthController;

import javax.swing.*;
import java.awt.*;
import java.sql.Date;

public class RegisterView extends JFrame {

    private AuthController authController = new AuthController();

    public RegisterView() {
        setTitle("Customer Registration");
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(8, 2, 10, 10));

        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JPasswordField pass = new JPasswordField();
        JPasswordField confirmPass = new JPasswordField();
        JTextField gender = new JTextField();
        JTextField dob = new JTextField(); // Format: YYYY-MM-DD

        JButton regBtn = new JButton("Register");

        panel.add(new JLabel("Name:"));
        panel.add(name);

        panel.add(new JLabel("Email:"));
        panel.add(email);

        panel.add(new JLabel("Password:"));
        panel.add(pass);

        panel.add(new JLabel("Confirm Password:"));
        panel.add(confirmPass);

        panel.add(new JLabel("Gender (Male/Female):"));
        panel.add(gender);

        panel.add(new JLabel("DOB (YYYY-MM-DD):"));
        panel.add(dob);

        panel.add(regBtn);

        add(panel);

        regBtn.addActionListener(e -> {
            String response = authController.registerCustomer(
                    name.getText(),
                    email.getText(),
                    String.valueOf(pass.getPassword()),
                    String.valueOf(confirmPass.getPassword()),
                    gender.getText(),
                    Date.valueOf(dob.getText())
            );

            if(response.equals("SUCCESS")) {
                JOptionPane.showMessageDialog(this, "Registration Success!");
                dispose();
                new LoginView().setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, response);
            }
        });
    }
}
