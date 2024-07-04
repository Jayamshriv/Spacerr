package com.example.antrikshgyan.data.repository

import androidx.compose.ui.unit.Constraints
import com.example.antrikshgyan.constants.Constants
import com.example.antrikshgyan.data.remote.apiservice.APIService
import com.example.antrikshgyan.data.remote.dto.APODDto
import com.example.antrikshgyan.domain.repository.NASARepository
import javax.inject.Inject

class NASARepositoryImpl @Inject constructor(
    private val nasaApi : APIService
): NASARepository {
    override suspend fun getAPODData(): APODDto {
        return nasaApi.getAPOD(Constants.API_KEY)
    }
}