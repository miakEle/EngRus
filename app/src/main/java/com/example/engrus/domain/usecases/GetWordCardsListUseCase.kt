package com.example.engrus.domain.usecases

import com.example.engrus.domain.entities.WordCard
import com.example.engrus.domain.repository.WordCardRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetWordCardsListUseCase @Inject constructor(private val wordCardRepository: WordCardRepository) {

    operator fun invoke(): Flow<List<WordCard>>{
        return wordCardRepository.getWordCardsList()
    }
}