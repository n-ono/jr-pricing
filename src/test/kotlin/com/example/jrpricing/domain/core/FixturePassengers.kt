package com.example.jrpricing.domain.core

class FixturePassengers {
    fun anAdult() =
        Passengers(1, 0)

    fun aChild() =
        Passengers(0, 1)

    fun smallGroup() =
        Passengers(3, 5)

    fun largeGroup() =
        Passengers(3, 47)

    fun tooLargeGroup() =
        Passengers(1, 150)
}
