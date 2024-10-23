package view;

import controller.JTool;
import model.Admin;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class ManageParking {
    private JPanel manageParkingField;
    private JComboBox operationComboBox;
    private JButton returnToDashboardButton;
    private JButton manageParkingSpot;
    private JTextField fromTextField;
    private JTextField toTextField;

    public ManageParking( Admin admin ) {

        JFrame frame = new JFrame("Manage Parking");
        frame.setContentPane(manageParkingField);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,500);
        frame.setResizable(false);
        frame.setVisible(true);

        operationComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                fromTextField.setText("");
                toTextField.setText("");

                if(operationComboBox.getSelectedIndex()==0){
                    manageParkingSpot.setText("Add Parking Spots");
                }
                else if(operationComboBox.getSelectedIndex()==1){
                    manageParkingSpot.setText("Delete Parking Spots");
                }
            }
        });
        returnToDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashboard( admin );
            }
        });
        manageParkingSpot.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                if(operationComboBox.getSelectedIndex()==0){

                    try {

                        JTool.addParkingSpots( Integer.parseInt(fromTextField.getText()), Integer.parseInt(toTextField.getText()) );
                        JOptionPane.showMessageDialog(frame, "Parking Spot Added");

                    } catch (IOException | ParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Unable to add parking spots");
                        throw new RuntimeException(ex);
                    }

                }
                else if(operationComboBox.getSelectedIndex()==1){

                    try {

                        JTool.deleteParkingSpots( Integer.parseInt(fromTextField.getText()), Integer.parseInt(toTextField.getText()) );
                        JOptionPane.showMessageDialog(frame, "Parking Spot Added");

                    } catch (IOException | ParseException ex) {
                        JOptionPane.showMessageDialog(frame, "Unable to delete parking spots");
                        throw new RuntimeException(ex);
                    }

                }

            }
        });
    }
}
