package com.dev.bayan_ibrahim.compose_course_2.ui.view_model

import android.util.Log
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.lifecycle.ViewModel
import com.dev.bayan_ibrahim.compose_course_2.domain.model.Sport
import com.dev.bayan_ibrahim.compose_course_2.domain.model.SportsUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class SportsViewModel : ViewModel() {
    private val _sportsUiState = MutableStateFlow<SportsUiState>(SportsUiState())
    val sportsUiState: StateFlow<SportsUiState> = _sportsUiState

    fun setCurrentSport(sport: Sport) {
        _sportsUiState.update {
            it.copy(
                sport = sport,
            )
        }
    }

    fun navigate(isHomeScreen: Boolean) {
        _sportsUiState.update {
            it.copy(
                isHomeScreen = isHomeScreen
            )
        }
    }

    fun setWindowSize(windowSize: WindowWidthSizeClass) {
        _sportsUiState.update {
            Log.d("windowSize", "setWindowSize: current window size: $windowSize")
            it.copy(
                windowSize = windowSize,
            )
        }
    }
}
