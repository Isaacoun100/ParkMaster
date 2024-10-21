package view;

import controller.jsonParser;
import model.other.Vehicle;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class AddCustomer extends JFrame {
    private JPanel addUserPanel;
    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JFormattedTextField phoneNumberTextField;
    private JTextField billingAddressTextField;
    private JTextField emailTextField;
    private JFormattedTextField idTextField;
    private JButton createCustomer;
    private JButton returnToPreviousMenuButton;
    private JFormattedTextField cardNumberTextField;
    private JFormattedTextField expDateTextField;
    private JFormattedTextField cvvTextField;
    private JTextField registrationPlateTextField;
    private JTextField brandTextField;
    private JTextField modelTextField;
    private JPasswordField pinTextField;
    private JButton addANewVehicleButton;

    public AddCustomer() {

        JFrame frame = new JFrame("Add User");
        frame.setContentPane(addUserPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setResizable(false);
        frame.setVisible(true);

        ArrayList<Vehicle> vehicles = new ArrayList<>();

        // Set document filters
        setDocumentFilters();

        createCustomer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                System.out.println("I'm about to check");
                if (!vehicles.isEmpty() || addVehicleToList(frame, vehicles)) {
                    System.out.println("I checked correctly");

                    try {
                        jsonParser.addCustomer(
                                nameTextField.getText(),
                                lastNameTextField.getText(),
                                phoneNumberTextField.getText(),
                                emailTextField.getText(),
                                billingAddressTextField.getText(),
                                idTextField.getText(),
                                new String(pinTextField.getPassword()),
                                cardNumberTextField.getText(),
                                expDateTextField.getText(),
                                cvvTextField.getText(),
                                vehicles
                        );
                        System.out.println("Account created successfully");
                        frame.dispose();
                        new HomeCustomer();

                    } catch (IOException | ParseException ex) {
                        System.out.println(ex.getMessage());
                        throw new RuntimeException(ex);
                    }

                } else {
                    JOptionPane.showMessageDialog(frame, "Something went wrong");
                }
            }
        });

        returnToPreviousMenuButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new HomeCustomer();
            }
        });

        addANewVehicleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (addVehicleToList(frame, vehicles)) {
                    JOptionPane.showMessageDialog(frame, "You have successfully added a new vehicle");
                    registrationPlateTextField.setText("");
                    brandTextField.setText("");
                    modelTextField.setText("");
                } else {
                    JOptionPane.showMessageDialog(frame, "Something went wrong");
                }
            }
        });
    }

    private boolean addVehicleToList(JFrame frame, ArrayList<Vehicle> vehicles) {

        if (registrationPlateTextField.getText().isEmpty() &&
                brandTextField.getText().isEmpty() &&
                modelTextField.getText().isEmpty()) {
            return false;
        } else {
            vehicles.add(new Vehicle(
                    registrationPlateTextField.getText(),
                    brandTextField.getText(),
                    modelTextField.getText()
            ));
            return true;
        }
    }

    private void setDocumentFilters() {
        ((AbstractDocument) nameTextField.getDocument()).setDocumentFilter(new LengthFilter(2, 20));
        ((AbstractDocument) lastNameTextField.getDocument()).setDocumentFilter(new LengthFilter(1, 40));
        ((AbstractDocument) phoneNumberTextField.getDocument()).setDocumentFilter(new NumericFilter(8, 8));
        ((AbstractDocument) emailTextField.getDocument()).setDocumentFilter(new LengthFilter(11, Integer.MAX_VALUE));
        ((AbstractDocument) billingAddressTextField.getDocument()).setDocumentFilter(new LengthFilter(5, 60));
        ((AbstractDocument) idTextField.getDocument()).setDocumentFilter(new LengthFilter(2, 25));
        ((AbstractDocument) pinTextField.getDocument()).setDocumentFilter(new NumericFilter(4, 4));
        ((AbstractDocument) cardNumberTextField.getDocument()).setDocumentFilter(new NumericFilter(16, 16));
        ((AbstractDocument) expDateTextField.getDocument()).setDocumentFilter(new LengthFilter(5, 5));
        ((AbstractDocument) cvvTextField.getDocument()).setDocumentFilter(new NumericFilter(3, 3));
        ((AbstractDocument) registrationPlateTextField.getDocument()).setDocumentFilter(new LengthFilter(1, 6));
        ((AbstractDocument) brandTextField.getDocument()).setDocumentFilter(new LengthFilter(1, 15));
        ((AbstractDocument) modelTextField.getDocument()).setDocumentFilter(new LengthFilter(1, 15));
    }

    // Document filter for numeric input only with length restriction
    private static class NumericFilter extends DocumentFilter {
        private final int minLength;
        private final int maxLength;

        public NumericFilter(int minLength, int maxLength) {
            this.minLength = minLength;
            this.maxLength = maxLength;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && string.matches("\\d*") && (fb.getDocument().getLength() + string.length()) <= maxLength) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null && text.matches("\\d*") && (fb.getDocument().getLength() - length + text.length()) <= maxLength) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }

    // Document filter for length restriction only
    private static class LengthFilter extends DocumentFilter {
        private final int minLength;
        private final int maxLength;

        public LengthFilter(int minLength, int maxLength) {
            this.minLength = minLength;
            this.maxLength = maxLength;
        }

        @Override
        public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr) throws BadLocationException {
            if (string != null && (fb.getDocument().getLength() + string.length()) <= maxLength) {
                super.insertString(fb, offset, string, attr);
            }
        }

        @Override
        public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs) throws BadLocationException {
            if (text != null && (fb.getDocument().getLength() - length + text.length()) <= maxLength) {
                super.replace(fb, offset, length, text, attrs);
            }
        }
    }
}
