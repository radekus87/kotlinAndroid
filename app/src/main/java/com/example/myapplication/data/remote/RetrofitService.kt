package com.example.myapplication.data.remote

import com.example.myapplication.domain.resposne.GetBooksReposne
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface RetrofitService {

    @GET("/library/books.json")
    fun fetchAllBook(@Query("libraryId") library: String): Single<GetBooksReposne>
}