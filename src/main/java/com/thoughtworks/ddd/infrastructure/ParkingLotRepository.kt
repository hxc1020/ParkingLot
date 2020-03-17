package com.thoughtworks.ddd.infrastructure

import com.thoughtworks.ddd.domain.ParkingLot
import com.thoughtworks.ddd.domain.ParkingLotId

class ParkingLotRepository {
    private val parkingLots = mutableMapOf<ParkingLotId, ParkingLot>()

    fun add(e: ParkingLot) {
        parkingLots[e.id] = e
    }

    fun addAll(list: List<ParkingLot>) {
        parkingLots.putAll(list.map { it.id to it })
    }

    fun remove(e: ParkingLot) = parkingLots.remove(e.id)

    fun getByIds(ids: List<ParkingLotId>) = parkingLots.filter { ids.contains(it.key) }.values.toList()

}