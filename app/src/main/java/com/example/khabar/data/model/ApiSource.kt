package com.example.khabar.data.model

import com.example.khabar.data.local.entity.Source
import com.google.gson.annotations.SerializedName

data class ApiSource(
    @SerializedName("id")
    val id: String?,

    @SerializedName("name")
    val name: String?
)

fun ApiSource.apiSourceToSource(): Source {
    return Source(
        id = id,
        name = name
    )
}
