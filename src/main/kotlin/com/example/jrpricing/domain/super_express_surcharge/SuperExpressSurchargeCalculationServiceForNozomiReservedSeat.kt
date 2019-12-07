package com.example.jrpricing.domain.super_express_surcharge

import com.example.jrpricing.domain.core.DepartureDate
import com.example.jrpricing.domain.core.Destination
import java.lang.RuntimeException

class SuperExpressSurchargeCalculationServiceForNozomiReservedSeat(
    private val destination: Destination,
    private val departureDate: DepartureDate
) : SuperExpressSurchargeCalculationService {
    private val map = mapOf(
        Destination.SHINOSAKA to 5810,
        Destination.HIMEJI to 6450
    )

    override fun calculate() =
        getSuperExpressSurchargeForSuperExpress().variedBySeason(Season.of(departureDate))

    private fun getSuperExpressSurchargeForSuperExpress() =
        when {
            map.containsKey(destination) -> SuperExpressSurcharge(map.getValue(destination))
            else -> throw RuntimeException("failed: unknown destination $destination")
        }
}
