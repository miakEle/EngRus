package com.example.engrus.domain.usecases

import com.example.engrus.domain.entities.WordCard
import com.example.engrus.domain.repository.WordCardRepository

class AddWordCardUseCase (private val wordCardRepository: WordCardRepository){

    operator fun invoke(wordCard: WordCard){
        wordCardRepository.addWordCard(wordCard)
    }

}