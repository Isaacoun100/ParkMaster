package view;

import com.toedter.calendar.JDateChooser;
import controller.JTool;
import model.Admin;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class ManageAdmin {
    private JPanel manageAdminPanel;
    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JFormattedTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField billingAddressTextField;
    private JButton updateAdmin;
    private JButton returnToPreviousMenuButton;
    private JDateChooser dateChooser;

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
        dateChooser.setDate( Date.from(admin.getHireDate().atStartOfDay(ZoneId.systemDefault()).toInstant()) );

        returnToPreviousMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashboard( admin );
            }
        });

        updateAdmin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Admin newAdmin = new Admin(
                        nameTextField.getText(),
                        lastNameTextField.getText(),
                        phoneNumberTextField.getText(),
                        emailTextField.getText(),
                        billingAddressTextField.getText(),
                        admin.getId(),
                        admin.getPIN(),
                        LocalDate.parse( dateChooser.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate().toString() )
                );

                try {

                    JTool.updateAdmin( newAdmin );

                    frame.dispose();
                    new AdminDashboard( newAdmin );

                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Something went wrong", "Error", JOptionPane.ERROR_MESSAGE);
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    private void createUIComponents() {
        // Initialize the JDateChooser for the custom component
        dateChooser = new JDateChooser();
        Date currentDate = new Date();

        dateChooser.setDate(currentDate);
        dateChooser.setDateFormatString("yyyy-MM-dd");
    }
}
