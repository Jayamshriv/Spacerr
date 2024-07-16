package com.example.antrikshgyan.domain.model.nasa.mars

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class CameraModel(
    val full_name: String = "",
    val name: String = "",
) : Parcelable
