package com.example.myapplication.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.domain.model.BookModel

@Dao
interface BookDao {

    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAllBooks(): LiveData<List<BookModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(bookModelList: List<BookModel>)

    @Delete
    fun removeBook(bookModel: BookModel)
}