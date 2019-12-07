package com.example.jrpricing.domain.core

enum class TripType {
    ONE_WAY, ROUND_TRIP;

    fun isOneWay() = this == ONE_WAY
}