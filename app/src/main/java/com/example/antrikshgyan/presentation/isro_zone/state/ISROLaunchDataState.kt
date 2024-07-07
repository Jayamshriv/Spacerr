package com.example.antrikshgyan.presentation.isro_zone.state

import com.example.antrikshgyan.domain.model.ISROLaunchesModel

data class ISROLaunchDataState(
    val isLoading: Boolean = false,
    val launch: List<ISROLaunchesModel> = listOf(),
    val error: String = ""
)
