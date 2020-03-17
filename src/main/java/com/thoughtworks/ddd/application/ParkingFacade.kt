package com.thoughtworks.ddd.application

import com.thoughtworks.ddd.domain.*


class ParkingFacade {

    fun parkByParkingManager(manager: ParkingManager, car: Car): Receipt {
        return manager.findAvailableParkingBoy()
                .findAvailableParkingLot()
                .parkCar(car)

    }

    fun parkByParkingBoy(boy: ParkingBoy, car: Car): Receipt {
        return boy.findAvailableParkingLot()
                .parkCar(car)
    }

    fun findAvailableParkingLot(manager: ParkingManager): ParkingLot {
        return manager.findAvailableParkingBoy()
                .findAvailableParkingLot()
    }
}