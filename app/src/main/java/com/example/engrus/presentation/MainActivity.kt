package com.example.engrus.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.engrus.WordCardApplication
import com.example.engrus.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by lazy(){
        ViewModelProvider(this, viewModelFactory)[MainActivityViewModel::class.java]
    }

    private val adapter = WordCardAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as WordCardApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        observeWordCardsList()

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(this, AddScreenActivity::class.java)
            startActivity(intent)
        }

    }

    private fun setupRecyclerView(){
        binding.rvwordcards.layoutManager = LinearLayoutManager(this)
        binding.rvwordcards.adapter = adapter
    }

    private fun observeWordCardsList(){
        lifecycleScope.launchWhenCreated {
            viewModel.listOfWordCards.collect { list->
                adapter.submitList(list)
            }
        }
    }
}