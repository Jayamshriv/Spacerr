package com.example.antrikshgyan.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Contextual

@Parcelize
data class APODModel(
    val explanation: String? ="",
    val media_type: String?="",
    val title: String?="",
    val url: String?="",
    val hdurl : String? =""
) : Parcelable
