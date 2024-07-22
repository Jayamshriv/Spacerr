package com.example.antrikshgyan.data.remote.dto.iss

import com.example.antrikshgyan.domain.model.iss.ISSPositionDetailModel

data class ISSPositionDetailDto(
    val country_code: String,
    val latitude: String,
    val longitude: String,
    val map_url: String,
    val offset: Int,
    val timezone_id: String
)

fun ISSPositionDetailDto.toISSPositionDetailModel() : ISSPositionDetailModel{
    return ISSPositionDetailModel(
        country_code, latitude, longitude, map_url, timezone_id
    )
}