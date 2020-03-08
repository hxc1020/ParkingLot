package com.thoughtworks.ddd;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

class SearchByMaximumSlotsTest {

    @Test
    void when_slots_free_a_3_b_2_c_4_should_return_lot_c() {
        SearchParkingLotRule rule = new SearchByMaximumSlots();
        ParkingLot a = new ParkingLot(1L, 3L);
        ParkingLot b = new ParkingLot(2L, 2L);
        ParkingLot c = new ParkingLot(3L, 4L);
        List<ParkingLot> parkingLots = Arrays.asList(a, b, c);

        ParkingLot lot = rule.search(parkingLots);

        Assertions.assertNotNull(lot);
        Assertions.assertEquals(3L, lot.getId());
    }
}