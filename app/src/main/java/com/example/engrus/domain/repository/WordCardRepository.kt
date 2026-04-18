package com.example.engrus.domain.repository

import com.example.engrus.domain.entities.WordCard
import kotlinx.coroutines.flow.Flow

interface WordCardRepository {

    suspend fun addWordCard(wordCard: WordCard): Long

    suspend fun deleteWordCard(wordCard: WordCard)

    suspend fun editWordCard(wordCard: WordCard)

    suspend fun getWordCardById(wordCardId: Long): WordCard?

    fun getWordCardsList(): Flow<List<WordCard>>


}