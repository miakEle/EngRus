package com.example.engrus.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.engrus.domain.entities.WordCard
import kotlinx.coroutines.flow.Flow

@Dao
interface WordCardDao {

    @Insert
    suspend fun addWordCard(wordCard: WordCardDbModel)

    @Delete
    suspend fun deleteWordCard(wordCard: WordCardDbModel)

    @Update
    suspend fun editWordCard(wordCard: WordCardDbModel)

    @Query("SELECT * FROM table_of_word_cards WHERE id = :wordCardId LIMIT 1")
    suspend fun getWordCardById(wordCardId: Long): WordCardDbModel?

    @Query("SELECT * FROM table_of_word_cards ORDER BY successfulAttempts DESC")
    fun getWordCardsList(): Flow<List<WordCardDbModel>>
}