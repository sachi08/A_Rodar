package com.santiagogarciav.a_rodar.ui.start

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.santiagogarciav.a_rodar.R
import com.santiagogarciav.a_rodar.databinding.ActivityStartBinding
import com.santiagogarciav.a_rodar.ui.login.LoginActivity
import com.santiagogarciav.a_rodar.ui.main.MainActivity
import com.santiagogarciav.a_rodar.ui.register.RegisterActivity

class StartActivity : AppCompatActivity() {

    private lateinit var startBinding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startBinding = ActivityStartBinding.inflate(layoutInflater)
        val view = startBinding.root
        setContentView(view)

        startBinding.startLoginButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        startBinding.startRegiserButton.setOnClickListener {
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)
        }
    }
}