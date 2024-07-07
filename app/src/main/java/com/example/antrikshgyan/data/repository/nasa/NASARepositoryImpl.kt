package com.example.antrikshgyan.data.repository.nasa

import com.example.antrikshgyan.common.Constants
import com.example.antrikshgyan.data.remote.apiservice.APIService
import com.example.antrikshgyan.data.remote.dto.nasa.APODDto
import com.example.antrikshgyan.domain.repository.NASARepository
import javax.inject.Inject

class NASARepositoryImpl @Inject constructor(
    private val nasaApi : APIService
): NASARepository {
    override suspend fun getAPODData(): APODDto {
        return nasaApi.getAPOD(Constants.API_KEY)
    }
}