package ParkingManagementSystem;

public class FeeCalculator {
    public void calculateFee(Car car) {
        long entryTime = car.GetEntryTime();
        long currentTime = System.currentTimeMillis();
        long durationInMinutes = (currentTime - entryTime) / (1000 * 60);

        long chargeableHours = (long) Math.ceil(durationInMinutes / 60.0);
        if (chargeableHours == 1) {
            System.out.println("Your car stayed for " + durationInMinutes + " minutes. It's free of charge.");
        } else {
            int fee = (int) (chargeableHours - 1) * 10; // �C�p�ɦ��O 10 ��
            System.out.println("Your car stayed for " + durationInMinutes + " minutes.");
            System.out.println("Fee: $" + fee);
        }
    }
}//by gpt
