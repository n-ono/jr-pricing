package com.example.jrpricing.domain.core

import com.example.jrpricing.domain.discount.LargeGroupDiscountPolicyAppliedPassengers

class Passengers(val adults: Int, val children: Int) {
    fun all() = adults + children

    fun exclude(largeGroupDiscountPolicyAppliedPassengers: LargeGroupDiscountPolicyAppliedPassengers) =
        Passengers(
            adults - largeGroupDiscountPolicyAppliedPassengers.adults,
            children - largeGroupDiscountPolicyAppliedPassengers.children
        )
}