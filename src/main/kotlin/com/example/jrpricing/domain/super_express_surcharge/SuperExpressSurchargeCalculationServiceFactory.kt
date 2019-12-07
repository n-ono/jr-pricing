package com.example.jrpricing.domain.super_express_surcharge

import com.example.jrpricing.domain.core.DepartureDate
import com.example.jrpricing.domain.core.Destination
import com.example.jrpricing.domain.core.SeatType
import com.example.jrpricing.domain.core.TrainType

class SuperExpressSurchargeCalculationServiceFactory {
    companion object {
        fun create(trainType: TrainType, seatType: SeatType, destination: Destination, departureDate: DepartureDate) =
            when {
                trainType.isNozomi() -> createServiceForNozomi(seatType, destination, departureDate)
                else -> createServiceForHikari(seatType, destination, departureDate)
            }

        private fun createServiceForNozomi(seatType: SeatType, destination: Destination, departureDate: DepartureDate) =
            when {
                seatType.isFree() -> SuperExpressSurchargeCalculationServiceForNozomiFreeSeat(destination)
                else -> SuperExpressSurchargeCalculationServiceForNozomiReservedSeat(destination, departureDate)
            }

        private fun createServiceForHikari(seatType: SeatType, destination: Destination, departureDate: DepartureDate) =
            when {
                seatType.isFree() -> SuperExpressSurchargeCalculationServiceForHikariFreeSeat(destination)
                else -> SuperExpressSurchargeCalculationServiceForHikariReservedSeat(destination, departureDate)
            }
    }
}