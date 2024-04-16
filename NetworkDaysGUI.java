package hicheel2024.buteelt.NetWorkDays;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

public class NetworkDaysGUI extends JFrame {
    private JTextField startDateField;
    private JTextField endDateField;
    private JTextField holidaysField;
    private JButton calculateButton;
    private JLabel resultLabel;

    public NetworkDaysGUI() {
        setTitle("Network Days Calculator");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new GridLayout(5, 2, 5, 5)); // 5 rows, 2 columns, gaps of 5 pixels

        JLabel startDateLabel = new JLabel("Start Date (yyyy-MM-dd):");
        startDateField = new JTextField();
        add(startDateLabel);
        add(startDateField);

        JLabel endDateLabel = new JLabel("End Date (yyyy-MM-dd):");
        endDateField = new JTextField();
        add(endDateLabel);
        add(endDateField);

        JLabel holidaysLabel = new JLabel("Holidays (comma-separated, yyyy-MM-dd):");
        holidaysField = new JTextField();
        add(holidaysLabel);
        add(holidaysField);

        calculateButton = new JButton("Calculate");
        calculateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                calculateNetworkDays();
            }
        });
        add(calculateButton);

        resultLabel = new JLabel();
        add(resultLabel);

        setVisible(true);
    }

    private void calculateNetworkDays() {
        try {
            LocalDate startDate = LocalDate.parse(startDateField.getText());
            LocalDate endDate = LocalDate.parse(endDateField.getText());
            Set<LocalDate> holidays = parseHolidays(holidaysField.getText());

            long workingDays = DateUtils.networkdays(startDate, endDate, holidays);
            resultLabel.setText("Number of working days: " + workingDays);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private Set<LocalDate> parseHolidays(String holidaysText) {
        Set<LocalDate> holidays = new HashSet<>();
        String[] holidayStrings = holidaysText.split(",");
        for (String holidayString : holidayStrings) {
            holidays.add(LocalDate.parse(holidayString.trim()));
        }
        return holidays;
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new NetworkDaysGUI();
            }
        });
    }
}
