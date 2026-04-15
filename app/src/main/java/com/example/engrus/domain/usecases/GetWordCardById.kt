package com.example.engrus.domain.usecases

import com.example.engrus.domain.repository.WordCardRepository
import javax.inject.Inject

class GetWordCardById @Inject constructor(private val wordCardRepository: WordCardRepository) {

    operator fun invoke(wordCardId: Long){
        wordCardRepository.getWordCardById(wordCardId)
    }
}