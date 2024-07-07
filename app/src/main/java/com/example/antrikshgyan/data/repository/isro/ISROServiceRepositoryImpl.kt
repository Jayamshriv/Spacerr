package com.example.antrikshgyan.data.repository.isro

import com.example.antrikshgyan.data.remote.apiservice.IsroServiceApiService
import com.example.antrikshgyan.data.remote.dto.isro.ISROLaunchesDto
import com.example.antrikshgyan.data.remote.dto.isro.ISROSpacecraftDto
import com.example.antrikshgyan.domain.repository.ISROServiceRepository
import retrofit2.Response
import javax.inject.Inject

class ISROServiceRepositoryImpl @Inject constructor(
    private val isroServiceApiService: IsroServiceApiService
) : ISROServiceRepository {
    override suspend fun getISROSpacecraft(): Response<List<ISROSpacecraftDto>> {
        return isroServiceApiService.getSpacecraft()
    }

    override suspend fun getISROLaunches(): Response<List<ISROLaunchesDto>> {
        return isroServiceApiService.getLaunches()
    }
}