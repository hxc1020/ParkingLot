package com.thoughtworks.ddd.domain

import com.thoughtworks.ddd.infrastructure.ParkException

class ParkingLot(
        val id: ParkingLotId,
        private val capacity: Int
) {
    private val cars = mutableMapOf<PlateNumber, Car>()
    private val receipts = mutableListOf<Receipt>()

    val free get() = capacity - cars.size
    val isNotFull get() = capacity > cars.size

    fun parkCar(car: Car): Receipt {
        if (isNotFull) {
            val receipt = Receipt(car.plateNumber)
            receipts.add(receipt)
            cars[car.plateNumber] = car
            return receipt
        }
        throw ParkException("当前车位已满不能继续停车")
    }

    fun getCar(receipt: Receipt): Car {
        if (receiptAvailable(receipt)) {
            return with(receipt) {
                receipts.remove(this)
                cars.remove(plateNumber)!!
            }
        }
        throw ParkException("小票无效，无法取车")
    }

    private fun receiptAvailable(receipt: Receipt): Boolean = receipts.contains(receipt)

}

inline class ParkingLotId(val id: Int)

data class Car(val plateNumber: PlateNumber)

inline class PlateNumber(val number: String)

data class Receipt(val plateNumber: PlateNumber)