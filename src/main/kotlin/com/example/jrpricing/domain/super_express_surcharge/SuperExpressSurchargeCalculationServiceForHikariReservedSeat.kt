package com.example.jrpricing.domain.super_express_surcharge

import com.example.jrpricing.domain.core.DepartureDate
import com.example.jrpricing.domain.core.Destination
import java.lang.RuntimeException

class SuperExpressSurchargeCalculationServiceForHikariReservedSeat(
    private val destination: Destination,
    private val departureDate: DepartureDate
) : SuperExpressSurchargeCalculationService {
    private val map = mapOf(
        Destination.SHINOSAKA to 5490,
        Destination.HIMEJI to 5920
    )

    override fun calculate() =
        getSuperExpressSurchargeFor().variedBySeason(Season.of(departureDate))

    private fun getSuperExpressSurchargeFor() =
        when {
            map.containsKey(destination) -> SuperExpressSurcharge(map.getValue(destination))
            else -> throw RuntimeException("failed: unknown destination $destination")
        }
}
