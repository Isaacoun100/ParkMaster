package view;

import com.toedter.calendar.JDateChooser;
import controller.JTool;
import model.Inspector;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ManageInspector {
    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JFormattedTextField phoneNumberTextField;
    private JPanel manageInspectorPanel;
    private JTextField billingAddressTextField;
    private JTextField emailTextField;
    private JDateChooser dateChooser;
    private JButton updateInspector;
    private JButton returnToPreviousMenuButton;

    public ManageInspector( Inspector inspector ) {

        JFrame frame = new JFrame("Manage Inspector");
        frame.setContentPane(manageInspectorPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,450);
        frame.setResizable(false);
        frame.setVisible(true);

        nameTextField.setText(inspector.getName());
        lastNameTextField.setText(inspector.getLastName());
        phoneNumberTextField.setText(inspector.getPhoneNumber());
        billingAddressTextField.setText(inspector.getBillingAddress());
        emailTextField.setText(inspector.getEmail());

        returnToPreviousMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new InspectorDashboard( inspector );
            }
        });

        updateInspector.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Inspector newInspector = new Inspector(
                        nameTextField.getText(),
                        lastNameTextField.getText(),
                        phoneNumberTextField.getText(),
                        emailTextField.getText(),
                        billingAddressTextField.getText(),
                        inspector.getId(),
                        inspector.getPIN(),
                        inspector.getHireDate(),
                        inspector.getTerminal()
                );

                try {
                    JTool.updateInspector(newInspector);
                    JOptionPane.showMessageDialog(frame, "Inspector Updated");
                    frame.dispose();
                    new InspectorDashboard( newInspector );

                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "There an was error");
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);
                }

            }
        });
    }

}
