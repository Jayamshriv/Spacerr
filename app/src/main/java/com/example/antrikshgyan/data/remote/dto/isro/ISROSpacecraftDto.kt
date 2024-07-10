package com.example.antrikshgyan.data.remote.dto.isro

import com.example.antrikshgyan.domain.model.isro.ISROSpaceCraftModel

data class ISROSpacecraftDto(
    val _id: String,
    val application: String,
    val launchDate: String,
    val launchVehicle: String,
    val link: String,
    val missionStatus: String,
    val name: String,
    val orbitType: String,
    val serialNumber: String,
    val updatedAt: String?
)

fun ISROSpacecraftDto.toISROSpacecraftModel(): ISROSpaceCraftModel {
    return ISROSpaceCraftModel(
        application,
        launchDate,
        launchVehicle,
        link,
        missionStatus,
        name,
        orbitType,
    )
}

