package com.example.jrpricing.application

import com.example.jrpricing.domain.core.*
import com.example.jrpricing.domain.price.PriceCalculationService
import org.springframework.stereotype.Service

@Service
class PriceCalculationService {
    fun apply(
        destination: Destination,
        trainType: TrainType,
        seatType: SeatType,
        passengers: Passengers,
        departureDate: DepartureDate,
        tripType: TripType
    ) =
        PriceCalculationService(
            destination,
            trainType,
            seatType,
            passengers,
            departureDate,
            tripType
        ).calculate()
}