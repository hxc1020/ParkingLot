package com.thoughtworks.ddd.domain

import com.thoughtworks.ddd.infrastructure.ParkException

class ParkingManager(
        private val parkingBoys: List<ParkingBoy>
) {
    fun findAvailableParkingBoy(): ParkingBoy =
            parkingBoys.firstOrNull { it.isAvailable }
                    ?: throw ParkException("当前无可用停车小弟")
}