package com.example.myapplication.data.local.db.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import androidx.room.Insert
import androidx.room.Query
import com.example.myapplication.domain.entities.Book

@Dao
interface BookDao {

    @Query("SELECT * FROM books ORDER BY title ASC")
    fun getAllBooks(): LiveData<List<Book>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAllBooks(bookList: List<Book>)

    @Delete
    fun removeBook(book: Book)
}