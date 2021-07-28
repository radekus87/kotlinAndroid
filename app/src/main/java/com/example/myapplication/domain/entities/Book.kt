package com.example.myapplication.domain.entities

import androidx.annotation.NonNull
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "books")
data class Book(
    @PrimaryKey @NonNull val id: Int,
    val title: String,
    val price: Double
)