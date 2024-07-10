package com.example.antrikshgyan.presentation.nasa.state

import com.example.antrikshgyan.domain.model.nasa.APODModel

data class APODDataState (
    val isLoading: Boolean = false,
    val apod: APODModel = APODModel(),
    val error: String = ""
)