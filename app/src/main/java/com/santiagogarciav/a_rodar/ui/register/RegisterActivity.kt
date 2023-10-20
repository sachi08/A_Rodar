package com.santiagogarciav.a_rodar.ui.register

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.santiagogarciav.a_rodar.databinding.ActivityRegisterBinding
import com.santiagogarciav.a_rodar.ui.register.RegisterViewModel

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

        registerBinding.registerButton.setOnClickListener {
            val email = registerBinding.registerEmailEditText.text.toString()
            val password = registerBinding.registerPasswordEditText.text.toString()
            val repeatPassword = registerBinding.registerRepeatPasswordEditText.text.toString()


            registerViewModel.validateFields(email, password, repeatPassword)
        }

    }

    private fun showErrorMsg(msg:String?){
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show()
    }
}


