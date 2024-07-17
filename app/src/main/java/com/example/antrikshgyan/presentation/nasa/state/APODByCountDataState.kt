package com.example.antrikshgyan.presentation.nasa.state

import com.example.antrikshgyan.domain.model.nasa.APODModel

data class APODByCountDataState(
    val isLoading : Boolean = false,
    val apodList : List<APODModel>? = listOf(),
    val error: String = ""
)