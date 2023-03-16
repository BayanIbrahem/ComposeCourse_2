package com.dev.bayan_ibrahim.compose_course_2.domain.model

import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import com.dev.bayan_ibrahim.compose_course_2.data.LocalSportsDataProvider

data class SportsUiState(
    val sport: Sport = LocalSportsDataProvider.defaultSport,
    val isHomeScreen: Boolean = true,
    val windowSize: WindowWidthSizeClass = WindowWidthSizeClass.Compact,
)
