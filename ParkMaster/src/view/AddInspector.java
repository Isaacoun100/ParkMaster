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
        createInspectorButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        returnToPreviousMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Add Inspector");
        frame.setContentPane(new AddInspector().addInspectorPanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setSize(900,450);
        frame.setResizable(false);
        frame.setVisible(true);
    }

}
