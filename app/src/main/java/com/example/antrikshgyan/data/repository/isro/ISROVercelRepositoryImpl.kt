package com.example.antrikshgyan.data.repository.isro

import com.example.antrikshgyan.data.remote.apiservice.IsroVercelApiService
import com.example.antrikshgyan.data.remote.dto.isro.ISROCentresDto
import com.example.antrikshgyan.data.remote.dto.isro.ISROCusSatellitesDto
import com.example.antrikshgyan.domain.repository.isro.ISROVercelRepository
import javax.inject.Inject

class ISROVercelRepositoryImpl @Inject constructor(
    private val apiService: IsroVercelApiService
) : ISROVercelRepository {

    override suspend fun getCusSatellites(): ISROCusSatellitesDto {
        return apiService.getCusSatellites()
    }

    override suspend fun getCentres(): ISROCentresDto {
        return apiService.getCentres()
    }
}