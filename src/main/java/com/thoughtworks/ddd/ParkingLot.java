package com.thoughtworks.ddd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ParkingLot {

    private Long id;
    private Long capacity;
    private Map<String, Car> cars;
    private List<Receipt> receipts;

    public ParkingLot(Long id, Long capacity) {
        this.id = id;
        this.capacity = capacity;
        this.receipts = new ArrayList<>();
        this.cars = new HashMap<>();
    }

    public Long getId() {
        return id;
    }

    public Receipt parkCar(Car car) throws ParkException {
        long next = receipts.size() + 1;
        if (next > capacity) {
            throw new ParkException("当前车位已满不能继续停车");
        }

        Receipt receipt = new Receipt(car);
        this.receipts.add(receipt);
        this.cars.put(car.getPlateNumber(), car);
        return receipt;
    }

    public Car getCar(Receipt receipt) throws ParkException {
        if (!this.receipts.contains(receipt)) {
            throw new ParkException("小票无效，无法取车");
        }
        Car car = cars.get(receipt.getPlateNumber());
        receipts.remove(receipt);
        cars.remove(car.getPlateNumber());
        return car;
    }

}
