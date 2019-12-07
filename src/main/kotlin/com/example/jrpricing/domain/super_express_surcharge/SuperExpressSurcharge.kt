package com.example.jrpricing.domain.super_express_surcharge

import com.example.jrpricing.domain.discount.DiscountRate

data class SuperExpressSurcharge(private val value: Int) {
    fun variedBySeason(season: Season) =
        SuperExpressSurcharge(value + season.value)

    fun forChild() = value / 2

    fun forAdult() = value

    fun discounted(discountRate: DiscountRate): SuperExpressSurcharge {
        val discountedValue = value - (value * discountRate.value / 100)
        return SuperExpressSurcharge((discountedValue / 10) * 10)
    }
}