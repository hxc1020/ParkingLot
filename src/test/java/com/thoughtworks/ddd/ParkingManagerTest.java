package com.thoughtworks.ddd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

public class ParkingManagerTest {

    @Test
    void parking_manager_dispatch_success() throws ParkException {
        ParkingLot a = new ParkingLot(1L, 2L);
        ParkingLot b = new ParkingLot(2L, 3L);
        ParkingLot c = new ParkingLot(3L, 1L);
        ParkingLot d = new ParkingLot(4L, 2L);
        ParkingLot e = new ParkingLot(5L, 3L);
        ParkingManager parkingManager = new ParkingManager(Arrays.asList(
                new ParkingBoy(Arrays.asList(a, b, c), new SearchByOrder(new long[]{2L, 3L, 1L})),
                new ParkingBoy(Arrays.asList(c, d, e), new SearchByMaximumSlots())
        ));

        Receipt receipt = parkingManager.dispatchWork(new Car("川A 123ABC"));

        Assertions.assertNotNull(receipt);
    }

    @Test
    void parking_manager_dispatch_exception() throws ParkException {
        ParkingLot a = new ParkingLot(1L, 1L);
        ParkingManager parkingManager = new ParkingManager(
                Collections.singletonList(new ParkingBoy(Collections.singletonList(a), new SearchByOrder(new long[]{1L})))
        );

        parkingManager.dispatchWork(new Car("川A 123ABC"));

        Assertions.assertThrows(ParkException.class, () -> parkingManager.dispatchWork(new Car("川A 123ABD")));
    }
}
