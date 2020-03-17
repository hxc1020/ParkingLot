package com.thoughtworks.ddd.domain

import com.thoughtworks.ddd.infrastructure.ParkException

class ParkingBoy(
        private val parkingLots: List<ParkingLot>,
        private val parkingPolicy: ParkingPolicy
) {

    val isAvailable = parkingLots.any { it.isNotFull }

    fun findAvailableParkingLot(): ParkingLot =
            parkingPolicy.findParkingLot(parkingLots)
                    ?: throw ParkException("当前无可用停车场")

}