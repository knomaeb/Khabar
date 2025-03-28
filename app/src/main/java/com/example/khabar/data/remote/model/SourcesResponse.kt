package com.example.khabar.data.remote.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SourcesResponse(
    @SerialName("status")
    val status: String = "",
    @SerialName("sources")
    val sources: List<Source> = ArrayList(),
)