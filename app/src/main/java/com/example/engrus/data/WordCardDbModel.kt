package com.example.engrus.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.time.LocalDateTime

@Entity(tableName = "table_of_word_cards")
data class WordCardDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0, //id is not defined
    val word: String,
    val translation: String,
    val insertTime: LocalDateTime,
    val nextReviewTime: LocalDateTime,// room 2,5+ -> LocalDate by TypeConverter
    val attempts: Int,
    val successfulAttempts: Int
)