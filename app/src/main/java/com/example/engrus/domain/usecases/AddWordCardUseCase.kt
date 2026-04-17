package com.example.engrus.domain.usecases

import com.example.engrus.domain.entities.WordCard
import com.example.engrus.domain.repository.WordCardRepository
import javax.inject.Inject

class AddWordCardUseCase @Inject constructor(private val wordCardRepository: WordCardRepository){

    suspend operator fun invoke(wordCard: WordCard){
        wordCardRepository.addWordCard(wordCard)
    }

}