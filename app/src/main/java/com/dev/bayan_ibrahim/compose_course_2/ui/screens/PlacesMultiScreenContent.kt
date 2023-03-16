package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.PermanentDrawerSheet
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme
import androidx.compose.material3.PermanentNavigationDrawer
import androidx.compose.material3.Text
import androidx.compose.ui.res.painterResource
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.data.LocalDataProvider
import com.dev.bayan_ibrahim.compose_course_2.domain.model.*
import com.dev.bayan_ibrahim.compose_course_2.navigationList

@Composable
fun PlacesMultiScreenContent(
    modifier: Modifier = Modifier,
    places: List<Place>,
    placesUiState: PlacesUiState,
    onSelectNavigateItem: (PlaceNavigationItemContent) -> Unit,
    navigationList: List<PlaceNavigationItemContent>,
    onItemClick: (place: Place) -> Unit,
    ) {
    PermanentNavigationDrawer(
        drawerContent = {
            PermanentDrawerSheet(Modifier.width(240.dp)) {
                PermanentDrawerSheet() {
                    NavigationItemsDrawer(
                        navigationList = navigationList,
                        currentTab = navigationList.first { it.type == placesUiState.currentScreen },
                        onSelectNavigateItem = onSelectNavigateItem
                    )
                }
            }
        }
    ) {
        Row(modifier = modifier) {
            PlacesSingleScreenContent(
                modifier= Modifier.weight(1f),
                places = places,
                placesUiState = placesUiState,
                navigationType = NavigationType.PERMANENT_NAVIGATION_DRAWER,
                onSelectNavigateItem = onSelectNavigateItem,
                navigationList = navigationList,
                onItemClick = onItemClick,
                onBackClicked = {}
            )
            PlaceDetailsScreen(
                modifier= Modifier.weight(1f),
                place = placesUiState.currentItem,
                onBackClicked = {},
                isFullScreen = false,
            )
        }
    }
}

@Composable
private fun NavigationItemsDrawer(
    modifier: Modifier = Modifier,
    navigationList: List<PlaceNavigationItemContent>,
    currentTab: PlaceNavigationItemContent,
    onSelectNavigateItem: (PlaceNavigationItemContent) -> Unit
) {
    NavigationDrawerHeader(modifier = modifier)
    for(navItem in navigationList) {
        NavigationDrawerItem(
            label = { Text(text = navItem.text) },
            icon = {
                Icon(
                    painter = painterResource(id = navItem.icon),
                    contentDescription = null
                )
            },
            selected = currentTab == navItem,
            onClick = { onSelectNavigateItem(navItem) }
        )
    }
}

@Composable
private fun NavigationDrawerHeader(
    modifier: Modifier = Modifier,
) {
    Text(
        modifier = modifier.padding(16.dp),
        text = "My City Places", style = MaterialTheme.typography.h6,
    )
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun PlacesMultiScreenContentFunctionForPreview() {
    PlacesMultiScreenContent(
        places = LocalDataProvider.allPlaces,
        placesUiState = PlacesUiState(),
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
        onItemClick = {

        }
    )
}

@Preview(showBackground = false, widthDp = 1200)
@Composable
private fun PlacesMultiScreenContentPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesMultiScreenContentFunctionForPreview()
        }
    }
}

@Preview(showBackground = false, widthDp = 1200)
@Composable
private fun PlacesMultiScreenContentPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesMultiScreenContentFunctionForPreview()
        }
    }
}
