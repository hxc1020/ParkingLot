package com.thoughtworks.ddd.infrastructure

import com.thoughtworks.ddd.domain.ParkingBoyConfig
import com.thoughtworks.ddd.domain.ParkingBoyId

class ParkingBoyRepository {
    private val parkingBoys = mutableMapOf<ParkingBoyId, ParkingBoyConfig>()

    fun add(e: ParkingBoyConfig) {
        parkingBoys[e.id] = e
    }

    fun addAll(list: List<ParkingBoyConfig>) {
        parkingBoys.putAll(list.map { it.id to it })
    }

    fun remove(e: ParkingBoyConfig) = parkingBoys.remove(e.id)

    fun getByIds(ids: List<ParkingBoyId>) = parkingBoys.filter { ids.contains(it.key) }.values.toList()

    fun getById(id: ParkingBoyId) = parkingBoys[id]

}