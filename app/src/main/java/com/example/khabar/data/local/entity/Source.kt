package com.example.khabar.data.local.entity

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("sourceId")
    val id: String?,

    @SerializedName("sourceName")
    val name: String?
)

fun Source.sourceToSavedSourceEntity(): SavedSourceEntity {
    return SavedSourceEntity(
        id = id,
        name = name
    )
}