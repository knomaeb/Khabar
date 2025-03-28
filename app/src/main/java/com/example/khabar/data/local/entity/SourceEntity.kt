package com.example.khabar.data.local.entity

import androidx.room.ColumnInfo
import com.example.khabar.data.model.SourceContract
import com.example.khabar.data.remote.model.Source
import com.example.khabar.utils.AppConstants.DEFAULT_SOURCE

data class SourceEntity(
    @ColumnInfo(name = "sourceId")
    override val sourceId: String = DEFAULT_SOURCE,
    @ColumnInfo(name = "sourceName")
    override val sourceName: String = DEFAULT_SOURCE,
): SourceContract

fun SourceEntity.toSource(): Source {
    return Source(
        sourceId = sourceId,
        sourceName = sourceName
    )
}