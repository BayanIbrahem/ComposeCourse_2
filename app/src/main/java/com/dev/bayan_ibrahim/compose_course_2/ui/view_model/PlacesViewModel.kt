package com.dev.bayan_ibrahim.compose_course_2.ui.view_model

import androidx.lifecycle.ViewModel
import com.dev.bayan_ibrahim.compose_course_2.data.LocalDataProvider
import com.dev.bayan_ibrahim.compose_course_2.domain.model.PlacesTypes
import com.dev.bayan_ibrahim.compose_course_2.domain.model.Place
import com.dev.bayan_ibrahim.compose_course_2.domain.model.PlacesUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update

class PlacesViewModel: ViewModel() {
    private val _placesUiState = MutableStateFlow(PlacesUiState())
    val placesUiState: StateFlow<PlacesUiState> = _placesUiState

    fun getCurrentPlacesList(): List<Place> {
        return when (placesUiState.value.currentScreen) {
            PlacesTypes.Cafe -> {
                LocalDataProvider.allCafes
            }
            PlacesTypes.Restaurant -> {
                LocalDataProvider.allRestaurants
            }
            PlacesTypes.Hotel -> {
                LocalDataProvider.allHotels
            }
        }
    }

    fun setCurrentTab(type: PlacesTypes) {
        _placesUiState.update { it.copy(currentScreen = type) }
    }

    fun setCurrentItem(place: Place) {
        _placesUiState.update { it.copy(currentItem = place, isHomeScreen = false) }
    }
    fun returnToHomeScreen() {
        _placesUiState.update { it.copy(isHomeScreen = true) }
    }

}