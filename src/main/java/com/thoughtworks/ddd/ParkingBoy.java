package com.thoughtworks.ddd;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {

    private List<ParkingLot> parkingLots;
    private SearchParkingLotRule rule;

    public ParkingBoy(List<ParkingLot> parkingLots, SearchParkingLotRule rule) {
        this.parkingLots = parkingLots;
        this.rule = rule;
    }

    public Receipt parkCar(Car car) {
        ParkingLot parkingLot = rule.search(parkingLots);
        try {
            return parkingLot.parkCar(car);
        } catch (ParkException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ParkingLot searchAvailableParkingLot() {
        List<ParkingLot> availableParkingLots = parkingLots.stream().filter(ParkingLot::isNotFull).collect(Collectors.toList());
        if (availableParkingLots.isEmpty()) {
            return null;
        }
        return availableParkingLots.get(0);
    }

    public Boolean isAvailable() {
        return parkingLots.stream().anyMatch(ParkingLot::isNotFull);
    }
}
