package com.example.engrus.data

import com.example.engrus.domain.entities.WordCard
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class WordCardMapper @Inject constructor(){

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

    fun wordCardFromDbToDomain(wordCardDbModel: WordCardDbModel?): WordCard? {
        return wordCardDbModel?.let {
            WordCard(
                id = wordCardDbModel.id,
                word = wordCardDbModel.word,
                translation = wordCardDbModel.translation,
                insertTime = wordCardDbModel.insertTime,
                attempts = wordCardDbModel.attempts,
                successfulAttempts = wordCardDbModel.successfulAttempts)

        }
    }

//    fun mapListDbModelToListEntity(list: Flow<List<WordCardDbModel>>) = list.map {
//        wordCardFromDbToDomain(it)
//    }


}