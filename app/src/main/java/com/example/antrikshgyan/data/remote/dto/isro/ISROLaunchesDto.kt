package com.example.antrikshgyan.data.remote.dto.isro

import com.example.antrikshgyan.domain.model.isro.ISROLaunchesModel

data class ISROLaunchesDto(
    val LaunchDate: String,
    val LaunchType: String,
    val Link: String,
    val MissionStatus: String,
    val Name: String,
    val Payload: String,
    val SerialNumber: String,
    val UUID: String
)

fun ISROLaunchesDto.toISROLaunchesModel(): ISROLaunchesModel {
    return ISROLaunchesModel(
        LaunchDate,
        LaunchType,
        Link,
        MissionStatus,
        Name,
        Payload,
    )
}