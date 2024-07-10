package com.example.antrikshgyan.presentation.mars.state

import com.example.antrikshgyan.domain.model.nasa.mars.MarsRoverModel

data class MarsRoverImageDataState(
    val isLoading : Boolean = false,
    val marsRoverImage : MarsRoverModel = MarsRoverModel(),
    val error : String = ""
)
