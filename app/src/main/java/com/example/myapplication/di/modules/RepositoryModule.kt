package com.example.myapplication.di.modules

import com.example.myapplication.data.repositories.BookRepository
import org.koin.dsl.module

val repositoryModule = module {
    factory {
        BookRepository()
    }
}