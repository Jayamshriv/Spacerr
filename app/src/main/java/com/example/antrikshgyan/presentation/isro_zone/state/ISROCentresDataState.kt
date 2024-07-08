package com.example.antrikshgyan.presentation.isro_zone.state

import com.example.antrikshgyan.domain.model.CentreModel

data class ISROCentresDataState (
    val isLoading : Boolean = false,
    val centres : List<CentreModel> = emptyList(),
    val error : String = "",
)