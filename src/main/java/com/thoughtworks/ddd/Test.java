package com.thoughtworks.ddd;

import java.util.Arrays;

public class Test {
    public static void main(String[] args) {
        ParkingBoy boy = new ParkingBoy(Arrays.asList(
                new ParkingLot(1L, 3L),
                new ParkingLot(2L, 5L),
                new ParkingLot(3L, 3L),
                new ParkingLot(4L, 2L),
                new ParkingLot(5L, 6L)
        ));

        boy.doParkByOrder(new Long[]{2L,4L,3L,1L,5L} , new Car("川A 5T3P0"));
    }
}
