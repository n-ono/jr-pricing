package com.example.jrpricing.domain.super_express_surcharge

import com.example.jrpricing.domain.core.DepartureDate
import java.time.Month

enum class Season(val value: Int) {
    REGULAR(0), PEAK(200), OFF_PEAK(-200);

    companion object {
        fun of(departureDate: DepartureDate) =
            when {
                isPeak(departureDate) -> PEAK
                isOffPeak(departureDate) -> OFF_PEAK
                else -> REGULAR
            }

        private fun isPeak(departureDate: DepartureDate) =
            when (departureDate.value.month) {
                Month.DECEMBER -> 25 <= departureDate.value.dayOfMonth
                Month.JANUARY -> departureDate.value.dayOfMonth <= 10
                else -> false
            }

        private fun isOffPeak(departureDate: DepartureDate) =
            when (departureDate.value.month) {
                Month.JANUARY -> (16 <= departureDate.value.dayOfMonth) && (departureDate.value.dayOfMonth <= 30)
                else -> false
            }
    }
}