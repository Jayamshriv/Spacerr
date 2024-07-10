package com.example.antrikshgyan.domain.model.nasa

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class APODModel(
    val explanation: String? ="",
    val media_type: String?="",
    val title: String?="",
    val url: String?="",
    val hdurl : String? ="",
    val copyright: String?=""
) : Parcelable
