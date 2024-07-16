package com.example.antrikshgyan.data.remote.dto.nasa.mars

import androidx.compose.runtime.toMutableStateList
import com.example.antrikshgyan.domain.model.nasa.mars.MarsRoverModel
import com.example.antrikshgyan.domain.model.nasa.mars.PhotoModel

data class MarsRoverDto(
    val photos: List<Photo>
)

fun MarsRoverDto.toMarsRoverModel(): MarsRoverModel {
    val photosModel = mutableListOf<PhotoModel>()
    photos.forEach { item ->
        photosModel.add(item.toPhotoModel())
    }
    return MarsRoverModel(
        photos = photosModel.toMutableStateList()
    )
}
