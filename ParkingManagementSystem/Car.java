package ParkingManagementSystem;

public class Car {
    private String licensePlate;
    private long entryTime;

    public Car(String licensePlate, long entryTime) {
        this.licensePlate = licensePlate;
        this.entryTime = entryTime;
    }

    public String GetLicensePlate() {
        return licensePlate;
    }

    public long GetEntryTime() {
        return entryTime;
    }
}
