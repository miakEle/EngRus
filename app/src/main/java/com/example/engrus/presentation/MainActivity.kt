package com.example.engrus.presentation

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.engrus.WordCardApplication
import com.example.engrus.databinding.ActivityMainBinding
import kotlinx.coroutines.coroutineScope
import javax.inject.Inject
import kotlin.collections.get

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
        setupSwipeListener(binding.rvwordcards)

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

    private fun setupSwipeListener(rvShopList: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {

            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = adapter.currentList[viewHolder.adapterPosition]
                viewModel.deleteWordCard(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(rvShopList)
    }


}