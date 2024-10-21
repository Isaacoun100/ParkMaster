package view;

import controller.jsonParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalDate;

public class AddAdmin extends JFrame {
    private JPanel addAdminPanel;
    private JTextField nameTextField;
    private JTextField lastNameTextField;
    private JFormattedTextField phoneNumberTextField;
    private JTextField emailTextField;
    private JTextField billingAddressTextField;
    private JPasswordField pinTextField;
    private JFormattedTextField idTextField;
    private JButton createAdmin;
    private JButton returnToPreviousMenuButton;
    private JTextField dayTextField;
    private JTextField monthTextField;
    private JTextField yearTextField;

    public AddAdmin() {

        JFrame frame = new JFrame("Add Admin");
        frame.setContentPane(addAdminPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1000, 600);
        frame.setResizable(false);
        frame.setVisible(true);

        // Set document filters
        setDocumentFilters();

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

                try {
                    // Create LocalDate using input from day, month, and year text fields
                    LocalDate hireDate = LocalDate.of(
                            Integer.parseInt(yearTextField.getText()),
                            Integer.parseInt(monthTextField.getText()),
                            Integer.parseInt(dayTextField.getText())
                    );

                    jsonParser.addAdmin(
                            nameTextField.getText(),
                            lastNameTextField.getText(),
                            phoneNumberTextField.getText(),
                            emailTextField.getText(),
                            billingAddressTextField.getText(),
                            idTextField.getText(),
                            new String(pinTextField.getPassword()),
                            hireDate
                    );
                    JOptionPane.showMessageDialog(addAdminPanel, "Admin added successfully");
                } catch (IOException | ParseException | NumberFormatException ex) {
                    JOptionPane.showMessageDialog(addAdminPanel, "Unable to add admin, please check the information");
                    System.out.println(ex.getMessage());
                    throw new RuntimeException(ex);
                }

            }
        });
    }

    private void setDocumentFilters() {
        ((AbstractDocument) nameTextField.getDocument()).setDocumentFilter(new LengthFilter(2, 20));
        ((AbstractDocument) lastNameTextField.getDocument()).setDocumentFilter(new LengthFilter(1, 40));
        ((AbstractDocument) phoneNumberTextField.getDocument()).setDocumentFilter(new NumericFilter(8, 8));
        ((AbstractDocument) emailTextField.getDocument()).setDocumentFilter(new LengthFilter(11, Integer.MAX_VALUE));
        ((AbstractDocument) billingAddressTextField.getDocument()).setDocumentFilter(new LengthFilter(5, 60));
        ((AbstractDocument) idTextField.getDocument()).setDocumentFilter(new LengthFilter(2, 25));
        ((AbstractDocument) pinTextField.getDocument()).setDocumentFilter(new NumericFilter(4, 4));
        ((AbstractDocument) yearTextField.getDocument()).setDocumentFilter(new NumericFilter(1, 4));
        ((AbstractDocument) monthTextField.getDocument()).setDocumentFilter(new NumericFilter(2, 2));
        ((AbstractDocument) dayTextField.getDocument()).setDocumentFilter(new NumericFilter(2, 2));
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
