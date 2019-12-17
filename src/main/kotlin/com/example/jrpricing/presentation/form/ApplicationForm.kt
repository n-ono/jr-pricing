package com.example.jrpricing.presentation.form

import com.example.jrpricing.domain.core.*
import java.time.LocalDate
import javax.validation.constraints.Pattern
import javax.validation.constraints.PositiveOrZero

data class ApplicationForm(
    @field:Pattern(regexp = "(shinosaka|himeji)")
    private val destination: String,
    @field:Pattern(regexp = "(nozomi|hikari)")
    private val trainType: String,
    @field:Pattern(regexp = "(free|reserved)")
    private val seatType: String,
    @field:PositiveOrZero
    private val adults: Int,
    @field:PositiveOrZero
    private val children: Int,
    @field:Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}")
    private val departureDate: String,
    @field:Pattern(regexp = "(one-way|round-trip)")
    private val tripType: String
) {
    fun getDestination() =
        when (destination) {
            "shinosaka" -> Destination.SHINOSAKA
            else -> Destination.HIMEJI
        }

    fun getTrainType() =
        when (trainType) {
            "nozomi" -> TrainType.NOZOMI
            else -> TrainType.HIKARI
        }

    fun getSeatType() =
        when (seatType) {
            "free" -> SeatType.FREE
            else -> SeatType.RESERVED
        }

    fun getPassengers() =
        Passengers(adults, children)

    fun getDepartureDate(): DepartureDate {
        val (year, month, dayOfMonth) = departureDate.split("-").map(String::toInt)
        return DepartureDate(LocalDate.of(year, month, dayOfMonth))
    }

    fun getTripType() =
        when (tripType) {
            "one-way" -> TripType.ONE_WAY
            else -> TripType.ROUND_TRIP
        }
}
