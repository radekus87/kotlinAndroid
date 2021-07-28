package com.example.myapplication.ui.activities

import android.content.Intent
import android.os.Bundle
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R
import com.example.myapplication.databinding.ActivityLoginBinding
import com.example.myapplication.ui.vm.LoginActivityViewModel
import org.koin.android.ext.android.inject

class LoginActivity : AppCompatActivity() {

    private val loginActivityViewModel: LoginActivityViewModel by inject()

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initOnLogInButtonListener()
        initDoneListenerOnPasswordEditText()
        initOnSuccessLoginObserver()
    }

    fun initOnLogInButtonListener(){
        binding.logInButton.setOnClickListener {
            onLogInButtonCLick()
        }
    }

    fun onLogInButtonCLick(){
        if(binding.loginEditText.text.isEmpty()){
            Toast.makeText(this, getString(R.string.enter_login), Toast.LENGTH_LONG).show()
            return
        }

        if(binding.passwordEditText.text.isEmpty()){
            Toast.makeText(this, getString(R.string.enter_password), Toast.LENGTH_LONG).show()
            return
        }

        loginActivityViewModel.login(
            binding.loginEditText.text.toString(),
            binding.passwordEditText.text.toString()
        )
    }

    fun initDoneListenerOnPasswordEditText(){
        binding.passwordEditText.setOnEditorActionListener { v, actionId, event ->
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