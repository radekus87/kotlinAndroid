package com.example.myapplication.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repositories.BookRepository
import com.example.myapplication.domain.entities.Book
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BooksFragmentViewModel: ViewModel(), KoinComponent{

    private val bookRepository: BookRepository by inject()

    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun getAllbooks(): LiveData<List<Book>>{
        return bookRepository.getAllBooks()
    }

    fun fetchAllBooks(libraryId: String){

        bookRepository.fetchAllBooks(libraryId)
            .subscribeOn(io())
            .observeOn(io())
            .subscribeBy(
                onSuccess = {
                    bookRepository.insertAllBooks(convertMapToArray(it))
                },
                onError = {

                    viewModelScope.launch(Dispatchers.Main){
                        isSuccess.value = false
                    }

                }
            )
    }

    fun convertMapToArray(it: Map<String, Book>): ArrayList<Book>{
        val books: ArrayList<Book> = ArrayList()

        for ((key, value) in it) {
            books.add(value)
        }

        return books
    }

}