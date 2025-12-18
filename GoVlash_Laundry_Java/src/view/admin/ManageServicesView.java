package view.admin;

import controller.ServiceController;
import dao.ServiceDAO;
import model.Service;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class ManageServicesView extends JFrame {

    private ServiceDAO serviceDAO = new ServiceDAO();
    private ServiceController controller = new ServiceController();

    public ManageServicesView() {
        setTitle("Manage Services");
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel formPanel = new JPanel(new GridLayout(5, 2, 5, 5));

        JTextField name = new JTextField();
        JTextField desc = new JTextField();
        JTextField price = new JTextField();
        JTextField duration = new JTextField();

        JButton addBtn = new JButton("Add Service");

        formPanel.add(new JLabel("Name:"));
        formPanel.add(name);
        formPanel.add(new JLabel("Description:"));
        formPanel.add(desc);
        formPanel.add(new JLabel("Price:"));
        formPanel.add(price);
        formPanel.add(new JLabel("Duration Days:"));
        formPanel.add(duration);
        formPanel.add(addBtn);

        DefaultTableModel model = new DefaultTableModel(new Object[]{"ID", "Name", "Price", "Duration"}, 0);
        JTable table = new JTable(model);

        loadServices(model);

        JButton deleteBtn = new JButton("Delete Selected");
        JPanel bottom = new JPanel();
        bottom.add(deleteBtn);

        add(formPanel, BorderLayout.NORTH);
        add(new JScrollPane(table), BorderLayout.CENTER);
        add(bottom, BorderLayout.SOUTH);

        // Add service
        addBtn.addActionListener(e -> {
            String res = controller.addService(
                    name.getText(),
                    desc.getText(),
                    price.getText(),
                    duration.getText()
            );

            JOptionPane.showMessageDialog(this, res);

            if(res.equals("SUCCESS")) {
            	name.setText("");
                desc.setText("");
                price.setText("");
                duration.setText("");
                loadServices(model);
            }
        });

        // Delete service
        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if(row == -1) {
                JOptionPane.showMessageDialog(this, "Select service first!");
                return;
            }

            int id = (int) table.getValueAt(row, 0);
            serviceDAO.deleteService(id);
            loadServices(model);
        });
    }

    private void loadServices(DefaultTableModel model) {
        model.setRowCount(0);
        ArrayList<Service> list = serviceDAO.getAllServices();
        for(Service s : list) {
            model.addRow(new Object[]{s.getServiceId(), s.getName(), s.getPrice(), s.getDurationDays()});
        }
    }
}
