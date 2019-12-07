package com.example.jrpricing.domain.core

enum class TrainType {
    NOZOMI, HIKARI;

    fun isNozomi() = this == NOZOMI
}