package com.example.engrus.domain.repository

import com.example.engrus.domain.entities.WordCard
import kotlinx.coroutines.flow.Flow

interface WordCardRepository {

    fun addWordCard(wordCard: WordCard)

    fun deleteWordCard(wordCard: WordCard)

    fun editWordCard(wordCard: WordCard)

    fun getWordCardById(wordCardId: Long): WordCard?

    fun getWordCardsList(): Flow<List<WordCard>>


}