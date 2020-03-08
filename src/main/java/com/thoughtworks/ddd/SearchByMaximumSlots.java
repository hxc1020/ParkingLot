package com.thoughtworks.ddd;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SearchByMaximumSlots implements SearchParkingLotRule {
    @Override
    public ParkingLot search(List<ParkingLot> parkingLots) {
        List<ParkingLot> sortedLots = parkingLots.stream()
                .filter(ParkingLot::isNotFull)
                .sorted(Comparator.comparingLong(ParkingLot::getFree).reversed())
                .collect(Collectors.toList());
        if (sortedLots.isEmpty()) {
            return null;
        }
        return sortedLots.get(0);
    }
}
