package com.thoughtworks.ddd.domain

import com.thoughtworks.ddd.infrastructure.ParkingLotRepository

data class ParkingBoyConfig(
        val id: ParkingBoyId,
        val parkingLotIds: List<ParkingLotId>,
        val parkingPolicyName: ParkingPolicyName
)

inline class ParkingBoyId(val id: Int)

fun ParkingBoyConfig.toParkingBoy(repo: ParkingLotRepository) =
        ParkingBoy(repo.getByIds(this.parkingLotIds), this.parkingPolicyName.generate())