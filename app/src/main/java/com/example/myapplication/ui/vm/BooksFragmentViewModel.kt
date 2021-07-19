package com.example.myapplication.ui.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.data.repositories.BookRepository
import com.example.myapplication.domain.model.BookModel
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers.io
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class BooksFragmentViewModel: ViewModel(), KoinComponent{

    private val bookRepository: BookRepository by inject()

    val isSuccess: MutableLiveData<Boolean> = MutableLiveData()

    fun getAllbooks(): LiveData<List<BookModel>>{
        return bookRepository.getAllBooks()
    }

    fun fetchAllBooks(libraryId: String){

        bookRepository.fetchAllBooks(libraryId)
            .subscribeOn(io())
            .observeOn(io())
            .subscribeBy(
                onSuccess = {

                    val bookList: ArrayList<BookModel> = ArrayList()
                    bookList.add(it.book1)
                    bookList.add(it.book2)
                    bookList.add(it.book3)

                    bookRepository.insertAllBooks(bookList)
                },
                onError = {

                    viewModelScope.launch(Dispatchers.Main){
                        isSuccess.value = false
                    }

                }
            )
    }
}