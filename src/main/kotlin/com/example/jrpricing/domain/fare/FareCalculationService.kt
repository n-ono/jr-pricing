package com.example.jrpricing.domain.fare

import com.example.jrpricing.domain.core.Destination
import java.lang.RuntimeException

class FareCalculationService(private val destination: Destination) {
    private val map = mapOf(
        Destination.SHINOSAKA to 8910,
        Destination.HIMEJI to 10010
    )

    fun calculate() =
        when {
            map.containsKey(destination) -> Fare(map.getValue(destination))
            else -> throw RuntimeException("failed: unknown destination $destination")
        }
}