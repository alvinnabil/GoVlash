package view.customer;

import controller.TransactionController;
import dao.ServiceDAO;
import model.Service;
import model.User;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class CreateTransactionView extends JFrame {

    private TransactionController controller = new TransactionController();
    private ServiceDAO serviceDAO = new ServiceDAO();

    public CreateTransactionView(User customer) {
        setTitle("Create Transaction");
        setSize(400, 350);
        setLocationRelativeTo(null);

        JPanel panel = new JPanel(new GridLayout(5, 2, 5, 5));

        ArrayList<Service> services = serviceDAO.getAllServices();
        String[] serviceNames = new String[services.size()];
        int idx = 0;
        for(Service s : services) {
            serviceNames[idx++] = s.getServiceId() + " - " + s.getName();
        }

        JComboBox<String> serviceBox = new JComboBox<>(serviceNames);
        JTextField weight = new JTextField();
        JTextField notes = new JTextField();

        JButton submit = new JButton("Submit");

        panel.add(new JLabel("Service:"));
        panel.add(serviceBox);

        panel.add(new JLabel("Weight (kg):"));
        panel.add(weight);

        panel.add(new JLabel("Notes:"));
        panel.add(notes);

        panel.add(submit);

        add(panel);

        submit.addActionListener(e -> {
            int serviceId = Integer.parseInt(serviceBox.getSelectedItem().toString().split(" - ")[0]);

            String result = controller.createTransaction(
                    serviceId,
                    customer.getUserId(),
                    weight.getText(),
                    notes.getText()
            );

            JOptionPane.showMessageDialog(this, result);
        });
    }
}
