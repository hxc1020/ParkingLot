package com.thoughtworks.ddd;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ParkingBoy {

    private Map<Long, ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots.stream().collect(Collectors.toMap(ParkingLot::getId, it -> it));
    }

    public List<ParkingLot> getParkingLots() {
        return (List<ParkingLot>) parkingLots.values();
    }

    public void doParkByOrder(Long[] order, Car car) {
        System.out.println("开始顺序停车");
        for (Long next : order) {
            Receipt receipt;
            ParkingLot parkingLot = parkingLots.get(next);
            try {
                receipt = parkingLot.parkCar(car);
                System.out.println("停入【" + parkingLot.getId() + "】停车场，receipt token:" + receipt.getToken());
            } catch (ParkException e) {
                System.out.println(e.getMessage());
                continue;
            }

            try {
                parkingLot.getCar(receipt);
                System.out.println("取出【" + receipt.getPlateNumber() + "】，离开【" + parkingLot.getId() + "】停车场");
            } catch (ParkException e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
