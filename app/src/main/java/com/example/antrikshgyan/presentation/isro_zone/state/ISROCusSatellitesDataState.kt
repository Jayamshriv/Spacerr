package com.example.antrikshgyan.presentation.isro_zone.state

import com.example.antrikshgyan.data.remote.dto.isro.CustomerSatellite
import com.example.antrikshgyan.domain.model.CustomerSatelliteModel
import java.lang.Error

data class ISROCusSatellitesDataState(
    val isLoading : Boolean = false,
    val customerSatellite:List< CustomerSatelliteModel> = emptyList(),
    val error:String = ""
)
