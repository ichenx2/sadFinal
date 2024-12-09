package ParkingManagementSystem;

import java.util.HashMap;

public class ParkingLot {
    private int totalSpots;
    private HashMap<Integer, Car> spots;
    //private FeeCalculator feeCalculator;

    public ParkingLot(int totalSpots) {
        this.totalSpots = totalSpots;
        this.spots = new HashMap<>();
        //this.feeCalculator = new FeeCalculator();
    }

    public boolean ParkCar(String licensePlate) {
        if (spots.size() >= totalSpots || IsCarAlreadyParked(licensePlate)) {
            return false; 
        }

        int spot = FindAvailableSpot();
        if (spot == -1) {
            return false; 
        }

        long entryTime = System.currentTimeMillis();
        spots.put(spot, new Car(licensePlate, entryTime));
        return true;
    }

    public boolean ExitCar(String licensePlate) {
        for (int spot : spots.keySet()) {
            Car car = spots.get(spot);
            if (car.GetLicensePlate().equals(licensePlate)) {
                spots.remove(spot);
                return true; 
            }
        }
        return false;
    }

    public int GetAvailableSpots() {
        return totalSpots - spots.size();
    }

    public int GetTotalSpots() {
        return totalSpots;
    }

    private int FindAvailableSpot() {
        for (int i = 0; i < totalSpots; i++) {
            if (!spots.containsKey(i)) {
                return i; 
            }
        }
        return -1; 
    }

    private boolean IsCarAlreadyParked(String licensePlate) {
        for (Car car : spots.values()) {
            if (car.GetLicensePlate().equals(licensePlate)) {
                return true;
            }
        }
        return false;
    }
}
