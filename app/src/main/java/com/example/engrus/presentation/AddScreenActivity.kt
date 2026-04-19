package com.example.engrus.presentation

import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.engrus.WordCardApplication
import com.example.engrus.databinding.ActivityAddScreenBinding
import javax.inject.Inject

class AddScreenActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddScreenBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AddScreenViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as WordCardApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        viewModel = ViewModelProvider(this, viewModelFactory)[AddScreenViewModel::class.java]
        binding = ActivityAddScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.savebutton.setOnClickListener {
            viewModel.addWord(binding.tiwordtext.text.toString(), binding.titransaltetext.text.toString())
            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
            finish()
        }
    }
}