package com.example.antrikshgyan.presentation.iss.state

import com.example.antrikshgyan.domain.model.iss.ISSPositionModel

data class ISSPositionDataState (
    val isLoading : Boolean = false,
    val issPosition : ISSPositionModel = ISSPositionModel(),
    val error: String = ""
)