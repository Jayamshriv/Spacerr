package com.example.antrikshgyan.domain.repository.nasa

import com.example.antrikshgyan.data.remote.dto.nasa.APODDto
import com.example.antrikshgyan.data.remote.dto.nasa.mars.MarsRoverDto

interface NASARepository {

    suspend fun getAPODData(): APODDto

    suspend fun getMarsRoversImages(
        sol: Int,
        page: Int
    ): MarsRoverDto

    suspend fun getAPODByCount(
        count : Int
    ) : MutableList<APODDto>
}