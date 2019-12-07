package com.example.jrpricing.domain.price

import com.example.jrpricing.domain.core.*
import com.example.jrpricing.domain.discount.GroupDiscountPolicy
import com.example.jrpricing.domain.discount.RoundTripDiscountPolicy
import com.example.jrpricing.domain.fare.Fare
import com.example.jrpricing.domain.fare.FareCalculationService
import com.example.jrpricing.domain.super_express_surcharge.SuperExpressSurcharge
import com.example.jrpricing.domain.super_express_surcharge.SuperExpressSurchargeCalculationServiceFactory

class PriceCalculationService(
    private val destination: Destination,
    private val trainType: TrainType,
    private val seatType: SeatType,
    private val passengers: Passengers,
    private val departureDate: DepartureDate,
    private val tripType: TripType
) {
    fun calculate(): Price {
        val passengers = this.passengers.exclude(GroupDiscountPolicy.getComplimentaryPassengers(this.passengers))

        val totalPrice =
            (calculatePriceForChild() * passengers.children) + (calculatePriceForAdult() * passengers.adults)

        return when {
            tripType.isOneWay() -> totalPrice
            else -> totalPrice.forRoundTrip()
        }
    }

    private fun calculatePriceForChild(): Price {
        val fare = applyDiscount(FareCalculationService(destination).calculate())
        val superExpressSurcharge = applyDiscount(
            SuperExpressSurchargeCalculationServiceFactory.create(
                trainType,
                seatType,
                destination,
                departureDate
            ).calculate()
        )
        return Price(fare.forChild() + superExpressSurcharge.forChild())
    }

    private fun calculatePriceForAdult(): Price {
        val fare = applyDiscount(FareCalculationService(destination).calculate())
        val superExpressSurcharge = applyDiscount(
            SuperExpressSurchargeCalculationServiceFactory.create(
                trainType,
                seatType,
                destination,
                departureDate
            ).calculate()
        )
        return Price(fare.forAdult() + superExpressSurcharge.forAdult())
    }

    private fun applyDiscount(fare: Fare) =
        GroupDiscountPolicy.applySmallGroupDiscountPolicy(
            RoundTripDiscountPolicy.apply(fare, tripType, destination), passengers, departureDate
        )

    private fun applyDiscount(superExpressSurcharge: SuperExpressSurcharge) =
        GroupDiscountPolicy.applySmallGroupDiscountPolicy(superExpressSurcharge, passengers, departureDate)
}