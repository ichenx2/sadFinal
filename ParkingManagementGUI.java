import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.Random;

// ParkingLot class
class ParkingLot {
    private int totalSlots;
    private int availableSlots;

    public ParkingLot(int totalSlots) {
        this.totalSlots = totalSlots;
        updateSlots(); // 使用亂數初始化剩餘車位
    }

    public void updateSlots() {
        Random random = new Random();
        this.availableSlots = random.nextInt(totalSlots + 1); // 亂數生成剩餘車位
    }

    public int getAvailableSlots() {
        return availableSlots;
    }

    public void decrementAvailableSlots() {
        if (availableSlots > 0) {
            availableSlots--;
        }
    }

    public int getTotalSlots() {
        return totalSlots;
    }
}

// Car class
class Car {
    private String licensePlate;
    private Date entryTime;
    private Date exitTime;

    public Car(String licensePlate) {
        this.licensePlate = licensePlate;
        this.entryTime = new Date(); // 記錄進入時間
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Date getEntryTime() {
        return entryTime;
    }

    public Date getExitTime() {
        return exitTime;
    }

    public void setExitTime(Date exitTime) {
        this.exitTime = exitTime;
    }
}

// Main GUI Class
public class ParkingManagementGUI {
    private JFrame frame;
    private JLabel titleLabel;
    private JLabel resultLabel;
    private JButton enterButton;
    private ParkingLot parkingLot;
    private ArrayList<Car> parkedCars; // 存放進入停車場的車輛
    private SimpleDateFormat dateFormat;

    public ParkingManagementGUI() {
        // 初始化日期格式
        dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

        // 初始化停車場，假設總車位為100
        parkingLot = new ParkingLot(100);
        parkedCars = new ArrayList<>();

        // 建立主框架
        frame = new JFrame("停車場管理系統");
        frame.setSize(400, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // 標題
        titleLabel = new JLabel("歡迎使用停車場管理系統", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        frame.add(titleLabel, BorderLayout.NORTH);

        // 顯示結果的標籤（初始化顯示剩餘車位）
        resultLabel = new JLabel("剩餘車位：" + parkingLot.getAvailableSlots() + " / " + parkingLot.getTotalSlots(), SwingConstants.CENTER);
        resultLabel.setFont(new Font("Arial", Font.PLAIN, 16));
        frame.add(resultLabel, BorderLayout.CENTER);

        // 按鈕
        enterButton = new JButton("進入停車場");
        enterButton.setFont(new Font("Arial", Font.BOLD, 16));
        enterButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int availableSlots = parkingLot.getAvailableSlots();

                if (availableSlots > 0) {
                    // 剩餘車位不為0，要求輸入車牌號碼
                    String inputPlate = null;
                    boolean isValid = false;
                    while (!isValid) {
                        inputPlate = JOptionPane.showInputDialog(frame, "請輸入車牌後四碼（四位數字）：");
                        if (inputPlate == null) {
                            // 如果按下取消，退出迴圈
                            resultLabel.setText("輸入已取消");
                            return;
                        } else if (inputPlate.matches("\\d{4}")) {
                            isValid = true; // 格式正確，退出迴圈
                        } else {
                            JOptionPane.showMessageDialog(frame, "格式錯誤！請輸入四位數字的車牌後四碼。", "錯誤", JOptionPane.ERROR_MESSAGE);
                        }
                    }

                    // 創建車輛並加入停車場
                    Car car = new Car(inputPlate);
                    parkedCars.add(car);
                    parkingLot.decrementAvailableSlots(); // 減少一個車位

                    String entryTimeStr = dateFormat.format(car.getEntryTime());
                    String message = "車牌號碼 " + car.getLicensePlate() + " 歡迎進入！\n進入時間：" + entryTimeStr;

                    JOptionPane.showMessageDialog(frame, message, "成功", JOptionPane.INFORMATION_MESSAGE);

                    // 更新主畫面顯示
                    resultLabel.setText("剩餘車位：" + parkingLot.getAvailableSlots() + " / " + parkingLot.getTotalSlots());
                } else {
                    // 無剩餘車位
                    resultLabel.setText("目前無車位！");
                }
            }
        });
        frame.add(enterButton, BorderLayout.SOUTH);

        // 顯示框架
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ParkingManagementGUI());
    }
}
