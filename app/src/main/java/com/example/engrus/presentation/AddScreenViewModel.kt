package com.example.engrus.presentation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engrus.domain.entities.WordCard
import com.example.engrus.domain.usecases.AddWordCardUseCase
import com.example.engrus.domain.usecases.GetWordCardsListUseCase
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

class AddScreenViewModel @Inject constructor(
    private val context: Context,
    private val addUseCase: AddWordCardUseCase,
    private val getWordCardsListUseCase: GetWordCardsListUseCase
): ViewModel(){

    @RequiresApi(Build.VERSION_CODES.O)
    fun addWord(word: String, translation: String) {
        if (word.isBlank() || translation.isBlank()) return
        viewModelScope.launch {
            val id = addUseCase(WordCard(0, word, translation, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20),0, 0))
            WordCardWorker.scheduleFirst(context, id)
            getWordCardsListUseCase
        }
    }
}