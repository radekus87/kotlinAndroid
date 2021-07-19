package com.example.myapplication.di.modules

import com.example.myapplication.data.remote.RetrofitService
import com.example.myapplication.utils.Constants.BASE_URL
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single{
        provideRetrofit()
    }

    single{
        provideRetrofitService(get())
    }
}

fun provideRetrofit(): Retrofit {
    return Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
        .build()
}

fun provideRetrofitService(retrofit: Retrofit): RetrofitService {
    return retrofit.create(RetrofitService::class.java)
}