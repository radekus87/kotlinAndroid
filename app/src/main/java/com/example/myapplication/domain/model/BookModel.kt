package com.example.myapplication.domain.model

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class BookModel(
    @PrimaryKey @NonNull val id: Int,
    val title: String,
    val price: Double
)