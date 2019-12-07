package com.example.jrpricing.domain.super_express_surcharge

import com.example.jrpricing.domain.core.Destination
import java.lang.RuntimeException

class SuperExpressSurchargeCalculationServiceForNozomiFreeSeat(
    private val destination: Destination
) : SuperExpressSurchargeCalculationService {
    private val map = mapOf(
        Destination.SHINOSAKA to 5280,
        Destination.HIMEJI to 5920
    )

    override fun calculate() =
        when {
            map.containsKey(destination) -> SuperExpressSurcharge(map.getValue(destination))
            else -> throw RuntimeException("unknown destination $destination")
        }
}
