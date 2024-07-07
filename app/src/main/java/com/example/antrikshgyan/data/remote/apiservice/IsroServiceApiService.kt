package com.example.antrikshgyan.data.remote.apiservice

import com.example.antrikshgyan.data.remote.dto.isro.ISROSpacecraftDto
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface IsroServiceApiService {

    @GET("/api/spacecraft")
    suspend fun getSpacecraft() : Response<List<ISROSpacecraftDto>>
}