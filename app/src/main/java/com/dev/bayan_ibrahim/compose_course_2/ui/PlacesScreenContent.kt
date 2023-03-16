package com.dev.bayan_ibrahim.compose_course_2


import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.dev.bayan_ibrahim.compose_course_2.data.LocalDataProvider
import com.dev.bayan_ibrahim.compose_course_2.domain.model.*
import com.dev.bayan_ibrahim.compose_course_2.ui.PlacesMultiScreenContent
import com.dev.bayan_ibrahim.compose_course_2.ui.PlacesSingleScreenContent
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

val navigationList = listOf<PlaceNavigationItemContent>(
    PlaceNavigationItemContent(
        text = "Cafe",
        icon = R.drawable.coffee,
        type = PlacesTypes.Cafe,
    ),
    PlaceNavigationItemContent(
        text = "Restaurants",
        icon = R.drawable.restaurant,
        type = PlacesTypes.Restaurant,
    ),
    PlaceNavigationItemContent(
        text = "Hotels",
        icon = R.drawable.hotel,
        type = PlacesTypes.Hotel,
    ),
)
@Composable
fun PlacesScreenContent(
    modifier: Modifier = Modifier,
    places: List<Place>,
    placesUiState: PlacesUiState,
    navigationType: NavigationType,
    onSelectNavigateItem: (PlaceNavigationItemContent) -> Unit,
    onItemClick: (place: Place) -> Unit,
    onBackClick: () -> Unit,
) {
    if (navigationType == NavigationType.PERMANENT_NAVIGATION_DRAWER) {
        PlacesMultiScreenContent(
            navigationList = navigationList,
            onSelectNavigateItem = onSelectNavigateItem,
            places = places,
            placesUiState = placesUiState,
            onItemClick = onItemClick,
        )
    } else {
        PlacesSingleScreenContent(
            modifier = modifier,
            navigationList = navigationList,
            navigationType = navigationType,
            onSelectNavigateItem = onSelectNavigateItem,
            places = places,
            placesUiState = placesUiState,
            onItemClick = onItemClick,
            onBackClicked = onBackClick,
        )
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun PlacesScreenContentFunctionForPreview() {
    PlacesScreenContent(
        places = LocalDataProvider.allPlaces,
        placesUiState = PlacesUiState(),
        onSelectNavigateItem = {},
        navigationType = NavigationType.BOTTOM_NAVIGATION,
        onItemClick = {

        },
        onBackClick = {}
    )
}

@Preview(showBackground = false)
@Composable
private fun PlacesScreenContentPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesScreenContentFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PlacesScreenContentPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesScreenContentFunctionForPreview()
        }
    }
}
