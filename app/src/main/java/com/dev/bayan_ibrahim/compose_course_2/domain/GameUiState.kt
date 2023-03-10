package com.dev.bayan_ibrahim.compose_course_2.domain

data class GameUiState (
    val scrambledWord: String = "no word",
    val guessedWord: String = "",
    val wordIndex: Int = 0,
    val score: Int = 0,
    val wrongGuess: Boolean = false,
    val gameFinished: Boolean = false,
)