package com.example.jrpricing.domain.core

import java.time.LocalDate

class FixtureDepartureDate {
    fun get() =
        DepartureDate(LocalDate.of(2019, 9, 4))

    fun peakStart() =
        DepartureDate(LocalDate.of(2019, 12, 25))

    fun peakEnd() =
        DepartureDate(LocalDate.of(2020, 1, 10))

    fun peakOffStart() =
        DepartureDate(LocalDate.of(2020, 1, 16))

    fun peakOffEnd() =
        DepartureDate(LocalDate.of(2020, 1, 30))

    fun smallGroupDiscountStart() =
        DepartureDate(LocalDate.of(2019, 12, 21))

    fun smallGroupDiscountEnd() =
        DepartureDate(LocalDate.of(2020, 1, 10))
}
