package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.data.LocalDataProvider
import com.dev.bayan_ibrahim.compose_course_2.domain.model.*
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun PlacesSingleScreenContent(
    modifier: Modifier = Modifier,
    places: List<Place>,
    placesUiState: PlacesUiState,
    navigationType: NavigationType,
    onSelectNavigateItem: (PlaceNavigationItemContent) -> Unit,
    navigationList: List<PlaceNavigationItemContent>,
    onItemClick: (place: Place) -> Unit,
    onBackClicked: () -> Unit,
) {
    Row(
        modifier = modifier,
    ) {
        AnimatedVisibility(
            visible = navigationType == NavigationType.NAVIGATION_RAIL
        ) {
            PlacesNavigationRail(
                navigationItems = navigationList,
                onSelectNavigationItem = onSelectNavigateItem,
                currentTab = navigationList.first { it.type == placesUiState.currentScreen }
            )
        }
        Column(
            modifier = Modifier,
        ) {
            if (placesUiState.isHomeScreen) {
                PlacesListScreen(
                    modifier = modifier.weight(1f),
                    places = places,
                    onItemClick = onItemClick,
                )
            } else {
                PlaceDetailsScreen(
                    place = placesUiState.currentItem,
                    onBackClicked = onBackClicked,
                    isFullScreen = true,
                )
            }
            AnimatedVisibility(
                visible = navigationType == NavigationType.BOTTOM_NAVIGATION
            ) {
                PlacesBottomBarNavigation(
                    navigationItems = navigationList,
                    onSelectNavigationItem = onSelectNavigateItem,
                    currentTab = navigationList.first { it.type == placesUiState.currentScreen }
                )
            }
        }
    }
}

@Composable
private fun PlacesNavigationRail(
    modifier: Modifier = Modifier,
    navigationItems: List<PlaceNavigationItemContent>,
    onSelectNavigationItem: (PlaceNavigationItemContent) -> Unit,
    currentTab: PlaceNavigationItemContent
) {
    NavigationRail(modifier = modifier) {
        for (navItem in navigationItems) {
            NavigationRailItem(
                selected = navItem == currentTab,
                onClick = { onSelectNavigationItem(navItem) },
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.icon),
                        contentDescription = navItem.text,
                    )

                }
            )
        }
    }
}

@Composable
private fun PlacesBottomBarNavigation(
    modifier: Modifier = Modifier,
    navigationItems: List<PlaceNavigationItemContent>,
    currentTab: PlaceNavigationItemContent,
    onSelectNavigationItem: (PlaceNavigationItemContent) -> Unit
) {
    BottomNavigation (
        modifier = modifier
            .fillMaxWidth()
            .height(50.dp),
    ) {
        for (navItem in navigationItems) {
            BottomNavigationItem(
                selected = navItem == currentTab,
                onClick = { onSelectNavigationItem(navItem) },
                icon = {
                    Icon(
                        painter = painterResource(id = navItem.icon),
                        contentDescription = navItem.text
                    )
                }
            )
        }
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun PlacesSingleScreenContentFunctionForPreview() {
    PlacesSingleScreenContent(
        places = LocalDataProvider.allPlaces,
        placesUiState = PlacesUiState(),
        navigationType = NavigationType.BOTTOM_NAVIGATION,
        onSelectNavigateItem = {},

        navigationList = listOf<PlaceNavigationItemContent>(
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
        ),
        onItemClick = {},
        onBackClicked = {},
    )
}

@Preview(showBackground = false)
@Composable
private fun PlacesSingleScreenContentPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesSingleScreenContentFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PlacesSingleScreenContentPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesSingleScreenContentFunctionForPreview()
        }
    }
}
