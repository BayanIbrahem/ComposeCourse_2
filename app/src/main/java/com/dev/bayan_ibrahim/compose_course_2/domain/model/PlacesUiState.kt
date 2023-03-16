package com.dev.bayan_ibrahim.compose_course_2.domain.model

import com.dev.bayan_ibrahim.compose_course_2.data.LocalDataProvider

data class PlacesUiState (
    val currentScreen: PlacesTypes = PlacesTypes.Cafe,
    val currentItem: Place = LocalDataProvider.defaultCafe,
    val isHomeScreen: Boolean = true,
)