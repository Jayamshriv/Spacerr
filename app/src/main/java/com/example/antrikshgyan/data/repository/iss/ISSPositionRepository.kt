package com.example.antrikshgyan.data.repository.iss

import com.example.antrikshgyan.data.remote.dto.iss.ISSPositionDetailDto
import com.example.antrikshgyan.data.remote.dto.iss.ISSPositionDto
import retrofit2.Response

interface ISSPositionRepository {

    suspend fun getISSPosition(units : String) : ISSPositionDto

    suspend fun getISSPositionDetail(lat : Double, lng : Double) : ISSPositionDetailDto

    suspend fun getISSTLES() : Response<String>

}