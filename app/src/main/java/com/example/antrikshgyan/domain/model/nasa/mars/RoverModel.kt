package com.example.antrikshgyan.domain.model.nasa.mars

import android.os.Parcelable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import kotlinx.parcelize.Parcelize

@Parcelize
data class RoverModel(
    val cameras: MutableList<CameraModel> = mutableListOf(),
    val landing_date: String = "",
    val launch_date: String = "",
    val max_date: String = "",
    val max_sol: Int,
    val name: String = "",
    val status: String = "",
    val total_photos: Int = 0
) : Parcelable
