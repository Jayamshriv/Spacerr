package com.example.antrikshgyan.presentation.iss.state

import com.example.antrikshgyan.domain.model.iss.ISSPositionDetailModel

data class ISSPositionDetailDataState(
    val isLoading : Boolean = false,
    val issPositionDetail : ISSPositionDetailModel = ISSPositionDetailModel(),
    val error : String? = ""
)