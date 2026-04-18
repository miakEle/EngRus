package com.example.engrus.domain.entities

import java.time.LocalDate
import java.time.LocalDateTime

data class WordCard(
    val id: Long = 0, //id is not defined
    val word: String,
    val translation: String,
    val insertTime: LocalDateTime,
    val nextReviewTime: LocalDateTime,
    val attempts: Int,
    val successfulAttempts: Int
) {

}