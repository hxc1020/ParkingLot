package com.thoughtworks.ddd;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

public class SearchByOrder implements SearchParkingLotRule {

    private long[] order;

    public SearchByOrder(long[] order) {
        this.order = order;
    }

    @Override
    public ParkingLot search(List<ParkingLot> parkingLots) {
        Map<Long, ParkingLot> lotMap = parkingLots.stream().collect(Collectors.toMap(ParkingLot::getId, it -> it));
        for (long i : order) {
            ParkingLot lot = lotMap.get(i);
            if (!Objects.isNull(lot) && lot.isNotFull()) {
                return lot;
            }
        }
        return null;
    }
}
