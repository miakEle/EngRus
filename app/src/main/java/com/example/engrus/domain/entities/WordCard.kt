package com.example.engrus.domain.entities

import java.time.LocalTime

data class WordCard(
    val id: Long = 0, //id is not defined
    val word: String,
    val translation: String,
    val insertTime: LocalTime,
    val attempts: Int,
    val successfulAttempts: Int
){

}