package com.dev.bayan_ibrahim.compose_course_2.ui


import androidx.annotation.DrawableRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.Orientation
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role.Companion.Image
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.dev.bayan_ibrahim.compose_course_2.data.LocalDataProvider
import com.dev.bayan_ibrahim.compose_course_2.domain.model.Place
import com.dev.bayan_ibrahim.compose_course_2.ui.components.LabelValueText
import com.dev.bayan_ibrahim.compose_course_2.ui.theme.ComposeCourse_2Theme

@Composable
fun PlaceDetailsScreen(
    modifier: Modifier = Modifier,
    place: Place,
    onBackClicked: () -> Unit,
    isFullScreen: Boolean,
) {
    Column(
        modifier = modifier,
    ) {
        AnimatedVisibility(visible = isFullScreen) {
            IconButton(onClick = onBackClicked) {
                Icon(
                    modifier = Modifier.padding(16.dp),
                    imageVector = Icons.Filled.ArrowBack,
                    contentDescription = "Navigate Up",
                )
            }
        }
        PlaceDetailsScreenImageBox(imageRes = place.imageRes, name = place.name)
        Spacer(modifier = Modifier.padding(16.dp))
        PlaceDetailsScreenFullDetails(place = place)
    }
}

@Composable
private fun PlaceDetailsScreenImageBox(
    modifier: Modifier = Modifier,
    @DrawableRes imageRes: Int,
    name: String,
) {
    Box(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Image(
            painter = painterResource(id = imageRes),
            contentDescription = null,
        )
        Text(
            modifier = Modifier
                .shadow(
                    elevation = 5.dp,
                )
                .align(Alignment.BottomStart)
                .padding(8.dp),
            text = name,
            fontWeight = FontWeight.Bold,
        )
    }
}

@Composable
private fun PlaceDetailsScreenFullDetails(
    modifier: Modifier = Modifier,
    place: Place,
    oneLine: Boolean = false,
) {
    Column(
        modifier = modifier
            .scrollable(
                rememberScrollState(),
                orientation = Orientation.Vertical,
            ),
    ) {
        LabelValueText(label = "Name", value = place.name, oneLine = oneLine)
        LabelValueText(label = "Rate", value = place.rate.toString(), oneLine = oneLine)
        LabelValueText(label = "Description", value = stringResource(id = place.description), oneLine = oneLine)
        LabelValueText(label = "Location", value = place.location, oneLine = oneLine)
        LabelValueText(label = "Price", value = place.formattedPriceWithUnit, oneLine = oneLine)
    }
}
// we use this function not to repeat code passing parameters to each preview.
@Composable
private fun PlaceDetailsScreenFunctionForPreview() {
    PlaceDetailsScreen(place = LocalDataProvider.defaultCafe, isFullScreen = true, onBackClicked = {})
}

@Preview(showBackground = false)
@Composable
private fun PlaceDetailsScreenPreviewLight() {
    ComposeCourse_2Theme(darkTheme = false) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlaceDetailsScreenFunctionForPreview()
        }
    }
}

@Preview(showBackground = false)
@Composable
private fun PlaceDetailsScreenPreviewDark() {
    ComposeCourse_2Theme(darkTheme = true) {
        Surface(
            modifier = Modifier,
            color = MaterialTheme.colors.background,
        ) {
            PlaceDetailsScreenFunctionForPreview()
        }
    }
}
