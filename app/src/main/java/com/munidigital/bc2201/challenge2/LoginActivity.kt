package com.munidigital.bc2201.challenge2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.button.MaterialButton
import com.google.firebase.auth.FirebaseUser

//test1@munidigital.com
//bc2201
class LoginActivity : AppCompatActivity() {

    companion object{
        val KEY = "Key"
    }

    lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        val etMail = findViewById<EditText>(R.id.et_mail)
        val etPass = findViewById<EditText>(R.id.et_pass)
        val btnLogin = findViewById<Button>(R.id.btn_login)

        if (intent.getBooleanExtra(KEY,false))
            viewModel.logout()

        viewModel.state.observe(this) { state ->
            when {
                (!state.loginError && state.user != null) -> { // Usuario logueado
                    onLogged(state.user) // Loguea la info del usuario...
                    startActivity(Intent(this, MainActivity::class.java))
                    finish()
                }
                (state.loginError) -> { // El usuario intentó iniciar sesión y falló
                    Toast.makeText(this, "Invalid user credentials.", Toast.LENGTH_SHORT).show()
                }
            }
        }


        btnLogin.setOnClickListener {
                val enviado = viewModel.login(etMail.text.toString(), etPass.text.toString())
                if (!enviado) Toast.makeText(this,"Ingrese usuario y contraseña", Toast.LENGTH_SHORT).show()
        }

    }

    private fun onLogged(user: FirebaseUser) {
        user.apply {
            email?.let { Log.d("login", it) }
            isEmailVerified.let { Log.d("login", it.toString()) }
            uid.let { Log.d("login", it) }
        }
    }

}