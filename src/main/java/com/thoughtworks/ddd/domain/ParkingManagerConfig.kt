package com.thoughtworks.ddd.domain

import com.thoughtworks.ddd.infrastructure.ParkingBoyRepository
import com.thoughtworks.ddd.infrastructure.ParkingLotRepository

data class ParkingManagerConfig(
        val id: ParkingManagerId,
        val parkingBoyIds: List<ParkingBoyId>
)

inline class ParkingManagerId(val id: Int)

fun ParkingManagerConfig.toParkingManager(parkingBoyRepo: ParkingBoyRepository, parkingLotRepo: ParkingLotRepository) =
        ParkingManager(
                parkingBoyRepo.getByIds(this.parkingBoyIds)
                        .map { it.toParkingBoy(parkingLotRepo) }
                        .toList()
        )
