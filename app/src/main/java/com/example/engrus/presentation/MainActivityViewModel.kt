package com.example.engrus.presentation

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engrus.domain.entities.WordCard
import com.example.engrus.domain.repository.WordCardRepository
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val repository: WordCardRepository,
    private val uiMapperDomainToUi: WordCardUiMapper
) : ViewModel() {

    val listOfWordCards = repository.getWordCardsList()
        .map { list ->
            Log.d("TEST", "Domain list size = ${list.size}")
            list
                .map { uiMapperDomainToUi.mapFromDomainToUi(it) }
                .sortedByDescending { card ->
                if (card.attempts == 0) 0.0
                else card.successfulAttempts.toDouble() / card.attempts
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun deleteWordCard(wordCardUi: WordCardUi){
        viewModelScope.launch {
            repository.deleteWordCard(uiMapperDomainToUi.mapFromUiToDomain(wordCardUi))
        }
    }
}