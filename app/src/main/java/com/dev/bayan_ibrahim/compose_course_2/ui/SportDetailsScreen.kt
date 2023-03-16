package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.activity.compose.BackHandler
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.dev.bayan_ibrahim.compose_course_2.R
import com.dev.bayan_ibrahim.compose_course_2.data.LocalSportsDataProvider
import com.dev.bayan_ibrahim.compose_course_2.domain.model.Sport
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun SportDetailsScreen(
    modifier: Modifier = Modifier,
    sport: Sport,
    onBackButtonClicked: () -> Unit,
) {
    BackHandler {
        onBackButtonClicked()
    }
    Column (
        modifier = modifier
            .fillMaxSize(),
    ) {
        SportDetailsScreenImageBox(
            imageRes = sport.sportsImageBanner,
            title = sport.titleResourceId,
        )
        SportDetailsScreenNews(newsDetails = sport.newsDetails)
    }
}

@Composable
private fun SportDetailsScreenImageBox (
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    @StringRes title: Int,
) {
    Box(
        modifier = modifier,
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .padding(8.dp)
                .align(Alignment.BottomStart),
            text = stringResource(id = title),
            fontSize = 24.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White,
        )
    }
}

@Composable
private fun SportDetailsScreenNews (
    modifier: Modifier = Modifier,
    @StringRes newsDetails: Int,
) {
    Column(
        modifier = modifier
            .padding(16.dp),
    ) {
        Text(
            text = stringResource(id = R.string.news_title),
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.height(24.dp))
        Text(
            text = stringResource(id = newsDetails),
            style = MaterialTheme.typography.body1,
            textAlign = TextAlign.Justify,
        )
    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun SportDetailsScreenFunctionForPreview() {
    SportDetailsScreen(
        sport = LocalSportsDataProvider.defaultSport,
        onBackButtonClicked = {},
    )
}

@Preview(showBackground = false)
@Composable
private fun SportDetailsScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            SportDetailsScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun SportDetailsScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            SportDetailsScreenFunctionForPreview()
        }
    }
}
