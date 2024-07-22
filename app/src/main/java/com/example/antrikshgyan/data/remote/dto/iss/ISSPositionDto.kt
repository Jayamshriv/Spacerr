package com.example.antrikshgyan.data.remote.dto.iss

import com.example.antrikshgyan.domain.model.iss.ISSPositionModel

data class ISSPositionDto(
    val altitude: Double?,
    val daynum: Double?,
    val footprint: Double?,
    val id: Int?,
    val latitude: Double?,
    val longitude: Double?,
    val name: String?,
    val solar_lat: Double?,
    val solar_lon: Double?,
    val timestamp: Int?,
    val units: String?,
    val velocity: Double?,
    val visibility: String?
)

fun ISSPositionDto.toISSPositionModel(): ISSPositionModel {
    return ISSPositionModel(
        altitude = altitude, latitude, longitude, name, units, velocity, visibility
    )
}
