package com.example.myapplication.di.modules

import com.example.myapplication.ui.adapters.BooksAdapter
import com.example.myapplication.ui.vm.BooksFragmentViewModel
import com.example.myapplication.ui.vm.LoginActivityViewModel
import com.example.myapplication.ui.vm.MainActivityViewModel
import org.koin.dsl.module

val appModule = module {
    factory{
        MainActivityViewModel()
    }

    factory{
        LoginActivityViewModel()
    }

    factory {
        BooksFragmentViewModel()
    }

    factory{
        BooksAdapter()
    }
}