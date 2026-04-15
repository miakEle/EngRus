package com.example.engrus.data

import com.example.engrus.domain.entities.WordCard
import com.example.engrus.domain.repository.WordCardRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WordCardRepositoryImp (
    private val dao: WordCardDao,
    private  val mapper: WordCardMapper
    ): WordCardRepository {

    override fun addWordCard(wordCard: WordCard) {
        dao.addWordCard(mapper.wordCardFromDomainToDB(wordCard))
    }

    override fun deleteWordCard(wordCard: WordCard) {
        dao.deleteWordCard(mapper.wordCardFromDomainToDB(wordCard))
    }

    override fun editWordCard(wordCard: WordCard) {
        dao.editWordCard(mapper.wordCardFromDomainToDB(wordCard))
    }

    override fun getWordCardById(wordCardId: Long): WordCard? {
        return mapper.wordCardFromDbToDomain(dao.getWordCardById(wordCardId))
    }

    override fun getWordCardsList(): Flow<List<WordCard>> {
        return dao.getWordCardsList().map { list -> list.map { mapper.wordCardFromDbToDomain(it)!! }}
    }
}