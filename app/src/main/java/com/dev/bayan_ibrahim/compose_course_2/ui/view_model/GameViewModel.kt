package com.dev.bayan_ibrahim.compose_course_2.ui.view_model

import android.util.Log
import androidx.lifecycle.ViewModel
import com.dev.bayan_ibrahim.compose_course_2.data.MAX_NO_OF_WORDS
import com.dev.bayan_ibrahim.compose_course_2.data.SCORE_INCREASE
import com.dev.bayan_ibrahim.compose_course_2.data.allWords
import com.dev.bayan_ibrahim.compose_course_2.domain.GameUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

const val TAG = "GameViewModel"
class GameViewModel: ViewModel() {
    private val _gameUiState = MutableStateFlow<GameUiState>(GameUiState())
    val gameUiState: StateFlow<GameUiState> = _gameUiState

    private var currentWord: String = ""

    private var words = listOf<String>()

    init {
        resetGame()
    }
    fun resetGame() {
        words = allWords.shuffled().subList(fromIndex = 0, toIndex = MAX_NO_OF_WORDS)
        currentWord = ""
        _gameUiState.value = GameUiState()
        nextWord()
    }

    private fun nextWord () {
        val index = gameUiState.value.wordIndex
        val finished = index >= MAX_NO_OF_WORDS
        if (finished) {
            endGame()
            return
        }
        val nextWord = words[index]
        currentWord = nextWord
        val scrambledWord = scramble(nextWord)
        _gameUiState.update {
            it.copy(
                scrambledWord = scrambledWord,
                guessedWord = "",
                wordIndex = index.inc(),
                wrongGuess = false,
            )
        }

        Log.d(
            TAG,
            "current word: $currentWord\n" +
                    "current scrambled word: $scrambledWord\n" +
                    "current word index: $index\n"
        )
    }
    private fun endGame() {
        _gameUiState.update {
            it.copy(
                gameFinished = true,
            )
        }
    }
    private fun scramble(word: String): String {
        val chars = word.toCharArray()
        while (String(chars) == word) {
            chars.shuffle()
        }
        return String(chars)
    }
    fun changeGuess (newGuessedWord: String) {
        _gameUiState.update {
            it.copy(guessedWord = newGuessedWord)
        }
    }
    fun checkGuess () {
        val guess = gameUiState.value.guessedWord.trim()
        val passed = guess.equals(currentWord, ignoreCase = true)
        if (passed) {
            nextWord()
            increaseScore()
        } else {
            wrongGuess()
        }
    }
    private fun increaseScore() {
        val score = gameUiState.value.score + SCORE_INCREASE
        _gameUiState.update { it.copy(score = score) }
    }
    private fun wrongGuess() {
        _gameUiState.update {
            it.copy(guessedWord = "", wrongGuess = true)
        }
    }
    fun skipWord() {
        nextWord()
    }
}