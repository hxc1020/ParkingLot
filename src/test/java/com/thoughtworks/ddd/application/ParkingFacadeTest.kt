package com.thoughtworks.ddd.application

import com.thoughtworks.ddd.domain.*
import com.thoughtworks.ddd.infrastructure.ParkingBoyRepository
import com.thoughtworks.ddd.infrastructure.ParkingLotRepository
import com.thoughtworks.ddd.not
import org.junit.jupiter.api.Test

internal class ParkingFacadeTest {
    private val context = Context()
    private val parkingFacade = ParkingFacade()

    @Test
    fun `test park car by parking manager`() {
        val car = Car(PlateNumber("川A 123ABC"))

        val receipt = parkingFacade.parkByParkingManager(context.parkingManager, car)

        receipt not null
    }

    @Test
    fun `test park car by parking boy`() {
        val car = Car(PlateNumber("川A 123ABC"))
        val boy = context.boyRepo.getById(ParkingBoyId(1))!!.toParkingBoy(context.lotRepo)

        val receipt = parkingFacade.parkByParkingBoy(boy, car)

        receipt not null
    }

    @Test
    fun `test find an available parking lot by parking manager`() {
        val parkingLot = parkingFacade.findAvailableParkingLot(context.parkingManager)

        parkingLot not null
    }
}

class Context {
    val boyRepo = ParkingBoyRepository()
    val lotRepo = ParkingLotRepository()
    val parkingManager: ParkingManager

    init {
        lotRepo.addAll((1..5).map { ParkingLot(ParkingLotId(it), it + 1) })
        boyRepo.addAll(
                listOf(
                        ParkingBoyConfig(ParkingBoyId(1), listOf(1, 3, 5).map { ParkingLotId(it) }, ParkingPolicyName.BY_MAXIMUM_SLOT),
                        ParkingBoyConfig(ParkingBoyId(2), listOf(2, 4).map { ParkingLotId(it) }, ParkingPolicyName.BY_ORDER)
                )
        )
        parkingManager = ParkingManagerConfig(ParkingManagerId(1), listOf(ParkingBoyId(1), ParkingBoyId(2)))
                .toParkingManager(boyRepo, lotRepo)
    }
}