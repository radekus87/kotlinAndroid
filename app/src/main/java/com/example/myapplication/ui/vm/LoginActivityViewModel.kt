package com.example.myapplication.ui.vm

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.data.repositories.LoginRepository
import com.example.myapplication.domain.livedata.NetworkResponseLiveData
import com.example.myapplication.domain.request.LoginRequest
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.kotlin.subscribeBy
import io.reactivex.rxjava3.schedulers.Schedulers
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class LoginActivityViewModel: ViewModel(), KoinComponent {

    private val loginRepository: LoginRepository by inject()

    val loginSuccess: MutableLiveData<NetworkResponseLiveData> = MutableLiveData()

    fun login(login: String, password: String){
        loginRepository.login(LoginRequest(login, password))
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(

            onSuccess = {
                val networkResponseLiveData: NetworkResponseLiveData = NetworkResponseLiveData(true, "")
                loginSuccess.value = networkResponseLiveData
            },
            onError = {

                val networkResponseLiveData: NetworkResponseLiveData = NetworkResponseLiveData(true, "")
//                val networkResponseLiveData: NetworkResponseLiveData = NetworkResponseLiveData(false, it.message)
                loginSuccess.value = networkResponseLiveData
            }
        )
    }

}