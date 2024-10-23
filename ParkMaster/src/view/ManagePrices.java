package view;

import controller.JTool;
import model.Admin;
import model.other.Settings;
import org.json.simple.parser.ParseException;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import static java.lang.String.valueOf;

public class ManagePrices {
    private JTextField fromTextField;
    private JTextField toTextField;
    private JTextField parkingPriceTextField;
    private JTextField minimumTimeTextField;
    private JButton saveChangesButton;
    private JButton returnToDashboardButton;
    private JPanel manageParkingPanel;
    private JTextField ticketPriceTextField;

    public ManagePrices(Admin admin ) {

        JFrame frame = new JFrame("Manage Prices");
        frame.setContentPane(manageParkingPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(750,500);
        frame.setResizable(false);
        frame.setVisible(true);

        try {
            Settings settings = JTool.getSettings();
            fromTextField.setText( valueOf(settings.getFromTime()) );
            toTextField.setText( valueOf(settings.getToTime()) );
            parkingPriceTextField.setText( valueOf(settings.getPrice()) );
            minimumTimeTextField.setText( valueOf(settings.getMinimumTime()) );
            ticketPriceTextField.setText( valueOf(settings.getTicketPrice()) );
        } 
        catch (IOException | ParseException e) {
            throw new RuntimeException(e);
        }

        returnToDashboardButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                new AdminDashboard( admin );
            }
        });

        saveChangesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

                try{
                    Settings newSettings = new Settings(
                            LocalTime.parse( fromTextField.getText(), formatter ),
                            LocalTime.parse( toTextField.getText(), formatter ),
                            Integer.parseInt( parkingPriceTextField.getText() ),
                            Integer.parseInt( minimumTimeTextField.getText() ),
                            Integer.parseInt( ticketPriceTextField.getText() )
                    );

                    JTool.setSettings(newSettings);
                    JOptionPane.showMessageDialog(frame, "Preferences saved successfully");
                    frame.dispose();
                    new AdminDashboard( admin );

                }
                catch (NumberFormatException ex){
                    JOptionPane.showMessageDialog(frame, "There is an issue with the information");
                    throw new RuntimeException(ex);
                } catch (IOException | ParseException ex) {
                    JOptionPane.showMessageDialog(frame, "There is an issue with the file system, please try again later");
                    throw new RuntimeException(ex);
                }


            }
        });
    }
    
}
