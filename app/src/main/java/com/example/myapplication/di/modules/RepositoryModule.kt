package com.example.myapplication.di.modules

import com.example.myapplication.data.repositories.BookRepository
import com.example.myapplication.data.repositories.LoginRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        BookRepository()
    }

    factory {
        LoginRepository()
    }
}