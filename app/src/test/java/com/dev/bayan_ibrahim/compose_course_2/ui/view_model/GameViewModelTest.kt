package com.dev.bayan_ibrahim.compose_course_2.ui.view_model

import com.dev.bayan_ibrahim.compose_course_2.data.MAX_NO_OF_WORDS
import com.dev.bayan_ibrahim.compose_course_2.data.SCORE_AFTER_FIRST_CORRECT_ANSWER
import com.dev.bayan_ibrahim.compose_course_2.data.SCORE_INCREASE
import com.dev.bayan_ibrahim.compose_course_2.data.getUnscrambledWord
import org.junit.Assert.*
import org.junit.Test

class GameViewModelTest {
    private val gameViewModel = GameViewModel()
    @Test
    fun GameViewModel_CorrectWordGuessed_IncreaseScoreUnsetWrongGuess() {
        var currentUiState = gameViewModel.gameUiState.value
        val currentWord = getUnscrambledWord(currentUiState.scrambledWord)

        gameViewModel.changeGuess(currentWord)
        gameViewModel.checkGuess()

        currentUiState = gameViewModel.gameUiState.value

        assertEquals(20, currentUiState.score)
        assertEquals(false, currentUiState.wrongGuess)
    }

    @Test
    fun GameViewModel_IncoorectGuess_ScoreRemainsSetWrongGuess(){
        var currentUiState = gameViewModel.gameUiState.value
        val wrongWord = "and"

        gameViewModel.changeGuess(wrongWord)
        gameViewModel.checkGuess()

        currentUiState = gameViewModel.gameUiState.value

        assertEquals(0, currentUiState.score)
        assertEquals(true, currentUiState.wrongGuess)
    }

    @Test
    fun GameViewModel_Initialisation_FirstWordLoaded() {
        val gameUiState = gameViewModel.gameUiState.value
        val unScrambledWord = getUnscrambledWord(gameUiState.scrambledWord)

        // Assert that current word is scrambled.
        assertNotEquals(unScrambledWord, gameUiState.scrambledWord)
        // Assert that current word count is set to 1.
        assertTrue(gameUiState.wordIndex == 1)
        // Assert that initially the score is 0.
        assertTrue(gameUiState.score == 0)
        // Assert that the wrong word guessed is false.
        assertFalse(gameUiState.wrongGuess)
        // Assert that game is not over.
        assertFalse(gameUiState.gameFinished)
    }
    @Test
    fun gameViewModel_AllWordsGuessed_UiStateUpdatedCorrectly() {
        var expectedScore = 0
        var currentGameUiState = gameViewModel.gameUiState.value
        var correctPlayerWord = getUnscrambledWord(currentGameUiState.scrambledWord)
        repeat(MAX_NO_OF_WORDS) {
            expectedScore += SCORE_INCREASE
            gameViewModel.changeGuess(correctPlayerWord)
            gameViewModel.checkGuess()
            currentGameUiState = gameViewModel.gameUiState.value
            correctPlayerWord = getUnscrambledWord(currentGameUiState.scrambledWord)
            // Assert that after each correct answer, score is updated correctly.
            assertEquals(expectedScore, currentGameUiState.score)
        }
        // Assert that after all questions are answered, the current word count is up-to-date.
        assertEquals(MAX_NO_OF_WORDS, currentGameUiState.wordIndex)
        // Assert that after 10 questions are answered, the game is over.
        assertTrue(currentGameUiState.gameFinished)
    }

    @Test
    fun gameViewModel_WordSkipped_ScoreUnchangedAndWordCountIncreased() {
        var gameUiState = gameViewModel.gameUiState.value
        val correctPlayerWord = getUnscrambledWord(gameUiState.scrambledWord)
        gameViewModel.changeGuess(correctPlayerWord)
        gameViewModel.checkGuess()

        gameUiState = gameViewModel.gameUiState.value
        val lastWordCount = gameUiState.wordIndex
        gameViewModel.skipWord()
        gameUiState = gameViewModel.gameUiState.value
        // Assert that score remains unchanged after word is skipped.
        assertEquals(SCORE_AFTER_FIRST_CORRECT_ANSWER, gameUiState.score)
        // Assert that word count is increased by 1 after word is skipped.
        assertEquals(lastWordCount + 1, gameUiState.wordIndex)
    }
}