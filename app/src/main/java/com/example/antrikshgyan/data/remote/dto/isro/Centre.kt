package com.example.antrikshgyan.data.remote.dto.isro

import com.example.antrikshgyan.domain.model.CentreModel

data class Centre(
    val Place: String,
    val State: String,
    val id: Int,
    val name: String
)

fun Centre.toCentreModel() : CentreModel {
    return CentreModel(
        Place,
    State,
    name,
    )
}