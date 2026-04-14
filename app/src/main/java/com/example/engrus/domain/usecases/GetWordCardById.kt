package com.example.engrus.domain.usecases

import com.example.engrus.domain.repository.WordCardRepository

class GetWordCardById(private val wordCardRepository: WordCardRepository) {

    operator fun invoke(wordCardId: Long){
        wordCardRepository.getWordCardById(wordCardId)
    }
}