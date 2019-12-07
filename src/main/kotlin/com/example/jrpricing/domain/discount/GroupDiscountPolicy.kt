package com.example.jrpricing.domain.discount

import com.example.jrpricing.domain.core.DepartureDate
import com.example.jrpricing.domain.core.Passengers
import com.example.jrpricing.domain.fare.Fare
import com.example.jrpricing.domain.super_express_surcharge.SuperExpressSurcharge
import java.time.Month
import kotlin.math.min

class GroupDiscountPolicy {
    companion object {
        fun applySmallGroupDiscountPolicy(fare: Fare, passengers: Passengers, departureDate: DepartureDate) =
            when {
                8 <= passengers.all() -> fare.discounted(getDiscountRate(departureDate))
                else -> fare
            }

        fun applySmallGroupDiscountPolicy(
            superExpressSurcharge: SuperExpressSurcharge,
            passengers: Passengers,
            departureDate: DepartureDate
        ) =
            when {
                8 <= passengers.all() -> superExpressSurcharge.discounted(getDiscountRate(departureDate))
                else -> superExpressSurcharge
            }

        fun getComplimentaryPassengers(passengers: Passengers): LargeGroupDiscountPolicyAppliedPassengers {
            if (passengers.all() <= 30) {
                return LargeGroupDiscountPolicyAppliedPassengers(0, 0)
            }

            if (passengers.all() <= 50) {
                return when {
                    1 <= passengers.adults -> LargeGroupDiscountPolicyAppliedPassengers(1, 0)
                    else -> LargeGroupDiscountPolicyAppliedPassengers(0, 1)
                }
            }

            val complimentaryPassengers = passengers.all() / 50
            val adults = min(passengers.adults, complimentaryPassengers)
            val children = min(passengers.children, complimentaryPassengers - adults)

            return LargeGroupDiscountPolicyAppliedPassengers(adults, children)
        }

        private fun getDiscountRate(departureDate: DepartureDate): DiscountRate {
            val departureMonth = departureDate.value.month
            return if ((departureMonth == Month.DECEMBER) && (21 <= departureDate.value.dayOfMonth)) {
                DiscountRate._10PERCENT
            } else if ((departureMonth == Month.JANUARY) && (departureDate.value.dayOfMonth <= 10)) {
                DiscountRate._10PERCENT
            } else {
                DiscountRate._15PERCENT
            }
        }
    }
}