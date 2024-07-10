package com.example.antrikshgyan.data.remote.apiservice

import com.example.antrikshgyan.common.Constants
import com.example.antrikshgyan.data.remote.dto.nasa.APODDto
import com.example.antrikshgyan.data.remote.dto.nasa.mars.MarsRoverDto
import retrofit2.http.GET
import retrofit2.http.Query
import java.util.Date

interface APIService {

    //https://api.nasa.gov/planetary/apod?api_key=noMFHR9VTfElRHpLoPVcHaJNZsFU9hvtiIQyDS8m
    @GET("/planetary/apod")
    suspend fun getAPOD(
        @Query("api_key") apiKey: String
    ): APODDto

    //https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?sol=50&page=14&api_key=noMFHR9VTfElRHpLoPVcHaJNZsFU9hvtiIQyDS8m
    @GET("/mars-photos/api/v1/rovers/curiosity/photos")
    suspend fun getMarsRoverImages(
        @Query("sol") sol: Int = 1,
        @Query("page") page: Int = 1,
        @Query("api_key") apiKey: String = Constants.API_KEY
    ) : MarsRoverDto

    //https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=2020-6-3&api_key=noMFHR9VTfElRHpLoPVcHaJNZsFU9hvtiIQyDS8m
    @GET("/mars-photos/api/v1/rovers/curiosity/photos?sol=50&page=14&api_key=noMFHR9VTfElRHpLoPVcHaJNZsFU9hvtiIQyDS8m")
    suspend fun getMarsRoverImagesByDate(
        @Query("earth_date") page: Date = Date(2020-6-3),
        @Query("api_key") apiKey: String = Constants.API_KEY
    ) : MarsRoverDto

}