package com.example.antrikshgyan.data.repository.nasa

import com.example.antrikshgyan.common.Constants
import com.example.antrikshgyan.data.remote.apiservice.APIService
import com.example.antrikshgyan.data.remote.dto.nasa.APODDto
import com.example.antrikshgyan.data.remote.dto.nasa.mars.MarsRoverDto
import com.example.antrikshgyan.domain.repository.NASARepository
import javax.inject.Inject

class NASARepositoryImpl @Inject constructor(
    private val nasaApi: APIService
) : NASARepository {
    override suspend fun getAPODData(): APODDto {
        return nasaApi.getAPOD(Constants.API_KEY)
    }

    override suspend fun getMarsRoversImages(
        sol: Int,
        page: Int,
    ): MarsRoverDto {
        return nasaApi.getMarsRoverImages(
            sol = sol,
            page = page,
            apiKey = Constants.API_KEY
        )
    }

    override suspend fun getAPODByCount(count: Int): MutableList<APODDto> {
        return nasaApi.getAPODByCount(count)
    }


}