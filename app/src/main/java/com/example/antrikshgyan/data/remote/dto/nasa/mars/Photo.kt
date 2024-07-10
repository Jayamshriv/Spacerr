package com.example.antrikshgyan.data.remote.dto.nasa.mars

import com.example.antrikshgyan.domain.model.nasa.mars.PhotoModel

data class Photo(
    val camera: Camera,
    val earth_date: String,
    val id: Int,
    val img_src: String,
    val rover: Rover,
    val sol: Int
)

fun Photo.toPhotoModel(): PhotoModel {
    return PhotoModel(
        camera = camera.toCameraModel(),
        earth_date = earth_date,
        img_src = img_src,
        rover = rover.toRoverModel(),
        sol = sol
    )
}