package com.example.jrpricing.domain.discount

import com.example.jrpricing.domain.core.Destination
import com.example.jrpricing.domain.core.TripType
import com.example.jrpricing.domain.fare.Fare

class RoundTripDiscountPolicy {
    companion object {
        fun apply(fare: Fare, tripType: TripType, destination: Destination) =
            when {
                tripType.isOneWay() -> fare
                destination.isTooFar() -> fare.discounted(DiscountRate._10PERCENT)
                else -> fare
            }
    }
}