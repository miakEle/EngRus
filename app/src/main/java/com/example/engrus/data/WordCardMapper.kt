package com.example.engrus.data

import com.example.engrus.domain.entities.WordCard

class WordCardMapper {

    fun wordCardFromDomainToDB(wordCard: WordCard): WordCardDbModel {
        return WordCardDbModel(
            id = wordCard.id,
            word = wordCard.word,
            translation = wordCard.translation,
            insertTime = wordCard.insertTime,
            attempts = wordCard.attempts,
            successfulAttempts = wordCard.successfulAttempts
        )
    }

    fun wordCardFromDbToDomain(wordCardDbModel: WordCardDbModel): WordCard {
        return WordCard(
            id = wordCardDbModel.id,
            word = wordCardDbModel.word,
            translation = wordCardDbModel.translation,
            insertTime = wordCardDbModel.insertTime,
            attempts = wordCardDbModel.attempts,
            successfulAttempts = wordCardDbModel.successfulAttempts
        )
    }

    fun mapListDbModelToListEntity(list: List<WordCardDbModel>) = list.map {
        wordCardFromDbToDomain(it)
    }


}