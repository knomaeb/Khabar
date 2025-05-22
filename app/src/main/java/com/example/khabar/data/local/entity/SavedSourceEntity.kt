package com.example.khabar.data.local.entity

import androidx.room.ColumnInfo
import com.example.khabar.core.utils.AppConstants.DEFAULT_SOURCE

data class SavedSourceEntity(
    @ColumnInfo(name = "sourceId")
    val id: String? = DEFAULT_SOURCE,

    @ColumnInfo(name = "sourceName")
    val name: String? = DEFAULT_SOURCE,
)

fun SavedSourceEntity.savedSourceEntityToSource(): Source {
    return Source(
        id = id,
        name = name
    )
}