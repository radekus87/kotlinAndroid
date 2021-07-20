package com.example.myapplication.data.repositories

import androidx.lifecycle.LiveData
import com.example.myapplication.data.local.db.dao.BookDao
import com.example.myapplication.data.remote.RetrofitService
import com.example.myapplication.domain.model.BookModel
import com.example.myapplication.domain.resposne.GetBooksReposne
import io.reactivex.rxjava3.core.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BookRepository: KoinComponent {

    val bookDao: BookDao by inject()
    val retrofitService: RetrofitService by inject()

    fun fetchAllBooks(libraryId: String): Single<Map<String,BookModel>> {
        return retrofitService.fetchAllBook(libraryId)
    }

    fun getAllBooks(): LiveData<List<BookModel>>{
        return bookDao.getAllBooks()
    }


    fun insertAllBooks(bookModelList: List<BookModel>){
        bookDao.insertAllBooks(bookModelList)
    }

    fun removeBook(bookModel: BookModel){
        bookDao.removeBook(bookModel)
    }
}