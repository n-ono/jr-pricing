package com.example.jrpricing.domain.price

data class Price(private val _value: Int) {
    val value = (_value / 10) * 10

    fun forRoundTrip() = Price(value * 2)

    operator fun plus(other: Price) =
        Price(value + other.value)

    operator fun times(n: Int) =
        Price(value * n)
}