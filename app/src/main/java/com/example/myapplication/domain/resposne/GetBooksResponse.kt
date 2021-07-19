package com.example.myapplication.domain.resposne

import com.example.myapplication.domain.model.BookModel

data class GetBooksReposne(
    val book1: BookModel,
    val book2: BookModel,
    val book3: BookModel
)