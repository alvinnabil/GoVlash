package view.admin;

import controller.UserController;
import dao.UserDAO;
import model.User;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Date;
import java.util.ArrayList;

public class ManageEmployeesView extends JFrame {

    private UserDAO userDAO = new UserDAO();
    private UserController controller = new UserController();

    public ManageEmployeesView() {
        setTitle("Manage Employees");
        setSize(700, 450);
        setLocationRelativeTo(null);

        JPanel form = new JPanel(new GridLayout(8, 2, 5, 5));

        JTextField name = new JTextField();
        JTextField email = new JTextField();
        JTextField pass = new JTextField();
        JTextField pass2 = new JTextField();
        JTextField gender = new JTextField();
        JTextField dob = new JTextField(); // yyyy-mm-dd
        JTextField role = new JTextField();

        JButton add = new JButton("Add Employee");

        form.add(new JLabel("Name:")); form.add(name);
        form.add(new JLabel("Email:")); form.add(email);
        form.add(new JLabel("Password:")); form.add(pass);
        form.add(new JLabel("Confirm Password:")); form.add(pass2);
        form.add(new JLabel("Gender:")); form.add(gender);
        form.add(new JLabel("DOB (YYYY-MM-DD):")); form.add(dob);
        form.add(new JLabel("Role:")); form.add(role);
        form.add(add);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Email", "Role"}, 0);
        JTable table = new JTable(model);
        loadEmployees(model);

        add(form, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);

        add.addActionListener(e -> {
            String result = controller.addEmployee(
                    name.getText(), email.getText(), pass.getText(), pass2.getText(),
                    gender.getText(), Date.valueOf(dob.getText()), role.getText()
            );

            JOptionPane.showMessageDialog(this, result);
            if(result.equals("SUCCESS")) {
                loadEmployees(model);
            }
        });
    }

    private void loadEmployees(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<User> list = userDAO.getAllUsers();

        for (User u : list) {
            if(!u.getRole().equals("Customer")) {
                model.addRow(new Object[]{u.getUserId(), u.getName(), u.getEmail(), u.getRole()});
            }
        }
    }
}
