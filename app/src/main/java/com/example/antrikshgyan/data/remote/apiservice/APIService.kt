package com.example.antrikshgyan.data.remote.apiservice

import com.example.antrikshgyan.data.remote.dto.nasa.APODDto
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    //https://api.nasa.gov/planetary/apod?api_key=noMFHR9VTfElRHpLoPVcHaJNZsFU9hvtiIQyDS8m
    @GET("planetary/apod")
    suspend fun getAPOD(@Query("api_key") apiKey: String): APODDto

}