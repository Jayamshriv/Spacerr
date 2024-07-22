package com.example.antrikshgyan.domain.repository.isro

import com.example.antrikshgyan.data.remote.dto.isro.ISROCentresDto
import com.example.antrikshgyan.data.remote.dto.isro.ISROCusSatellitesDto

interface ISROVercelRepository {

    suspend fun getCusSatellites() : ISROCusSatellitesDto

    suspend fun getCentres() : ISROCentresDto

}