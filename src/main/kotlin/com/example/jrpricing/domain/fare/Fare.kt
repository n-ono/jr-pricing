package com.example.jrpricing.domain.fare

import com.example.jrpricing.domain.discount.DiscountRate

data class Fare(private val value: Int) {
    fun forChild() = value / 2

    fun forAdult() = value

    fun discounted(discountRate: DiscountRate): Fare {
        val discountedValue = value - value * discountRate.value / 100
        return Fare((discountedValue / 10) * 10)
    }
}