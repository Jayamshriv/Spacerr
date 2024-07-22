package com.example.antrikshgyan.domain.model.iss

data class ISSPositionModel(
    val altitude: Double? = 0.0,
    val latitude: Double?= 0.0,
    val longitude: Double? = 0.0,
    val name: String? ="",
    val units: String? ="",
    val velocity: Double?  = 0.0,
    val visibility: String? =""
)
