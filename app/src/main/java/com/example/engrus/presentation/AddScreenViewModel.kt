package com.example.engrus.presentation

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engrus.domain.entities.WordCard
import com.example.engrus.domain.repository.WordCardRepository
import com.example.engrus.domain.usecases.AddWordCardUseCase
import com.example.engrus.domain.usecases.GetWordCardsListUseCase
import kotlinx.coroutines.launch
import java.time.LocalDateTime
import javax.inject.Inject

class AddScreenViewModel @Inject constructor(
    private val context: Context,
    private val repository: WordCardRepository
): ViewModel(){

    private val _wordCard = MutableLiveData<WordCard>()
    val wordCard: LiveData<WordCard>
        get() = _wordCard

    @RequiresApi(Build.VERSION_CODES.O)
    fun addWord(word: String, translation: String) {
        if (word.isBlank() || translation.isBlank()) return
        viewModelScope.launch {
            val id = repository.addWordCard(WordCard(0, word, translation, LocalDateTime.now(), LocalDateTime.now().plusMinutes(20),0, 0))
            WordCardWorker.scheduleFirst(context, id)
//            repository.getWordCardsList()
        }
    }

    fun getWordById(id: Long){
        viewModelScope.launch {
            repository.getWordCardById(id)?.let {
                _wordCard.value = it
            }
        }
    }

    fun editWord(word: String, translation: String){
        _wordCard.value?.let {
            viewModelScope.launch {
                val wordCard = it.copy(word = word, translation = translation)
                repository.editWordCard(wordCard)
            }
        }
    }
}