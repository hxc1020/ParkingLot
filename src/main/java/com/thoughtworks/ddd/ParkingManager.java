package com.thoughtworks.ddd;

import java.util.List;

public class ParkingManager {
    private List<ParkingBoy> parkingBoys;

    public ParkingManager(List<ParkingBoy> parkingBoys) {
        this.parkingBoys = parkingBoys;
    }

    public Receipt dispatchWork(Car car) throws ParkException {
        // 未知的委派规则，选择当前任何可用的停车小弟
        return parkingBoys.stream()
                .filter(ParkingBoy::isAvailable)
                .findAny()
                .orElseThrow(() -> new ParkException("没有可用停车小弟"))
                .parkCar(car);
    }
}
