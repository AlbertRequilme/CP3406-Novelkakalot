package com.example.cp3406_a1_albertrequilme.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val title: String,
    val author: String,
    val coverUrl: String? = null,
    val progress: Float = 0f,
    val rating: Float = 0f,
    val review: String? = null
)