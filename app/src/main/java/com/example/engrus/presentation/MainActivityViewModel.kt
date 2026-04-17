package com.example.engrus.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.engrus.domain.usecases.GetWordCardsListUseCase
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import javax.inject.Inject

class MainActivityViewModel @Inject constructor(
    private val getWordCardsListUseCase: GetWordCardsListUseCase,
    private val uiMapperDomainToUi: WordCardMapperDomainToUi
) : ViewModel() {

    val listOfWordCards = getWordCardsListUseCase.invoke()
        .map { list ->
            list
                .map { uiMapperDomainToUi.map(it) }
                .sortedByDescending { card ->
                if (card.attempts == 0) 0.0
                else card.successfulAttempts.toDouble() / card.attempts
            }
        }
        .stateIn(viewModelScope, SharingStarted.Lazily, emptyList())
}