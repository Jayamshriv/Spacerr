package com.example.antrikshgyan.presentation.isro_zone.state

import com.example.antrikshgyan.domain.model.isro.ISROSpaceCraftModel

data class ISROSpacecraftDataState (
    val isLoading : Boolean = false,
    val spacecraft : List<ISROSpaceCraftModel> = emptyList(),
    val error : String = ""
)