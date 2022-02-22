package com.caexlogistics.facturador

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.caexlogistics.facturador.databinding.ActivityLoginBinding
import com.caexlogistics.facturador.view.activity.MainActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)

        binding.btnLogin.setOnClickListener {
            login()
        }

        setContentView(binding.root)
    }

    private fun login() {
        if(!identified()) {
            Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show()
            return
        }

        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    private fun identified(): Boolean {
        binding.let {
            if (it.etUsername.text.isBlank() && it.etPassword.text.isBlank())
                return false
        }
        return true
    }
}