package com.example.antrikshgyan.domain.model.nasa.mars

data class RoverModel(
    val cameras: MutableList<CameraModel> = mutableListOf(),
    val landing_date: String = "",
    val launch_date: String = "",
    val max_date: String = "",
    val max_sol: Int,
    val name: String = "",
    val status: String = "",
    val total_photos: Int = 0
)
