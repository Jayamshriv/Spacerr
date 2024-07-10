package com.example.antrikshgyan.presentation.isro_zone.state

import com.example.antrikshgyan.domain.model.isro.CustomerSatelliteModel

data class ISROCusSatellitesDataState(
    val isLoading : Boolean = false,
    val customerSatellite:List<CustomerSatelliteModel> = emptyList(),
    val error:String = ""
)
