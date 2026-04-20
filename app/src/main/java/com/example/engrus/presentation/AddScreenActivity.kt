package com.example.engrus.presentation

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.example.engrus.WordCardApplication
import com.example.engrus.databinding.ActivityAddScreenBinding
import javax.inject.Inject

class AddScreenActivity: AppCompatActivity() {

    private lateinit var binding: ActivityAddScreenBinding

    private var screenMode = MODE_UNKNOWN
    private var wordCardId = 0L

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    private lateinit var viewModel: AddScreenViewModel


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        (application as WordCardApplication).appComponent.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        parseIntent()

        viewModel = ViewModelProvider(this, viewModelFactory)[AddScreenViewModel::class.java]
        binding = ActivityAddScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchRightMode()

//        binding.savebutton.setOnClickListener {
//            viewModel.addWord(binding.tiwordtext.text.toString(), binding.titransaltetext.text.toString())
//            Toast.makeText(this,"Success", Toast.LENGTH_SHORT).show()
//            finish()
//        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
            else -> throw RuntimeException("Unknown screen mode $screenMode")
        }

    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun launchAddMode(){
        binding.savebutton.setOnClickListener {
            viewModel.addWord(binding.tiwordtext.text.toString(), binding.titransaltetext.text.toString())
            finish()
        }
    }

    private fun launchEditMode(){
        viewModel.getWordById(wordCardId)
        viewModel.wordCard.observe(this){
            binding.tiwordtext.setText(it.word)
            binding.titransaltetext.setText(it.translation.toString())
        }
        binding.savebutton.setOnClickListener {
            viewModel.editWord(binding.tiwordtext.text.toString(),binding.titransaltetext.text.toString())
            finish()

        }
    }


    fun parseIntent() {
        if (!intent.hasExtra(EXTRA_SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = intent.getStringExtra(EXTRA_SCREEN_MODE)
        if (mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("Unknown screen mode $mode")
        }
        screenMode = mode
        if (screenMode == MODE_EDIT) {
            if (!intent.hasExtra(EXTRA_SHOP_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            wordCardId = intent.getLongExtra(EXTRA_SHOP_ITEM_ID, 0)
        }

    }

    companion object {
        private const val EXTRA_SCREEN_MODE = "extra_mode"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_ADD = "mode_add"
        private const val EXTRA_SHOP_ITEM_ID = "extra_shop_item_id"

        private const val MODE_UNKNOWN = ""

        fun newIntentAdd(context: Context): Intent {
            val intent = Intent(context, AddScreenActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_ADD)
            return intent
        }

        fun newIntentEdit(context: Context, shopItemId: Long): Intent {
            val intent = Intent(context, AddScreenActivity::class.java)
            intent.putExtra(EXTRA_SCREEN_MODE, MODE_EDIT)
            intent.putExtra(EXTRA_SHOP_ITEM_ID, shopItemId)
            return intent
        }


    }

}