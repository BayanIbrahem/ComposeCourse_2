package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.bayan_ibrahim.compose_course_2.PlacesScreenContent
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme
import com.dev.bayan_ibrahim.compose_course_2.ui.view_model.PlacesViewModel
import com.dev.bayan_ibrahim.compose_course_2.domain.model.NavigationType
import com.dev.bayan_ibrahim.compose_course_2.domain.model.PlacesContentType
@Composable
fun PlacesApp(
    modifier: Modifier = Modifier,
    placesViewModel: PlacesViewModel = viewModel(),
    windowSize: WindowWidthSizeClass,
) {
    Scaffold(
        topBar = {
            
        }
    ) {
        Modifier.padding(it)
        val placesUiState by placesViewModel.placesUiState.collectAsState()
        val navigationType: NavigationType
        val placesContentType: PlacesContentType
        when (windowSize) {
            WindowWidthSizeClass.Compact -> {
                navigationType = NavigationType.BOTTOM_NAVIGATION
                placesContentType = PlacesContentType.LIST_ONLY
            }
            WindowWidthSizeClass.Medium -> {
                navigationType = NavigationType.NAVIGATION_RAIL
                placesContentType = PlacesContentType.LIST_ONLY
            }
            WindowWidthSizeClass.Expanded -> {
                navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER
                placesContentType = PlacesContentType.LIST_AND_DETAIL
            }
            else -> {
                navigationType = NavigationType.BOTTOM_NAVIGATION
                placesContentType = PlacesContentType.LIST_ONLY
            }
        }
        PlacesScreenContent(
            places = placesViewModel.getCurrentPlacesList(),
            placesUiState = placesUiState,
            navigationType = navigationType,
            onSelectNavigateItem = {item ->
                placesViewModel.setCurrentTab(item.type)
            },
            onItemClick = {place ->
                placesViewModel.setCurrentItem(place)
            },
            onBackClick = {
                placesViewModel.returnToHomeScreen()
            }
        )
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun PlacesAppFunctionForPreview() {
    PlacesApp(
        windowSize = WindowWidthSizeClass.Compact
    )
}

@Preview(showBackground = false)
@Composable
private fun PlacesAppPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesAppFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PlacesAppPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesAppFunctionForPreview()
        }
    }
}
