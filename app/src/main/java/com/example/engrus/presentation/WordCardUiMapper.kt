package com.example.engrus.presentation

import com.example.engrus.domain.entities.WordCard
import javax.inject.Inject

class WordCardUiMapper @Inject constructor() {

    fun mapFromDomainToUi(domain: WordCard): WordCardUi {
        return WordCardUi(
            id = domain.id,
            word = domain.word,
            translation = domain.translation,
            insertTime = domain.insertTime,
            nextReviewTime = domain.nextReviewTime,
            attempts = domain.attempts,
            successfulAttempts = domain.successfulAttempts
        )
    }

    fun mapFromUiToDomain(ui: WordCardUi): WordCard {
        return WordCard(
            id = ui.id,
            word = ui.word,
            translation = ui.translation,
            insertTime = ui.insertTime,
            nextReviewTime = ui.nextReviewTime,
            attempts = ui.attempts,
            successfulAttempts = ui.successfulAttempts
        )
    }
}