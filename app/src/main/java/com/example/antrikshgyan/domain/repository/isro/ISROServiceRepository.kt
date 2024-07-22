package com.example.antrikshgyan.domain.repository.isro

import com.example.antrikshgyan.data.remote.dto.isro.ISROLaunchesDto
import com.example.antrikshgyan.data.remote.dto.isro.ISROSpacecraftDto
import retrofit2.Response

interface ISROServiceRepository {

    suspend fun getISROSpacecraft() : Response<List<ISROSpacecraftDto>>

    suspend fun getISROLaunches() :Response<List<ISROLaunchesDto>>
}