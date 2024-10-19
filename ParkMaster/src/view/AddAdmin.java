package view;

import javax.swing.*;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;

public class AddAdmin extends JFrame {
    private JPanel addAdminPanel;
    private JTextField nameFieldText;
    private JTextField lastNameFieldText;
    private JFormattedTextField phoneNumberFieldText;
    private JTextField emailFieldText;
    private JTextField billingAddressFieldText;
    private JFormattedTextField hireDateFieldText;
    private JPasswordField pinFieldText;
    private JFormattedTextField idFieldText;
    private JButton createAdmin;
    private JButton returnToPreviousMenuButton;

    public AddAdmin() {

        JFrame frame = new JFrame("Add Admin");
        frame.setContentPane(addAdminPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,450);
        frame.setResizable(false);
        frame.setVisible(true);

        returnToPreviousMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashboard();
            }
        });
        createAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(addAdminPanel, "Admin added successfully");
            }
        });
    }

}
