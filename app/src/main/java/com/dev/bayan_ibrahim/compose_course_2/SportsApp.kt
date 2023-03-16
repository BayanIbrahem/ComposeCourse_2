package com.dev.bayan_ibrahim.compose_course_2


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.windowsizeclass.WindowWidthSizeClass
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.dev.bayan_ibrahim.compose_course_2.data.LocalSportsDataProvider
import com.dev.bayan_ibrahim.compose_course_2.domain.model.Sport
import com.dev.bayan_ibrahim.compose_course_2.domain.model.SportsUiState
import com.dev.bayan_ibrahim.compose_course_2.ui.SportDetailsScreen
import com.dev.bayan_ibrahim.compose_course_2.ui.SportsListScreen
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme
import com.dev.bayan_ibrahim.compose_course_2.ui.view_model.SportsViewModel

@Composable
fun SportsApp(
    modifier: Modifier = Modifier,
    viewModel: SportsViewModel = viewModel(),
    windowSize: androidx.compose.material3.windowsizeclass.WindowWidthSizeClass,
) {
    val sportsUiState by viewModel.sportsUiState.collectAsState()


    Scaffold(
        modifier = modifier,
        topBar = {
            SportsAppTopAppBar(
                onNavigateUp = { viewModel.navigate(true) },
                isNavigableToHomeScreen = sportsUiState.isHomeScreen.not(),
            )
        }
    ) {
        viewModel.setWindowSize(windowSize = windowSize)
        Row(
            modifier = Modifier.padding(it),
        ) {
            if (
                sportsUiState.isHomeScreen
                || sportsUiState.windowSize != WindowWidthSizeClass.Compact
            ) {
                SportsListScreen(
                    modifier = Modifier
                        .weight(1f),
                    sports = LocalSportsDataProvider.getSportsData(),
                    onCardClick = { sport ->
                        viewModel.setCurrentSport(sport)
                        viewModel.navigate(false)
                    } ,
                )
            } else if (sportsUiState.windowSize == WindowWidthSizeClass.Compact){
                SportDetailsScreen(
                    modifier = Modifier
                        .weight(1f),
                    sport = sportsUiState.sport,
                    onBackButtonClicked = {
                    }
                )
            }
            AnimatedVisibility(
                modifier = Modifier.weight(1f),
                visible = sportsUiState.windowSize != WindowWidthSizeClass.Compact,
            ) {
                SportDetailsScreen(
                    sport = sportsUiState.sport,
                    onBackButtonClicked = {
                    }
                )
            }
        }

    }
}

@Composable
private fun SportsAppTopAppBar (
    modifier: Modifier = Modifier,
    onNavigateUp: () -> Unit,
    isNavigableToHomeScreen: Boolean,
) {
    Row(
        modifier = modifier
            .background(color = MaterialTheme.colors.primary)
            .padding(16.dp)
            .height(52.dp)
            .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        if (isNavigableToHomeScreen) {
            IconButton(
                onClick = onNavigateUp,
            ) {
                Icon(
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = null,
                    tint = MaterialTheme.colors.onPrimary,
                )
            }
        }
        val title = if (isNavigableToHomeScreen) stringResource(R.string.sport_news) else stringResource(R.string.sport)
        Text(
            text = title,
            color = MaterialTheme.colors.onPrimary,
            style = MaterialTheme.typography.h6,
        )
    }
}

@Composable
private fun SportsAppCompat(
    modifier: Modifier = Modifier,
    sportsUiState: SportsUiState,
    sports: List<Sport>,
    onCardClick: (Sport)-> Unit,
    onBackButtonClicked: () -> Unit,
) {
    if (sportsUiState.isHomeScreen) {
        SportsListScreen(
            modifier = modifier,
            sports = sports,
            onCardClick = {sport ->
                onCardClick(sport)
            }
        )
    } else {
        SportDetailsScreen(
            modifier = modifier,
            sport = sportsUiState.sport,
            onBackButtonClicked = {
                onBackButtonClicked()
            }
        )
    }
}

@Composable
private fun SportsAppMediumAndExpanded(
    modifier: Modifier = Modifier,
    sports: List<Sport>,
    sportsUiState: SportsUiState,
    onCardClick: (Sport) -> Unit,
) {
}
// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun SportsAppFunctionForPreview() {
    SportsApp(windowSize = WindowWidthSizeClass.Compact)
}

@Preview(showBackground = false)
@Composable
private fun SportsAppPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            SportsAppFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun SportsAppPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            SportsAppFunctionForPreview()
        }
    }
}
