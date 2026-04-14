package com.example.engrus.domain.usecases

import com.example.engrus.domain.entities.WordCard
import com.example.engrus.domain.repository.WordCardRepository
import kotlinx.coroutines.flow.Flow

class GetWordCardsList(private val wordCardRepository: WordCardRepository) {

    operator fun invoke(): Flow<List<WordCard>>{
        return wordCardRepository.getWordCardsList()
    }
}