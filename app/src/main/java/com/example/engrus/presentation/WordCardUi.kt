package com.example.engrus.presentation

data class WordCardUi(
    val id: Long,
    val word: String,
    val translation: String,
    val attempts: Int,
    val successfulAttempts: Int
)