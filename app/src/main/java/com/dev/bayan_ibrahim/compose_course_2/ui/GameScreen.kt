package com.dev.bayan_ibrahim.compose_course_2.ui

import android.app.Activity
import android.util.Log
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.bayan_ibrahim.compose_course_2.data.MAX_NO_OF_WORDS
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme
import com.dev.bayan_ibrahim.compose_course_2.ui.view_model.GameViewModel
import kotlin.math.sin


@Composable
fun GameScreen(gameViewModel: GameViewModel = viewModel(), modifier: Modifier = Modifier) {
    val gameUiState = gameViewModel.gameUiState.collectAsState().value
    Column(
        modifier = modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        GameStatus(
            index = gameUiState.wordIndex,
            score = gameUiState.score
        )
        GameLayout(
            scrambledWord = gameUiState.scrambledWord,
            guessedWord = gameUiState.guessedWord,
            isWrongGuess = gameUiState.wrongGuess,
            onGuessedWordChanged = { gameViewModel.changeGuess(it) },
            onKeyboardDone = { gameViewModel.checkGuess() }
        )
        GameButtons(
            onSkip = { gameViewModel.skipWord() },
            onConfirm = { gameViewModel.checkGuess() },
        )
    }
    if (gameUiState.gameFinished) {
        FinishedGameDialog(
            score = gameUiState.score,
            onPlayAgain = {
                Log.d("GameScreen", "play again clicked, state before resetting game $gameUiState")
                gameViewModel.resetGame()
                Log.d("GameScreen", "play again clicked, state after resetting game $gameUiState")
            }
        )
    }
}

@Composable
fun GameStatus(modifier: Modifier = Modifier, index: Int, score: Int) {
    Row(modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)
    ) {
        Text(
            modifier = Modifier
                .weight(1f),
            text = "Word $index of $MAX_NO_OF_WORDS",
            style = MaterialTheme.typography.body1,
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(text = "Score $score")
    }
}

@Composable
fun GameLayout(
    modifier: Modifier = Modifier,
    scrambledWord: String,
    guessedWord: String,
    onGuessedWordChanged: (String) -> Unit,
    isWrongGuess: Boolean,
    onKeyboardDone: () -> Unit,
) {
    Column(
        modifier = modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = scrambledWord,
            style = MaterialTheme.typography.h3,
        )
        Text(
            text = "Guess the word",
            style = MaterialTheme.typography.body1,
        )
        OutlinedTextField(
            value = guessedWord,
            onValueChange = { onGuessedWordChanged(it) },
            label = { Text(text = "Word") },
            isError = isWrongGuess,
            singleLine = true,
            keyboardActions = KeyboardActions(
                onDone = { onKeyboardDone() }
            ),
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Done,
            )
        )
    }
}

@Composable
fun GameButtons(modifier: Modifier = Modifier, onSkip: () -> Unit, onConfirm: () -> Unit) {
    Row(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
    ) {
        OutlinedButton(
            modifier = Modifier.weight(1f),
            onClick = { onSkip() },
        ) {
            Text (text = "Skip", style = MaterialTheme.typography.button)
        }
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            modifier = Modifier.weight(1f),
            onClick = { onConfirm() },
        ) {
            Text (text = "Confirm", style = MaterialTheme.typography.button)
        }
    }
}

@Composable
fun FinishedGameDialog (
    modifier: Modifier = Modifier,
    score: Int,
    onPlayAgain: () -> Unit,
) {
    val activity = ( LocalContext.current as Activity )
    AlertDialog(
        onDismissRequest = {
            // Dismiss the dialog when the user clicks outside the dialog or on the back
            // button. If you want to disable that functionality, simply use an empty
            // onCloseRequest.
        },
        title = { Text(text = "Congregations!") },
        text = { Text(text = "You scored $score point") },
        modifier = modifier,
        dismissButton = {
            TextButton(
                onClick = {
                    activity.finish()
                }
            ) {
                Text(text = "Exit")
            }
        },
        confirmButton = {
            TextButton(
                onClick = {
                    onPlayAgain()
                }
            ) {
                Text(text = "Play Again")
            }
        }
    )
}
@Preview (showBackground = true)
@Composable
fun GameScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface (color = MaterialTheme.colors.background){
            GameScreen()
        }
    }
}

@Preview (showBackground = true)
@Composable
fun GameScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface (color = MaterialTheme.colors.background){
            GameScreen()
        }
    }
}
