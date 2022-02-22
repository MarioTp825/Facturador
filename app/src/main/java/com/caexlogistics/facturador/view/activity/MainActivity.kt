package com.caexlogistics.facturador.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController
import com.caexlogistics.facturador.R
import com.caexlogistics.facturador.databinding.ActivityLoginBinding
import com.caexlogistics.facturador.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val controller = findNavController(R.id.fragmentContainerView)
        binding.bnv.setupWithNavController(controller)
    }
}