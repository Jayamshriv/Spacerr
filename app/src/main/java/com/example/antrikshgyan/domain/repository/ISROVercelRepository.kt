package com.example.antrikshgyan.domain.repository

import com.example.antrikshgyan.data.remote.dto.isro.ISROCentresDto
import com.example.antrikshgyan.data.remote.dto.isro.ISROCusSatellitesDto
import javax.inject.Inject

interface ISROVercelRepository {

    suspend fun getCusSatellites() : ISROCusSatellitesDto

    suspend fun getCentres() : ISROCentresDto

}