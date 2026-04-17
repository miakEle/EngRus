package com.example.engrus.presentation

import com.example.engrus.domain.entities.WordCard
import javax.inject.Inject

class WordCardMapperDomainToUi @Inject constructor() {

    fun map(domain: WordCard): WordCardUi {
        return WordCardUi(
            id = domain.id,
            word = domain.word,
            translation = domain.translation,
            attempts = domain.attempts,
            successfulAttempts = domain.successfulAttempts
        )
    }
}