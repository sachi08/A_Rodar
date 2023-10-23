package com.santiagogarciav.a_rodar.ui.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.santiagogarciav.a_rodar.databinding.ActivityLoginBinding
import com.santiagogarciav.a_rodar.ui.login.LoginViewModel
import com.santiagogarciav.a_rodar.ui.main.MainActivity
import com.santiagogarciav.a_rodar.ui.navdrawer.NavigationDrawerActivity
import com.santiagogarciav.a_rodar.ui.register.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginBinding: ActivityLoginBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]
        val view = loginBinding.root
        setContentView(view)

        loginViewModel.errorMessage.observe(this){msg-> //para fragment donde va el this se pone viewLifeCycleOwner
            showErrorMsg(msg)
        }

        loginBinding.loginButton.setOnClickListener {
            val email = loginBinding.loginEmailEditText.text.toString()
            val password = loginBinding.loginPasswordEditText.text.toString()
            val flag = loginViewModel.validateFields(email, password)
            if(flag){
                val intent = Intent(this, NavigationDrawerActivity::class.java)
                startActivity(intent)
            }
        }
        loginBinding.loginRegisterButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }



//        val email = Observer<Double>{correo->
//            loginBinding.loginEmailEditText.setText(correo)
//        }
//        loginViewModel.email.observe(this, email)

    }

    private fun showErrorMsg(msg: String?) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}

