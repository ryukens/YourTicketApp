package com.moralesjuan.yourticketapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        button_Login.setOnClickListener() {
            startActivity(Intent(this, PrincipalActivity::class.java))
            finish()
        }

        button_Google.setOnClickListener() {
            startActivity(Intent(this, RegistroActivity::class.java))
        }

        button_registrarse.setOnClickListener() {
            startActivity(Intent(this, RegistroActivity::class.java))
        }
    }
}