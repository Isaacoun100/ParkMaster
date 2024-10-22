package view;

import model.Admin;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ManageAdmin {
    private JPanel manageAdminPanel;
    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JFormattedTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField billingAddressTextField;
    private JFormattedTextField idTextField;
    private JTextField hireDateTextField;
    private JButton createAdmin;
    private JButton returnToPreviousMenuButton;

    public ManageAdmin( Admin admin ) {

        JFrame frame = new JFrame("Update Admin");
        frame.setContentPane(manageAdminPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setVisible(true);

        //Update information from the variable
        nameTextField.setText( admin.getName() );
        lastNameTextField.setText( admin.getLastName() );
        phoneNumberTextField.setText( admin.getPhoneNumber() );
        emailTextField.setText( admin.getEmail() );
        billingAddressTextField.setText( admin.getBillingAddress() );
        idTextField.setText( admin.getId() );
        hireDateTextField.setText(admin.getHireDate().toString());


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

            }
        });
    }
}
