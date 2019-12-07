package com.example.jrpricing.domain.super_express_surcharge

import com.example.jrpricing.domain.core.Destination
import java.lang.RuntimeException

class SuperExpressSurchargeCalculationServiceForHikariFreeSeat(private val destination: Destination) :
    SuperExpressSurchargeCalculationService {
    private val map = mapOf(
        Destination.SHINOSAKA to 4960,
        Destination.HIMEJI to 5390
    )

    override fun calculate() =
        when {
            map.containsKey(destination) -> SuperExpressSurcharge(map.getValue(destination))
            else -> throw RuntimeException("failed: unknown destination $destination")
        }
}
