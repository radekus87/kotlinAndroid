package com.example.myapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.ui.vm.LoginActivityViewModel
import kotlinx.android.synthetic.main.activity_login.*
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val loginActivityViewModel: LoginActivityViewModel by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initOnLogInButtonListener()
        initDoneListenerOnPasswordEditText()
        initOnSuccessLoginObserver()
    }

    fun initOnLogInButtonListener(){
        logInButton.setOnClickListener {
            onLogInButtonCLick()
        }
    }

    fun onLogInButtonCLick(){
        if(loginEditText.text.isEmpty()){
            Toast.makeText(this, "Podaj login!", Toast.LENGTH_LONG).show()
            return
        }

        if(passwordEditText.text.isEmpty()){
            Toast.makeText(this, "Podaj hasło!", Toast.LENGTH_LONG).show()
            return
        }

        loginActivityViewModel.login(
            loginEditText.text.toString(),
            passwordEditText.text.toString()
        )
    }

    fun initDoneListenerOnPasswordEditText(){
        passwordEditText.setOnEditorActionListener { v, actionId, event ->
            if(actionId == EditorInfo.IME_ACTION_DONE){
                onLogInButtonCLick()
                true
            }
            false
        }
    }

    fun initOnSuccessLoginObserver(){
        loginActivityViewModel.loginSuccess.observe(this, {networkResponseLiveData ->

            if(networkResponseLiveData.isSuccess){
                showMainActivity()
            }else{
                Toast.makeText(
                    this, networkResponseLiveData.msg, Toast.LENGTH_LONG
                ).show()
            }

        })
    }

    fun showMainActivity(){
        val intent: Intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK

        startActivity(intent)
    }

}