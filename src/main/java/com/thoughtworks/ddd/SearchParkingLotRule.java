package com.thoughtworks.ddd;

import java.util.List;

public interface SearchParkingLotRule {
    ParkingLot search(List<ParkingLot> parkingLots);
}
