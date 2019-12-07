package com.example.jrpricing.domain.core

enum class SeatType {
    FREE, RESERVED;

    fun isFree() = this == FREE
}