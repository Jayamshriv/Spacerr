package com.example.antrikshgyan.data.remote.dto.nasa.mars

import androidx.compose.runtime.toMutableStateList
import com.example.antrikshgyan.domain.model.nasa.mars.CameraModel
import com.example.antrikshgyan.domain.model.nasa.mars.RoverModel

data class Rover(
    val cameras: List<CameraX> = emptyList(),
    val id: Int = 0,
    val landing_date: String = "",
    val launch_date: String = "",
    val max_date: String = "",
    val max_sol: Int = 0,
    val name: String = "",
    val status: String = "",
    val total_photos: Int = 0,
)

fun Rover.toRoverModel() : RoverModel{
    val camerasList = mutableListOf<CameraModel>()
    cameras.forEach { item->
        camerasList.add(item.toCameraModel())
    }
    return RoverModel(
        cameras = camerasList.toMutableStateList(),
        landing_date = landing_date,
        launch_date = launch_date,
        max_date = max_date,
        max_sol = max_sol,
        name = name,
        status = status,
        total_photos = total_photos
    )
}