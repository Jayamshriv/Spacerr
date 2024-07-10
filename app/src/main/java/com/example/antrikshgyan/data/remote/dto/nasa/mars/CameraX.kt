package com.example.antrikshgyan.data.remote.dto.nasa.mars

import com.example.antrikshgyan.domain.model.nasa.mars.CameraModel

data class CameraX(
    val full_name: String,
    val name: String
)

fun CameraX.toCameraModel() : CameraModel {
    return  CameraModel(
        full_name,
        name
    )
}