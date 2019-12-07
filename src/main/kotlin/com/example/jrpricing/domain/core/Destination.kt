package com.example.jrpricing.domain.core

enum class Destination(private val kilometer: Int) {
    SHINOSAKA(553), HIMEJI(644);

    // todo rename
    fun isTooFar() = 601 <= kilometer
}