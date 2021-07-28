package com.example.myapplication.data.repositories

import com.example.myapplication.data.remote.RetrofitService
import com.example.myapplication.domain.request.LoginRequest
import com.example.myapplication.domain.resposne.LoginResponse
import io.reactivex.rxjava3.core.Single
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginRepository: KoinComponent {

    private val retrofitService: RetrofitService by inject()

    fun login(loginRequest: LoginRequest): Single<LoginResponse>{
        return retrofitService.login(loginRequest)
    }

}