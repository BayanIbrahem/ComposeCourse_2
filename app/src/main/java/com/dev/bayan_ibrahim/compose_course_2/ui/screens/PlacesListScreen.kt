package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.data.LocalDataProvider
import com.dev.bayan_ibrahim.compose_course_2.domain.model.Place
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun PlacesListScreen(
    modifier: Modifier = Modifier,
    places: List<Place>,
    onItemClick: (place: Place) -> Unit,
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
    ) {
        items(places) {
            PlacesListScreenCard(place = it, isExpanded = false, onItemClick = onItemClick)
        }
    }
}

@Composable
private fun PlacesListScreenCard(
    modifier: Modifier = Modifier,
    place: Place,
    isExpanded: Boolean,
    onItemClick: (place: Place) -> Unit
) {
    Card(
        modifier = modifier
            .padding(16.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessMedium,
                )
            ),
    ) {
        Column(
            modifier = Modifier
                .clickable {
                    onItemClick(place)
                },
        ) {
            Row(
                modifier = Modifier,
            ) {
                PlacesListScreenCardImage(imageRes = place.imageRes)
                Spacer(modifier = Modifier.width(12.dp))
                PlacesListScreenCardSummary(place = place)
            }
        }
        if (isExpanded) {
            PlacesListScreenCardSubDescription(description = place.description)
        }
    }

}

@Composable
private fun PlacesListScreenCardImage(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int
) {
    Image(
        modifier = modifier
            .size(160.dp)
            .clip(RoundedCornerShape(size = 10f))
            .aspectRatio(ratio = 1f),
        painter = painterResource(id = imageRes),
        contentDescription = null,
        contentScale = ContentScale.Crop,
    )
}

@Composable
private fun PlacesListScreenCardSummary(
    modifier: Modifier = Modifier,
    place: Place,
) {
    Column (
        modifier = modifier.fillMaxHeight(),
    ) {
        Text(text = place.name, style = MaterialTheme.typography.h4)
        Spacer(modifier = Modifier.height(12.dp))
        Text(text = place.location, style = MaterialTheme.typography.body1)
        Spacer(modifier = Modifier.weight(1f))
        Text(
            modifier = Modifier.align(Alignment.End),
            text = "${place.formattedPrice} per ${place.unitPriceAverageDescription}",
            style = MaterialTheme.typography.caption
        )
    }
}

@Composable
private fun PlacesListScreenCardSubDescription(
    modifier: Modifier = Modifier,
    @StringRes description: Int,
) {
    val text = stringResource(id = description).subSequence(0, 50).toString() + "..."
    Text(
        text = text,
        modifier = modifier,
    )
}
// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun PlacesListScreenFunctionForPreview() {
    PlacesListScreen(
        places = LocalDataProvider.allPlaces,
        onItemClick = {}
    )
}

@Preview(showBackground = false)
@Composable
private fun PlacesListScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesListScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PlacesListScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlacesListScreenFunctionForPreview()
        }
    }
}
