package view;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddInspector extends JFrame {

    private JTextField nameFieldText;
    private JTextField lastNameFieldText;
    private JFormattedTextField phoneNumberFieldText;
    private JTextField emailFieldText;
    private JTextField billingAddressFieldText;
    private JFormattedTextField hireDateFieldText;
    private JPasswordField pinFieldText;
    private JFormattedTextField idFieldText;
    private JFormattedTextField terminalTextField;
    private JButton createInspectorButton;
    private JButton returnToPreviousMenuButton;
    private JPanel addInspectorPanel;

    public AddInspector() {

        JFrame frame = new JFrame("Add Inspector");
        frame.setContentPane(addInspectorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,450);
        frame.setResizable(false);
        frame.setVisible(true);

        createInspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(AddInspector.this, "Inspector added successfully");
            }
        });
        returnToPreviousMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashboard();
            }
        });
    }

}
