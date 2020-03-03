package com.thoughtworks.ddd;

import java.util.Date;

public class Receipt {

    private String plateNumber;
    private String token;
    private String parkingTime;

    public Receipt(Car car) {
        this.parkingTime = String.valueOf(new Date().getTime());
        this.plateNumber = car.getPlateNumber();
        this.token = car.getPlateNumber() + this.parkingTime;
    }

    public String getToken() {
        return token;
    }

    public String getParkingTime() {
        return parkingTime;
    }

    public String getPlateNumber() {
        return plateNumber;
    }
}
