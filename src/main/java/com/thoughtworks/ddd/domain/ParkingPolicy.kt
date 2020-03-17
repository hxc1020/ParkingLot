package com.thoughtworks.ddd.domain

enum class ParkingPolicyName {
    BY_ORDER, BY_MAXIMUM_SLOT
}

fun ParkingPolicyName.generate(): ParkingPolicy {
    return when (this) {
        ParkingPolicyName.BY_ORDER -> ByOrder()
        ParkingPolicyName.BY_MAXIMUM_SLOT -> ByMaximumSlots()
    }
}

interface ParkingPolicy {
    fun findParkingLot(parkingLots: List<ParkingLot>): ParkingLot?
}

class ByOrder : ParkingPolicy {

    override fun findParkingLot(parkingLots: List<ParkingLot>): ParkingLot? {
        return parkingLots.firstOrNull()
    }
}

class ByMaximumSlots : ParkingPolicy {
    override fun findParkingLot(parkingLots: List<ParkingLot>): ParkingLot? {
        return parkingLots.asSequence()
                .filter { it.isNotFull }
                .sortedByDescending { it.free }
                .toList()
                .firstOrNull()
    }

}