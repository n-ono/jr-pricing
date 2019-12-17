package com.example.jrpricing.presentation

import com.example.jrpricing.application.PriceCalculationService
import com.example.jrpricing.presentation.form.ApplicationForm
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PriceCalculationApi {
    @Autowired
    private lateinit var service: PriceCalculationService

    @GetMapping("/jr-pricing/apply")
    @ResponseBody
    fun apply(@Validated @RequestBody applicationForm: ApplicationForm) =
        service.apply(
            applicationForm.getDestination(),
            applicationForm.getTrainType(),
            applicationForm.getSeatType(),
            applicationForm.getPassengers(),
            applicationForm.getDepartureDate(),
            applicationForm.getTripType()
        ).value
}
