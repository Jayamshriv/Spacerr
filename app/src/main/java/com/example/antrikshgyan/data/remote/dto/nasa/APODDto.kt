package com.example.antrikshgyan.data.remote.dto.nasa

import com.example.antrikshgyan.domain.model.APODModel

data class APODDto(
    val date: String?,
    val explanation: String?,
    val media_type: String?,
    val service_version: String?,
    val title: String?,
    val hdurl : String?,
    val url: String?,
    val copyright : String?
)

fun APODDto.toAPODModel() : APODModel{
    return APODModel(
        explanation,media_type,title,url, hdurl, copyright
    )
}

