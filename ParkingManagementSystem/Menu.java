package ParkingManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Menu extends JFrame {
    private ParkingLot parkingLot;
    private JLabel vacancyLabel;
    private JTextField licensePlateField;
    private JButton parkButton;
    private JButton exitButton;

    public Menu() {
        // 初始化停車場
        parkingLot = new ParkingLot(100);
        GenerateParkingLot();

        // 設定主視窗
        setTitle("Parking Lot Management System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Vacancy 標籤
        vacancyLabel = new JLabel("Vacancy: " + parkingLot.GetAvailableSpots(), SwingConstants.CENTER);
        vacancyLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(vacancyLabel, BorderLayout.NORTH);

        // 停車功能區域
        JPanel centerPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        JLabel licensePlateLabel = new JLabel("Enter License Plate:");
        licensePlateField = new JTextField(10); // 設定較小的輸入框
        parkButton = new JButton("Park Car");
        exitButton = new JButton("Exit System");

        centerPanel.add(licensePlateLabel);
        centerPanel.add(licensePlateField);
        centerPanel.add(parkButton);
        add(centerPanel, BorderLayout.CENTER);

        // 下方按鈕區域
        JPanel southPanel = new JPanel();
        southPanel.add(exitButton);
        add(southPanel, BorderLayout.SOUTH);

        // 設定按鈕事件
        parkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ParkCar();
            }
        });

        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // 結束程式
            }
        });
    }

    private void ParkCar() {
        String licensePlate = licensePlateField.getText().trim();
        if (licensePlate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "License plate cannot be empty!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        if (parkingLot.ParkCar(licensePlate)) {
            JOptionPane.showMessageDialog(this, "Car parked successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
            licensePlateField.setText("");
            UpdateVacancyLabel();
        } else {
            JOptionPane.showMessageDialog(this, "Parking lot is full or car already parked.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void UpdateVacancyLabel() {
        vacancyLabel.setText("Vacancy: " + parkingLot.GetAvailableSpots());
    }

    private void GenerateParkingLot() {
        int occupiedSpots = (int) (Math.random() * (parkingLot.GetTotalSpots() + 1));
        for (int i = 0; i < occupiedSpots; i++) {
            String fakePlate = "FAKE" + (i + 1);
            parkingLot.ParkCar(fakePlate);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                Menu gui = new Menu();
                gui.setVisible(true);
            }
        });
    }
}

