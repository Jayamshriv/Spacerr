package com.example.antrikshgyan.domain.repository

import com.example.antrikshgyan.data.remote.dto.isro.ISROSpacecraftDto
import retrofit2.Response

interface ISROServiceRepository {

    suspend fun getISROSpacecraft() : Response<List<ISROSpacecraftDto>>
}