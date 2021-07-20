package com.example.android.devbyteviewer.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class DatabaseVideo constructor(
    @PrimaryKey
    val url: String,
    val updated : String,
    val title: String,
    val description : String,
    val thumbnail : String
)

fun List<DatabaseVideo>.asDomainModel():List<DevByteVideo>{
    return map {
        DevByteVideo(
            url = it.url,
            updated = it.updated,
            title = it.title,
            description = it.description,
            thumbnail = it.thumbnail
        )
    }
}