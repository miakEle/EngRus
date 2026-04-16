package com.example.engrus.presentation

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.engrus.databinding.ActivityAddScreenBinding
import com.example.engrus.databinding.ActivityMainBinding

class AddScreenActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddScreenBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAddScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}