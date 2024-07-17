package com.example.antrikshgyan.domain.model.nasa.mars

import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.snapshots.SnapshotStateList

data class MarsRoverModel(
    val photos: MutableList<PhotoModel> = mutableListOf()
)