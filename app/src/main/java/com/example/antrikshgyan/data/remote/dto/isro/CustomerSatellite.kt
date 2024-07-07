package com.example.antrikshgyan.data.remote.dto.isro

import com.example.antrikshgyan.domain.model.CustomerSatelliteModel

data class CustomerSatellite(
    val country: String,
    val id: String,
    val launch_date: String,
    val launcher: String,
    val mass: String
)

fun CustomerSatellite.toCustomerSatelliteModel() : CustomerSatelliteModel{
    return CustomerSatelliteModel(
        country,
    launch_date,
    launcher,
    mass,
    )
}