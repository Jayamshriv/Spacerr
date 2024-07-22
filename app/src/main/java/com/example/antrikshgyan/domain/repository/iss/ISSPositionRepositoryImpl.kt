package com.example.antrikshgyan.domain.repository.iss

import com.example.antrikshgyan.data.remote.apiservice.ISSApiService
import com.example.antrikshgyan.data.remote.dto.iss.ISSPositionDetailDto
import com.example.antrikshgyan.data.remote.dto.iss.ISSPositionDto
import com.example.antrikshgyan.data.repository.iss.ISSPositionRepository
import retrofit2.Response
import javax.inject.Inject

class ISSPositionRepositoryImpl @Inject constructor(
    private val apiService: ISSApiService
) : ISSPositionRepository {

    override suspend fun getISSPosition(units: String): ISSPositionDto {
        return apiService.getISSPosition(units)
    }

    override suspend fun getISSPositionDetail(lat: Double, lng: Double): ISSPositionDetailDto {
        return apiService.getISSPositionDetail(lat, lng)
    }

    override suspend fun getISSTLES(): Response<String> {
        return apiService.getISSTLES()
    }

}