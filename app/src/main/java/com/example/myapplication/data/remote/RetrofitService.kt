package com.example.myapplication.data.remote

import com.example.myapplication.domain.entities.Book
import com.example.myapplication.domain.request.LoginRequest
import com.example.myapplication.domain.resposne.LoginResponse
import io.reactivex.rxjava3.core.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitService {

    @GET("/library/books.json")
    fun fetchAllBook(@Query("libraryId") library: String): Single<Map<String, Book>>

    @POST("/auth")
    fun login(@Body loginRequest: LoginRequest): Single<LoginResponse>
}