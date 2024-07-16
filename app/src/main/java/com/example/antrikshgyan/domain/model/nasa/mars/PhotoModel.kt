package com.example.antrikshgyan.domain.model.nasa.mars

import android.os.Parcelable
import com.example.antrikshgyan.data.remote.dto.nasa.mars.Rover
import kotlinx.parcelize.Parcelize

@Parcelize
data class PhotoModel(
    val camera: CameraModel,
    val earth_date: String = "",
    val img_src: String = "",
    val rover: RoverModel,
    val sol: Int = 0
) : Parcelable
