package com.santiagogarciav.a_rodar.ui.login

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.santiagogarciav.a_rodar.databinding.ActivityLoginBinding
import com.santiagogarciav.a_rodar.ui.login.LoginViewModel

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        val view = loginBinding.root
        setContentView(view)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

//        val email = Observer<Double>{correo->
//            loginBinding.loginEmailEditText.setText(correo)
//        }
//        loginViewModel.email.observe(this, email)

    }
}