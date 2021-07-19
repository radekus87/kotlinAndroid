package com.example.myapplication.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.myapplication.data.local.db.dao.BookDao
import com.example.myapplication.domain.model.BookModel

@Database(version = 1, entities = [BookModel::class], exportSchema = false)
abstract class MainDatabase: RoomDatabase() {
    abstract fun bookDao(): BookDao
}