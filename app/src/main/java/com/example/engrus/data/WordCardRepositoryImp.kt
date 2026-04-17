package com.example.engrus.data

import android.util.Log
import com.example.engrus.domain.entities.WordCard
import com.example.engrus.domain.repository.WordCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class WordCardRepositoryImp @Inject constructor(
    private val dao: WordCardDao,
    private  val mapper: WordCardMapper
    ): WordCardRepository {

    override suspend fun addWordCard(wordCard: WordCard) {
        Log.d("DB_TEST", "Adding: $wordCard")
        dao.addWordCard(mapper.wordCardFromDomainToDB(wordCard))
    }

    override suspend fun deleteWordCard(wordCard: WordCard) {
        dao.deleteWordCard(mapper.wordCardFromDomainToDB(wordCard))
    }

    override suspend fun editWordCard(wordCard: WordCard) {
        dao.editWordCard(mapper.wordCardFromDomainToDB(wordCard))
    }

    override suspend fun getWordCardById(wordCardId: Long): WordCard? {
        return mapper.wordCardFromDbToDomain(dao.getWordCardById(wordCardId))
    }

    override fun getWordCardsList(): Flow<List<WordCard>> {
        return dao.getWordCardsList().map { list -> list.map { mapper.wordCardFromDbToDomain(it)!! }}
    }
}