package com.example.antrikshgyan.presentation.apod

import com.example.antrikshgyan.domain.model.APODModel

data class APODDataState (
    val isLoading: Boolean = false,
    val apod: APODModel = APODModel(),
    val error: String = ""
)