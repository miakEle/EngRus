package com.example.engrus.presentation

import java.time.LocalDateTime

data class WordCardUi(
    val id: Long,
    val word: String,
    val translation: String,
    val insertTime: LocalDateTime,
    val nextReviewTime: LocalDateTime,
    val attempts: Int,
    val successfulAttempts: Int
)