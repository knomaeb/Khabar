package com.example.khabar.data.remote.model

import com.example.khabar.data.local.entity.SourceEntity
import com.example.khabar.data.model.SourceContract
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import com.example.khabar.utils.AppConstants.DEFAULT_SOURCE

@Serializable
data class Source(
    @SerialName("id")
    override val sourceId: String = DEFAULT_SOURCE,
    @SerialName("name")
    override val sourceName: String = DEFAULT_SOURCE
): SourceContract

fun Source.toSourceEntity(): SourceEntity {
    return SourceEntity(
        sourceId = sourceId,
        sourceName = sourceName
    )
}
