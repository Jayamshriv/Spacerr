package com.example.antrikshgyan.domain.repository

import com.example.antrikshgyan.data.remote.dto.APODDto

interface NASARepository {

    suspend fun getAPODData() : APODDto

}