package com.thoughtworks.ddd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;


class SearchByOrderTest {

    @Test
    void when_order_2_1_3_should_return_2() {
        SearchParkingLotRule rule = new SearchByOrder(new long[]{2L, 1L, 3L});
        ParkingLot a = new ParkingLot(1L, 3L);
        ParkingLot b = new ParkingLot(2L, 2L);
        ParkingLot c = new ParkingLot(3L, 4L);
        List<ParkingLot> parkingLots = Arrays.asList(a, b, c);

        ParkingLot lot = rule.search(parkingLots);

        Assertions.assertNotNull(lot);
        Assertions.assertEquals(2L, lot.getId());
    }

    @Test
    void when_order_2_1_3_and_2_is_full_should_return_1() throws ParkException {
        SearchParkingLotRule rule = new SearchByOrder(new long[]{2L, 1L, 3L});
        ParkingLot a = new ParkingLot(1L, 2L);
        ParkingLot b = new ParkingLot(2L, 2L);
        ParkingLot c = new ParkingLot(3L, 4L);
        List<ParkingLot> parkingLots = Arrays.asList(a, b, c);

        b.parkCar(new Car("川A 123ABC"));
        b.parkCar(new Car("川A 123ABD"));
        ParkingLot lot = rule.search(parkingLots);

        Assertions.assertNotNull(lot);
        Assertions.assertEquals(1L, lot.getId());
    }
}