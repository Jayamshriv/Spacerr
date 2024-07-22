package com.example.antrikshgyan.data.remote.apiservice

import com.example.antrikshgyan.data.remote.dto.iss.ISSPositionDetailDto
import com.example.antrikshgyan.data.remote.dto.iss.ISSPositionDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ISSApiService {

    //https://api.wheretheiss.at/v1/satellites/25544?units=miles
    @GET("/v1/satellites/25544")
    suspend fun getISSPosition(
        @Query("units") units: String = "json"
    ): ISSPositionDto

    @GET("/v1/coordinates/{lat},{lng}")
    suspend fun getISSPositionDetail(
        @Path("lat") lat: Double,
        @Path("lng") lng: Double
    ): ISSPositionDetailDto

    //https://api.wheretheiss.at/v1/satellites/25544/tles?format=text
    @GET("/v1/satellites/25544/tles?format=text")
    suspend fun getISSTLES() : Response<String>

}
