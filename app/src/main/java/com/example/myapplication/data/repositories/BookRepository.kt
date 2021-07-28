package com.example.myapplication.data.repositories

import androidx.lifecycle.LiveData
import com.example.myapplication.data.local.db.dao.BookDao
import com.example.myapplication.data.remote.RetrofitService
import com.example.myapplication.domain.entities.Book
import io.reactivex.rxjava3.core.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookRepository: KoinComponent {

    val bookDao: BookDao by inject()
    val retrofitService: RetrofitService by inject()

    fun fetchAllBooks(libraryId: String): Single<Map<String,Book>> {
        return retrofitService.fetchAllBook(libraryId)
    }

    fun getAllBooks(): LiveData<List<Book>>{
        return bookDao.getAllBooks()
    }


    fun insertAllBooks(bookList: List<Book>){
        bookDao.insertAllBooks(bookList)
    }

    fun removeBook(book: Book){
        bookDao.removeBook(book)
    }
}