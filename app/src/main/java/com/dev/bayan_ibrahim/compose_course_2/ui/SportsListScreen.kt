package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.annotation.DrawableRes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.data.LocalSportsDataProvider
import com.dev.bayan_ibrahim.compose_course_2.domain.model.Sport
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme
import com.dev.bayan_ibrahim.compose_course_2.R

@Composable
fun SportsListScreen(
    modifier: Modifier = Modifier,
    sports: List<Sport>,
    onCardClick: (Sport) -> Unit
) {
    LazyColumn (
        modifier = modifier,
    ) {
        items(sports) {
            SportsListItemCard(sport = it, onClick = {sport -> onCardClick(sport)})
        }
    }
}

@Composable
private fun SportsListItemCard(
    modifier: Modifier = Modifier,
    sport: Sport,
    onClick: (Sport) -> Unit
) {
    Card (
        modifier = modifier
            .clickable { onClick(sport) }
            .padding(16.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth(),
        ) {
            SportsListItemCardImage(imageId = sport.imageResourceId)
            Spacer(modifier = Modifier.width(12.dp))
            SportsListItemCardDetails(/*modifier = Modifier.weight(1f),*/ sport = sport)
        }
    }
}

@Composable
private fun SportsListItemCardImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageId: Int,
) {
    Image(
        modifier = modifier
            .size(100.dp)
            .aspectRatio(ratio = 1f)
            .clip(RoundedCornerShape(10.dp)),
        painter = painterResource(id = imageId),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun SportsListItemCardDetails(
    modifier: Modifier = Modifier,
    sport: Sport,
) {
    Column (
        modifier = modifier
            .padding(vertical = 8.dp),

    ) {
        Spacer(modifier = Modifier.weight(1f))
        Text (
            modifier = modifier,
            text = stringResource(id = sport.titleResourceId),
            style = MaterialTheme.typography.h5,
        )
        Spacer(modifier = Modifier.weight(1f))
        Text (
            modifier = modifier,
            text = stringResource(id = R.string.news_title),
            style = MaterialTheme.typography.h6,
            //TODO make color for dark theme
        )
        Spacer(modifier = Modifier.weight(1f))
        Text (
            modifier = modifier,
            text = stringResource(id = sport.subtitleResourceId),
            style = MaterialTheme.typography.body1,
        )

    }
}

// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun SportsListScreenFunctionForPreview() {
    SportsListScreen(
        sports = LocalSportsDataProvider.getSportsData(),
        onCardClick = {},
    )
}

@Preview(showBackground = false)
@Composable
private fun SportsListScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            SportsListScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun SportsListScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            SportsListScreenFunctionForPreview()
        }
    }
}
