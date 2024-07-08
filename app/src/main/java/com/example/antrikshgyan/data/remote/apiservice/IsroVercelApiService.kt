package com.example.antrikshgyan.data.remote.apiservice

import com.example.antrikshgyan.data.remote.dto.isro.ISROCentresDto
import com.example.antrikshgyan.data.remote.dto.isro.ISROCusSatellitesDto
import retrofit2.http.GET

interface IsroVercelApiService {

    @GET("/api/customer_satellites")
    suspend fun getCusSatellites() : ISROCusSatellitesDto

    @GET("/api/centres")
    suspend fun getCentres() : ISROCentresDto

}