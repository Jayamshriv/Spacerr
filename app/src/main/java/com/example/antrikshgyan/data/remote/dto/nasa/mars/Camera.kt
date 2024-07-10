package com.example.antrikshgyan.data.remote.dto.nasa.mars

import com.example.antrikshgyan.domain.model.nasa.mars.CameraModel

data class Camera(
    val full_name: String = "",
    val id: Int = 0,
    val name: String = "",
    val rover_id: Int = 0
)

fun Camera.toCameraModel() : CameraModel{
    return  CameraModel(
        full_name,
        name
    )
}