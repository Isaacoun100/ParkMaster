package view;

import controller.jsonParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class AddInspector extends JFrame {

    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JFormattedTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField billingAddressTextField;
    private JFormattedTextField hireDateTextField;
    private JPasswordField pinTextField;
    private JFormattedTextField idTextField;
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

                try {
                    jsonParser.addInspector(
                            nameTextField.getText(),
                            lastNameTextField.getText(),
                            phoneNumberTextField.getText(),
                            emailTextField.getText(),
                            billingAddressTextField.getText(),
                            idTextField.getText(),
                            pinTextField.getText(),
                            LocalDate.parse(hireDateTextField.getText()),
                            terminalTextField.getText()
                    );

                    JOptionPane.showMessageDialog(AddInspector.this, "Inspector added successfully");

                } catch (IOException | ParseException ex) {
                    System.out.println(ex.getMessage());
                    JOptionPane.showMessageDialog(frame, "Unable to add inspector, please check the information");
                    throw new RuntimeException(ex);
                }

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
