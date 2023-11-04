package com.santiagogarciav.a_rodar.ui.register

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.santiagogarciav.a_rodar.ui.main.MainActivity
import com.santiagogarciav.a_rodar.databinding.ActivityRegisterBinding
import com.santiagogarciav.a_rodar.ui.login.LoginActivity
import com.santiagogarciav.a_rodar.ui.navdrawer.NavigationDrawerActivity

class RegisterActivity : AppCompatActivity() {

    private lateinit var registerBinding: ActivityRegisterBinding
    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        registerViewModel = ViewModelProvider(this)[RegisterViewModel::class.java]
        val view = registerBinding.root
        setContentView(view)

        registerViewModel.errorMessage.observe(this){msg-> //para fragment donde va el this se pone viewLifeCycleOwner
            showErrorMsg(msg)
        }

        registerViewModel.registerSuccess.observe( this){
            onBackPressedDispatcher.onBackPressed()
        }

        registerBinding.registerButton.setOnClickListener {
            val email = registerBinding.registerEmailEditText.text.toString()
            val password = registerBinding.registerPasswordEditText.text.toString()
            val repeatPassword = registerBinding.registerRepeatPasswordEditText.text.toString()
            registerViewModel.validateFields(email, password, repeatPassword)
//            val flag = registerViewModel.validateFields(email, password, repeatPassword)
//            if(flag){
//                val intent = Intent(this, NavigationDrawerActivity::class.java)
//                startActivity(intent)
//            }
        }

        registerBinding.registerLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

    }

    private fun showErrorMsg(msg:String?){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}


