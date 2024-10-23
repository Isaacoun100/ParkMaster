package view;

import controller.JTool;
import model.Customer;
import model.other.PaymentMethod;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ManageCustomer {
    private JPanel manageCustomerPanel;
    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JFormattedTextField phoneNumberTextField;
    private JTextField billingAddressTextField;
    private JTextField emailTextField;
    private JFormattedTextField idTextField;
    private JButton updateInformationButton;
    private JButton returnToDashboardButton;
    private JFormattedTextField cardNumberTextField;
    private JFormattedTextField expDateTextField;
    private JFormattedTextField cvvTextField;

    public ManageCustomer( Customer customer ) {

        JFrame frame = new JFrame("Manage Customer");
        frame.setContentPane(manageCustomerPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(900,450);
        frame.setResizable(false);
        frame.setVisible(true);

        nameTextField.setText(customer.getName());
        lastNameTextField.setText(customer.getLastName());
        phoneNumberTextField.setText(customer.getPhoneNumber());
        billingAddressTextField.setText(customer.getBillingAddress());
        emailTextField.setText(customer.getEmail());

        returnToDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new CustomerDashboard( customer );
            }
        });


        updateInformationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                PaymentMethod paymentMethod = new PaymentMethod(
                        customer.getPaymentMethod().getCardNumber(),
                        customer.getPaymentMethod().getExpiryDate(),
                        customer.getPaymentMethod().getCvv()
                );

                if (!cardNumberTextField.getText().isEmpty() && !expDateTextField.getText().isEmpty() && !cvvTextField.getText().isEmpty()) {
                    paymentMethod.setCardNumber(cardNumberTextField.getText());
                    paymentMethod.setExpiryDate(expDateTextField.getText());
                    paymentMethod.setCvv(cvvTextField.getText());
                }

                try {

                    Customer newCustomer = new Customer(
                            nameTextField.getText(),
                            lastNameTextField.getText(),
                            phoneNumberTextField.getText(),
                            emailTextField.getText(),
                            billingAddressTextField.getText(),
                            customer.getId(),
                            customer.getPIN(),
                            paymentMethod,
                            customer.getVehicles(),
                            customer.getSignUpDate()
                    );
                    JTool.updateCustomer(newCustomer);
                    JOptionPane.showMessageDialog(frame, "Customer successfully updated");
                    frame.dispose();
                    new CustomerDashboard( newCustomer );

                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "Something went wrong");
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);
                }

            }
        });
    }
}
