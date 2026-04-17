package com.example.engrus.domain.usecases

import com.example.engrus.domain.repository.WordCardRepository
import javax.inject.Inject

class GetWordCardByIdUseCase @Inject constructor(private val wordCardRepository: WordCardRepository) {

    suspend operator fun invoke(wordCardId: Long){
        wordCardRepository.getWordCardById(wordCardId)
    }
}